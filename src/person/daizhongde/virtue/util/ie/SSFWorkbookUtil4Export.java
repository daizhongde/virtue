package person.daizhongde.virtue.util.ie;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Workbook util
 * <br>To construtor a Workbook and generate a sheet
 * <p>还没有改完
 * @author dzd
 *
 */
public class SSFWorkbookUtil4Export {
	private static Logger log = LogManager.getLogger(SSFWorkbookUtil4Export.class.getName() );
	
	private long startMili = 0;
	private long endMili = 0;
	
	private int fixColumnCount;
	private boolean sheetAutoSizeColumn;
	
	
	/** workbook object **/
	private Workbook wb;
	
	private Sheet sheet;
	private Row row;
	private Cell cell;
    private POICellStyle cs;
    
	/** excel sheet name **/
	private String sheetName;

	/** column's SQL type. SQL数据类型代码  **/
	private int[] columnTypes;
	/**  precision 精度  **/
	private int[] columnPrecisions;
	/**  column's number of digits to right of the decimal point. 小数点后数据位数 **/
	private int[] columnScales;
	/** column chinese name **/
	private String[] columnNames_zh;
	/**
	 * var xlsExportOptions = {
			IncludeColumnTitles		: true,
			ContinueOnError	: false
		};
	 */
	private Map options;
	
	/**
	 * whether excel include rownum due to invoke which method
	 * @param hwb
	 * @param cs
	 * @param sheetName
	 * @param columnNames_zh
	 * @param columnTypes
	 * @param columnScales
	 * @param fixColumnCount
	 * @param sheetAutoSizeColumn
	 */
	public SSFWorkbookUtil4Export(
			Workbook wb,
			POICellStyle cs,
			String sheetName,

			int[] columnTypes,
			int[] columnPrecisions,
			int[] columnScales,
			String[] columnNames_zh,
			
			int fixColumnCount,
			boolean sheetAutoSizeColumn,
			Map options
			){
		this.wb = wb;
		this.cs = cs;
		this.sheetName = sheetName;
		this.columnNames_zh = columnNames_zh;
		this.columnTypes = columnTypes;
		this.columnPrecisions = columnPrecisions;
		this.columnScales = columnScales;
		this.fixColumnCount = fixColumnCount;
		this.sheetAutoSizeColumn = sheetAutoSizeColumn;
		this.options = options;
	}

	/**
	 * No RecordNo, include Column title or not
	 * @param sheetNumber
	 * @param list
	 * @param currentSheetRecordCount
	 */
	public void generateASheet( int sheetNumber, List list, int currentSheetRecordCount){
		cs.initializeRequiredStyle(columnTypes, columnScales);
		if( Boolean.valueOf(options.get("IncludeColumnTitles").toString()).booleanValue() )
		{

			
			generateASheetWithColumnTitle( sheetNumber, list, currentSheetRecordCount );
		}
		else
		{
			log.debug("## 无表头-无序号列  ##");
			generateASheetNoColumnTitle( sheetNumber, list, currentSheetRecordCount );
		}
	}
	/**
	 * No RecordNo, include Column title
	 * @param sheetNumber sheet number, start from 0  
	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large list's size need a little time
	 * @param currentSheetRecordCount 
	 */
	public void generateASheetWithColumnTitle( int sheetNumber, List list, int currentSheetRecordCount ){

	}
	/**
	 * No RecordNo, No Column title
	 * @param sheetNumber sheet number, start from 0  
	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large list's size need a little time
	 * @param currentSheetRecordCount 
	 */
	public void generateASheetNoColumnTitle( int sheetNumber, List list, int currentSheetRecordCount){
		
//    	logger.debug("开始创建第<"+sheetNumber+">个sheet......");
//    	startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
//    	
//    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
//		sheet.createFreezePane( fixColumnCount, 0, fixColumnCount, 0 );
//
//		logger.debug("## No RecordNo, No Column title ##");
//		for (int i = 0, j = columnNames_zh.length; i < j; i ++)
//		{
//			POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
//		}
//		//第二层：行循环
//	    for (int i = 0;  i < currentSheetRecordCount; i++ ) 
//	    {
//	    	row = sheet.createRow(i);
//	    	Object[] oa_Row = (Object[]) list.get(i);
//	    	
//	    	//第一层：列循环------------------------无序号列---------------------------------------
//	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) 
//	    	{//如果j减一是因为要少导出一列(相对配置文件中的列)
//				cell = row.createCell(j);
//				POICellUtil.setCellValueT(oa_Row[j], cell, columnTypes[j] );
//			}//第一层：列循环 column number
//	    	
//		}//第二层：行循环 row number
//
//	    if( sheetAutoSizeColumn ){
//	    	logger.info("开始autoSizeColumn sheet......");
//		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
//		    	sheet.autoSizeColumn( (short)i );
//		    }
//		    logger.info("完成autoSizeColumn sheet!");
//	    }
//	    logger.debug("第<"+sheetNumber+">个sheet创建完成!");
//	    endMili=System.currentTimeMillis();
//        logger.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
}
