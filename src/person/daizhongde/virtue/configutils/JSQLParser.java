package person.daizhongde.virtue.configutils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.assemble.sql.SQLUtil;
import person.daizhongde.virtue.util.field.FIELDUtil;

/**
 * 解析SQL用
 * <br>
 * 为导出定制功能而写，见getSelectItem
 * <br>
 * select 的列表达式中不能包含from,这个很重要。
 * 不是没办法让它支持，只是非常的不推荐
 * <br>//do something , wait to finish.....	(only for copy use)
 * @author dzd
 *
 */
public class JSQLParser {
	
	private static Logger log = LogManager.getLogger(JSQLParser.class.getName() );
	
	/**
	 * 
	 * select 的列表达式中不能包含from,这个很重要
	 * @param sql
	 * @return
	 * @throws IOException 
	 */
	public static HashMap getSelectMap(String selectSQL) {
		log.debug("selectSQL:"+selectSQL);
//		file.encoding:GBK
//		System.getProperties().getProperty("file.encoding");
//		String csn = Charset.defaultCharset().name();
		String csn = System.getProperties().getProperty("file.encoding");
		return getSelectMap( selectSQL, csn );
	}
	/**
	 * The columns that can export,kep is uppercase.
	 * select 的列表达式中不能包含from,这个很重要
	 * @param sql
	 * @param charsetName
	 * @return
	 * @throws IOException 
	 */
	public static HashMap getSelectMap(String selectSQL, String charsetName) {
		
		log.debug("charsetName:"+charsetName);
		HashMap items = new HashMap();
		String sqlStr = selectSQL.trim().substring(7).trim();
//		int query_fromIndex = sqlStr.toLowerCase().indexOf("from ");
		
//		String customSelectCol = sqlStr.substring(0, query_fromIndex-1).trim();
//		String customSelectCol = selectSQL.trim().substring(0, query_fromIndex-1);
		String customSelectCol = SQLUtil.getSelectColSQL(sqlStr);
		log.debug("customSelectCol:"+customSelectCol);
		String key="";
//		String value="";
		
		byte[] b;
		try {
			b = customSelectCol.getBytes(charsetName);
		
			ByteArrayInputStream baIS = new ByteArrayInputStream(b); 
//			java.io.StringReader sr = new StringReader( customSelectCol );
//			java.io.StringWriter sw = new StringWriter();
//			java.lang.StringBuilder sbui = new StringBuilder();
			
			java.lang.StringBuffer sbuf = new StringBuffer();
			
//			int ParenthesesCount = 0;
			int pCount = 0;
//			boolean caseWhenThenEnd = false;
			boolean caseStatement = false;
	
			for( int m =0,n = b.length; m<n; m++ ){
	
				int i = baIS.read();
				char c = (char)i;
				
				switch(c){
				case ',':
					if(pCount==0 && caseStatement==false){
//						System.out.println("sbuf.toString():"+sbuf.toString());
//						key=sbuf.toString().substring(sbuf.toString().lastIndexOf(" ")+1, sbuf.toString().length()-1);
						String tempCol = sbuf.toString().trim();
						//select t1.N_MID   , t1.C_MNAME   , //leaf to solve
						key= new String(
								tempCol.substring(tempCol.lastIndexOf(" ")+1).getBytes("ISO-8859-1"),
								charsetName);

//						if( key.indexOf("\"")==-1 ){
						if( key.indexOf("\"")==-1 ){
							key = key.toUpperCase();
						}else{
							key = key.replaceAll("\"", "");
						}
						key = FIELDUtil.getColumnLabel(key);
						
//						String value = new String( tempCol.getBytes( "ISO-8859-1" ),charsetName).trim();
//						items.put(key, value );
						
						items.put(key, new String( tempCol.getBytes( "ISO-8859-1" ),charsetName).trim() );
						
//						System.out.println("sbuf.toString():"+new String(sbuf.toString().getBytes( "ISO-8859-1" ),charsetName).trim());
						
//						CharacterConvert.testCharSet(sbuf.toString());//ISO-8859-1 to GBK 
						sbuf = new StringBuffer();
					}else{
						sbuf.append(c);
					}
					break;
				case '(':;
					pCount++;
					sbuf.append(c);
					break;
				case ')':
					pCount--;
					sbuf.append(c);
					break;
				default:
					if(sbuf.toString().equalsIgnoreCase("CASE "))
					{
						caseStatement = true;
					}
					else if(sbuf.toString().length()<6)
					{
						
					}
					else if(sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(",CASE ")
						||sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(" CASE "))
					{
						caseStatement = true;
					}
					else if(sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(" END "))
					{
						caseStatement = false;
					}
					sbuf.append(c);
				}//end of switch
	
			}//end of while
						
			String tempCol = sbuf.toString().trim();
			//select t1.N_MID   , t1.C_MNAME   , //leaf to solve			
			key= new String(
					tempCol.substring(tempCol.lastIndexOf(" ")+1).getBytes("ISO-8859-1"),
					charsetName);
			
//			if( key.indexOf("\"")==-1 ){
			if( key.indexOf("\"")==-1 ){
				key = key.toUpperCase();
			}else{
				key = key.replaceAll("\"", "");
			}
			key = FIELDUtil.getColumnLabel(key);
			
			items.put(key, new String(sbuf.toString().getBytes( "ISO-8859-1" ),charsetName).trim());
			sbuf = null;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return items;
	}
	/**
	 * for AuthorityModule.Export.export.DefaultColumns index suport
	 * <br>custom export
	 * <br>注:
	 * <br>select 的列表达式中不能包含from,这个很重要
	 * @param sql
	 * @return
	 * @throws IOException 
	 */
	public static List getSelectItem(String selectSQL ){
//		String csn = Charset.defaultCharset().name();
		String csn = System.getProperties().getProperty("file.encoding");
		return getSelectItem( selectSQL, csn );
	}
	
	/**
	 * for AuthorityModule.Export.export.DefaultColumns index suport
	 * <br>custom export
	 * <br>注:
	 * <br>select 的列表达式中不能包含from,这个很重要
	 * @param sql
	 * @param charsetName
	 * @return
	 * @throws IOException 
	 */
	public static List getSelectItem(String selectSQL, String charsetName ){
		List items = new ArrayList();
		String sqlStr = selectSQL.trim().substring(7).trim();
		int query_fromIndex = sqlStr.toLowerCase().indexOf("from ");
		
		String customSelectCol = sqlStr.substring(0, query_fromIndex-1).trim();
		
		String key="";
//		String value="";
		
		byte[] b;
		try {
			b = customSelectCol.getBytes(charsetName);

			ByteArrayInputStream baIS = new ByteArrayInputStream(b); 
//			java.io.StringReader sr = new StringReader( customSelectCol );
//			java.io.StringWriter sw = new StringWriter();
//			java.lang.StringBuilder sbui = new StringBuilder();
			
			java.lang.StringBuffer sbuf = new StringBuffer();
			
//			int ParenthesesCount = 0;
			int pCount = 0;
//			boolean caseWhenThenEnd = false;
			boolean caseEnd = false;
	
			for( int m =0,n = b.length; m<n; m++ ){
	
				int i = baIS.read();
				char c = (char)i;
				
				switch(c){
				case ',':
					if(pCount==0 && caseEnd==false){
//						key=sbuf.toString().substring(sbuf.toString().lastIndexOf(" ")+1, sbuf.toString().length()-1);
						String tempCol = sbuf.toString().trim();
						key= new String(
								tempCol.substring(tempCol.lastIndexOf(" ")+1).getBytes("ISO-8859-1"),
								charsetName);

						if( key.indexOf("\"")==-1 ){
							key = key.toUpperCase();
						}else{
							key = key.replaceAll("\"", "");
						}
						key = FIELDUtil.getColumnLabel(key);
						
						items.add( new String(tempCol.getBytes( "ISO-8859-1" ),charsetName) );
//						CharacterConvert.testCharSet(sbuf.toString());//ISO-8859-1 to GBK 
						sbuf = new StringBuffer();
					}else{
						sbuf.append(c);
					}
					break;
				case '(':;
					pCount++;
					sbuf.append(c);
					break;
				case ')':
					pCount--;
					sbuf.append(c);
					break;
				default:
					if(sbuf.toString().equalsIgnoreCase("CASE "))
					{
						caseEnd = true;
					}
					else if(sbuf.toString().length()<6)
					{
						
					}
					else if(sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(",CASE ")
						||sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(" CASE "))
					{
						caseEnd = true;
					}
					else if(sbuf.toString().substring(sbuf.toString().length()-5).equalsIgnoreCase(" END "))
					{
						caseEnd = false;
					}
					sbuf.append(c);
				}//end of switch
	
			}//end of while
			
			key= new String(
					sbuf.toString().substring(sbuf.toString().lastIndexOf(" ")+1).getBytes("ISO-8859-1"),
					charsetName);

			if( key.indexOf("\"")==-1 ){
				key = key.toUpperCase();
			}else{
				key = key.replaceAll("\"", "");
			}
			key = FIELDUtil.getColumnLabel(key);
			
			items.add( new String(sbuf.toString().getBytes( "ISO-8859-1" ),charsetName) );
			sbuf = null;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String selectSQL  = "select t1.N_MID  , t1.C_MNAME, t1.N_Mlevel, decode(t1.N_Mlevel, 0, '零级', 1, '一级', 2, '二级', 3, '三级', 4, '四级', 5, '五级', 6, '六级', t1.N_Mlevel || '级') as \"level\", t1.C_Mleaf, decode(t1.C_Mleaf, 'true', '是', 'false', '否', t1.C_Mleaf) as \"leaf\", t1.N_MORDER, t1.N_MPARENT, t2.C_MNAME \"parent\", t1.C_MTARGET, decode(t1.C_MTARGET, 'R', '右边框架', 'B', '新窗口', 'T', '当前浏览器窗口', 'S', '当前框架', t1.C_MTARGET) as \"target\", t1.C_MICONCLS, t1.C_MEXPANDED, decode(t1.C_MEXPANDED, 'true', '是', 'false', '否', t1.C_MEXPANDED) as \"expanded\", t1.C_MCHECKED, decode(t1.C_MCHECKED, 'true', '是', 'false', '否', t1.C_MCHECKED) as \"checked\", t1.C_MPATH, t1.C_MNOTE, t1.C_MCTIME, t1.C_MCIP, t1.N_MCUSER, t1.C_MMTIME, t1.C_MMIP, t1.N_MMUSER from t_authority_module t1 left outer join t_authority_module t2 on t1.N_MPARENT = t2.N_MID";
//		String selectSQL ="s我";
//		HashMap map = getSelectMap( selectSQL, "UTF-8" );
//		HashMap map = getSelectMap( selectSQL);
//		Printer.print(map);
		
		SortedMap map = Charset.availableCharsets();
//		Printer.print(map);
		
//		List list = getSelectItem( selectSQL );
//		ListPrinter.print(list);
	}

}
