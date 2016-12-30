package person.daizhongde.virtue.util.ie;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * HSSFWorkbook util
 * <br>To construtor a HSSFWorkbook and generate a sheet
 * <p>Like POI's HSSFWorkbookUtil.java
 * @author dzd
 *
 */
public class TXTUtil {
	private Logger log = LogManager.getLogger(TXTUtil.class.getName() );
	
	private long startMili = 0;
	private long endMili = 0;
		
	/** column's SQL type. SQL数据类型代码  **/
	private int[] columnTypes;
	/**  precision 精度  **/
	private int[] columnPrecisions;
	/**  column's number of digits to right of the decimal point. 小数点后数据位数 **/
	private int[] columnScales;
	/** column chinese name **/
	private String[] columnNames_zh;
	
	private Map options;
	
	private TXTCellUtil txtCellUtil;
	
	private String TextQualifier;
	/**
	 * whether excel include rownum due to invoke which method
	 * @param columnNames_zh
	 * @param columnTypes
	 * @param columnScale
	 */
	public TXTUtil(
			
			int[] columnTypes,
			int[] columnPrecisions,
			int[] columnScales,
			String[] columnNames_zh,
			
			Map options
			){
		
		this.columnTypes = columnTypes;
		this.columnPrecisions = columnPrecisions;
		this.columnScales = columnScales;
		this.columnNames_zh = columnNames_zh;
		
		this.options = options;
		this.txtCellUtil = new TXTCellUtil(options);
		
		this.TextQualifier = options.get("TextQualifier").toString().trim().equalsIgnoreCase("null")?
				"" : options.get("TextQualifier").toString();
		
	}
	/**
	 * 无序号
	 * @param sheetNumber sheet number, start from 0  
	 * @param v cells data, v.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large vector's size need a little time
	 * @param currentSheetRecordCount 
	 */
	public void generateASheet( int sheetNumber, Vector v,
			int currentSheetRecordCount, 
			BufferedWriter bw, 
			String fieldDelim ) throws IOException{
		
    	log.debug("开始写第<"+sheetNumber+">批数据......");
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) {
	    	Vector row = (Vector)v.get(i);//this vector first column is record number
//	    	System.out.println("oa_Row.length:"+oa_Row.length);
	    	
	    	//第一层：列循环------------------------无序号列---------------------------------------
	    	for (int j = 0, n = columnTypes.length; j < n; j++ ) {//如果j减一是因为要少导出一列(相对配置文件中的列)
	    		if(j!=n-1){
	    			bw.write((row.get(j)!=null?row.get(j).toString():"")+fieldDelim);
	    		}else{
	    			bw.write(row.get(j)!=null?row.get(j).toString():"");
	    		}
			}//第一层：列循环 column number
	    	bw.newLine();
		}//第二层：行循环 row number
	    log.debug("写第<"+sheetNumber+">批数据完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
	
	
	/**
	 * No RecordNO
	 * @param sheetNumber sheet number, start from 0  
	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large list's size need a little time
	 * @param currentSheetRecordCount 
	 * @throws IOException 
	 */
	public void generateASheet( int sheetNumber, 
			List list, 
			int currentSheetRecordCount, 
			BufferedWriter bw ) throws IOException{
		
//		System.out.println("list:\n"+JSONArray.fromObject(list).toString());
        
		
    	log.debug("开始写第<"+sheetNumber+">批数据......");
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
	    for (int i = 0;  i < currentSheetRecordCount; i++ )
	    {
	    	Object[] row = (Object[]) list.get(i);//this vector first column is record number
//	    	Printer.print(oa_Row);
	    	
	    	//第一层：列循环------------------------无序号列---------------------------------------
	    	for (int j = 0, n = columnTypes.length; j < n; j++ )
	    	{//如果j减一是因为要少导出一列(相对配置文件中的列)
	    		if(j!=n-1)
	    		{
	    			bw.write( TextQualifier+
	    					txtCellUtil.getCellValueT( row[j], columnTypes[j], columnScales[j]  )+
	    					TextQualifier+
	    					options.get("FieldDelimiter").toString());
	    			
//	    			bw.write((row[j]!=null?row[j].toString():"")+fieldDelim);
	    		}
	    		else
	    		{
	    			bw.write( TextQualifier+
	    					txtCellUtil.getCellValueT( row[j], columnTypes[j],columnScales[j] ) +
	    					TextQualifier );
	    		}
			}//第一层：列循环 column number
//	    	bw.newLine();
	    	bw.write(options.get("RecordDelimiter").toString());
	    	
		}//第二层：行循环 row number
	    log.debug("写第<"+sheetNumber+">批数据完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
        
        /* 经过不权威的粗略测试第2种方法较第1种方法效率低 
    	log.debug("2开始写第<"+sheetNumber+">批数据......");
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		String line = "";
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) {
	    	Object[] oa_Row = (Object[]) list.get(i);//this vector first column is record number
	    	
	    	line="";
	    	//------------------------add for test run effection--------------------------------------
	    	for (int j = 0, n = columnTypes.length; j < n; j++ ) {//如果j减一是因为要少导出一列(相对配置文件中的列)
	    		if(j!=n-1){
	    			line += (oa_Row[j]!=null?oa_Row[j].toString():"")+fieldDelim;
	    		}else{
	    			line += oa_Row[j]!=null?oa_Row[j].toString():"";
	    		}
			}//第一层：列循环 column number
	    	bw.write(line);
	    	bw.newLine();
		}//第二层：行循环 row number
	    log.debug("2写第<"+sheetNumber+">批数据完成!");
	    endMili=System.currentTimeMillis();
        log.info("2总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");*/
	}
	public String getTextQualifier() {
		return TextQualifier;
	}
	
}
