package person.daizhongde.virtue.util.ie;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * HSSFWorkbook util
 * <br>To construtor a HSSFWorkbook and generate a sheet
 * @author dzd
 *
 */
public class HSSFWorkbookUtil4Export {
	private static Logger log = LogManager.getLogger(HSSFWorkbookUtil4Export.class.getName() );
	
	private long startMili = 0;
	private long endMili = 0;
	
	private int fixColumnCount;
	private boolean sheetAutoSizeColumn;
	
	
	/** workbook object **/
	private HSSFWorkbook wb;
	
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
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
	public HSSFWorkbookUtil4Export(
			HSSFWorkbook wb,
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
	 * 无序号
	 * @param sheetNumber sheet number, start from 0  
	 * @param v cells data, v.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large vector's size need a little time
	 * @param currentSheetRecordCount 
	 */
	public void generateASheet( int sheetNumber, Vector v, int currentSheetRecordCount ){
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		
    	log.debug("开始创建第<"+sheetNumber+">个sheet......");
    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
		sheet.createFreezePane( fixColumnCount , 1, fixColumnCount, 1 ); 

		if( Boolean.valueOf(options.get("IncludeColumnTitles").toString()).booleanValue() )
		{
		    row = sheet.createRow(0);
		    row.setHeightInPoints(18);
		    
		    //有表头-无序号列
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
			    cell = row.createCell(i);
			    cell.setCellStyle(cs.textHeader); 
			    cell.setCellValue(columnNames_zh[i]);
			}
		}
		else
		{
		    //无表头-无序号列
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
			}
		}

		//第二层：行循环
//		for (int i = 0, j=v.size();  i < j; i++ ) {
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) {
	    	row = sheet.createRow(i+1);
	    	Vector v_Row = (Vector)v.get(i);//this vector first column is record number
	    	
	    	//第一层：列循环------------------------无序号列---------------------------------------
	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) {//如果j减一是因为要少导出一列(相对配置文件中的列)
				cell = row.createCell(j);
				POICellUtil.setCellValueT(v_Row.get(j), cell, columnTypes[j] );
			}//第一层：列循环 column number
		}//第二层：行循环 row number
	    
	    if( sheetAutoSizeColumn ){
	    	long start = System.currentTimeMillis();// 当前时间对应的毫秒数
	    	log.info("开始autoSizeColumn sheet......");
		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
		    	sheet.autoSizeColumn( (short)i );
		    }
		    log.info("autoSizeColumn sheet 完成!");
		    long end = System.currentTimeMillis();
            log.info("autoSizeColumn 总耗时为："+(start-end)+"毫秒，"+(start-end)/1000+"秒!");
	    }
	    
	    log.debug("第<"+sheetNumber+">个sheet创建完成!");
	    
	    endMili=System.currentTimeMillis();
	    log.info("创建第<"+sheetNumber+">个sheet总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
	/**
	 * 有序号
	 * @param sheetNumber sheet number, start from 0  
	 * @param v cells data, v.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large vector's size need a little time
	 * @param currentSheetRecordCount
	 * @param currentRecordNumber current record number 序号, equals sheetNumber*pageSize
	 */
	public void generateASheet( int sheetNumber, Vector v, int currentSheetRecordCount, int currentRecordNumber ){
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		
    	log.debug("开始创建第<"+sheetNumber+">个sheet......");
    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
		sheet.createFreezePane( fixColumnCount, 1, fixColumnCount, 1 );

		if( Boolean.valueOf(options.get("IncludeColumnTitles").toString()).booleanValue() )
		{
		    row = sheet.createRow(0);
		    row.setHeightInPoints(18);
		    
		    //有表头-有序号
		    sheet.setDefaultColumnStyle((short)0, cs.numberNCP0);
		    cell = row.createCell(0);
		    cell.setCellStyle(cs.textHeader); 
		    cell.setCellValue("序号");
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i+1,  cs, columnTypes[i], columnScales[i]);
			    cell = row.createCell(i+1);
			    cell.setCellStyle(cs.textHeader); 
			    cell.setCellValue(columnNames_zh[i]);
			}
		}
		else
		{
		    //无表头-有序号
		    sheet.setDefaultColumnStyle((short)0, cs.numberNCP0);
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i+1,  cs, columnTypes[i], columnScales[i]);
			}
		}
		//第二层：行循环
//		for (int i = 0, j=v.size();  i < j; i++ ) {
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) {
	    	row = sheet.createRow(i+1);
	    	Vector v_Row = (Vector)v.get(i);//this vector first column is record number
	    	//有序号
	    	cell = row.createCell(0);
	    	/* i + (sheetNumber - 1) * fileRecordSize //start from 1
	    	 * currentRecordNumber
	    	 */
	    	/* i + sheetNumber * fileRecordSize+1  //start from 0
	    	 * currentRecordNumber
	    	 */
	    	currentRecordNumber ++;
	    	cell.setCellValue( currentRecordNumber  );
	    	//第一层：列循环
	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) {//如果j减一是因为要少导出一列(相对配置文件中的列)
				cell = row.createCell( j + 1 );
				POICellUtil.setCellValueT(v_Row.get(j), cell, columnTypes[j] );
			}//第一层：列循环 column number
	    	
		}//第二层：行循环 row number
	    
	    if( sheetAutoSizeColumn ){
	    	log.info("开始autoSizeColumn sheet......");
		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
		    	sheet.autoSizeColumn((short)i);
		    }
		    log.info("完成 autoSizeColumn sheet!");
	    }
	    log.debug("第<"+sheetNumber+">个sheet创建完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
//	public HSSFWorkbook generateASheet( int sheetNumber, List<Map> list ){
//		return hwb;
//	}
//	public HSSFWorkbook generateASheet( int sheetNumber, List<Object[]> list ){
//		
//	}
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
			log.debug("## 有表头-无序号列   ##");
			HSSFCellStyle textHeader = wb.createCellStyle();
			textHeader.setDataFormat( wb.createDataFormat().getFormat("text"));
			org.apache.poi.hssf.usermodel.HSSFFont headerF = wb.createFont();
			headerF.setFontHeightInPoints((short) 12);
//			headerF.setBoldweight( org.apache.poi.ss.usermodel.Font.BOLDWEIGHT_BOLD );
			headerF.setBoldweight( HSSFFont.BOLDWEIGHT_BOLD );
			
			textHeader.setFont(headerF);
			cs.setTextHeader(textHeader);
			
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
		
//		System.out.println("list:\n"+JSONArray.fromObject(list).toString());
		
    	log.debug("开始创建第<"+sheetNumber+">个sheet......");
    	startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
    	
    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
		sheet.createFreezePane( fixColumnCount, 1, fixColumnCount, 1 );
		
		log.debug("## No RecordNo, include Column title   ##");
		
	    row = sheet.createRow(0);
	    row.setHeightInPoints(18);
	    
	    //set default column style, create表头-无序号列
		for (int i = 0, j = columnNames_zh.length; i < j; i ++) 
		{
			//Sets the default column style for a given column.
			POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
		    cell = row.createCell(i);
		    cell.setCellStyle(cs.textHeader); 
		    cell.setCellValue(columnNames_zh[i]);
		}
		//第二层：行循环
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) 
	    {
	    	row = sheet.createRow(i+1);
	    	Object[] oa_Row = (Object[]) list.get(i);//this vector first column is record number
	    		    	
	    	//第一层：列循环------------------------无序号列---------------------------------------
	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) 
	    	{//如果j减一是因为要少导出一列(相对配置文件中的列)
				cell = row.createCell(j);
				POICellUtil.setCellValueT(oa_Row[j], cell, columnTypes[j] );
			}//第一层：列循环 column number
	    	
		}//第二层：行循环 row number


	    if( sheetAutoSizeColumn ){
	    	log.info("开始autoSizeColumn sheet......");
		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
		    	sheet.autoSizeColumn( (short)i );
		    }
		    log.info("完成autoSizeColumn sheet!");
	    }
	    log.debug("第<"+sheetNumber+">个sheet创建完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
	/**
	 * No RecordNo, No Column title
	 * @param sheetNumber sheet number, start from 0  
	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
	 *  count pass in parameter to reduce excute time, because calculator large list's size need a little time
	 * @param currentSheetRecordCount 
	 */
	public void generateASheetNoColumnTitle( int sheetNumber, List list, int currentSheetRecordCount){
		
//		System.out.println("list:\n"+JSONArray.fromObject(list).toString());
		
    	log.debug("开始创建第<"+sheetNumber+">个sheet......");
    	startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
    	
    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
		sheet.createFreezePane( fixColumnCount, 0, fixColumnCount, 0 );

		log.debug("## No RecordNo, No Column title ##");
		for (int i = 0, j = columnNames_zh.length; i < j; i ++)
		{
			POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
		}
		//第二层：行循环
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) 
	    {
	    	row = sheet.createRow(i);
	    	Object[] oa_Row = (Object[]) list.get(i);
	    	
	    	//第一层：列循环------------------------无序号列---------------------------------------
	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) 
	    	{//如果j减一是因为要少导出一列(相对配置文件中的列)
				cell = row.createCell(j);
				POICellUtil.setCellValueT(oa_Row[j], cell, columnTypes[j] );
			}//第一层：列循环 column number
	    	
		}//第二层：行循环 row number

	    if( sheetAutoSizeColumn ){
	    	log.info("开始autoSizeColumn sheet......");
		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
		    	sheet.autoSizeColumn( (short)i );
		    }
		    log.info("完成autoSizeColumn sheet!");
	    }
	    log.debug("第<"+sheetNumber+">个sheet创建完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
	/**
	 * 有序号
	 * @param sheetNumber sheet number, start from 0  
	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
	 *     count pass in parameter to reduce excute time, because calculator large list's size need a little time
	 * @param currentSheetRecordCount
	 * @param currentRecordNumber current record number 序号
	 */
	public void generateASheet( int sheetNumber, List list, int currentSheetRecordCount, int currentRecordNumber ){
    	startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
    	
    	log.debug("开始创建第<"+sheetNumber+">个sheet......");
    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
		sheet.createFreezePane( fixColumnCount, 1, fixColumnCount, 1 );
		
		if( Boolean.valueOf(options.get("IncludeColumnTitles").toString()).booleanValue() )
		{
			log.debug("## 有表头-有序号列   ##");
			// 遍历列头字段名
		    //String name : colNames
		    row = sheet.createRow(0);
		    row.setHeightInPoints(18);
		    
		    //set default column style, create表头-有序号
		    sheet.setDefaultColumnStyle( (short)0, cs.numberNCP0 );
		    cell = row.createCell(0);
		    cell.setCellStyle(cs.textHeader); 
		    cell.setCellValue("序号");
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i+1,  cs, columnTypes[i], columnScales[i]);
			    cell = row.createCell(i+1);
			    cell.setCellStyle(cs.textHeader); 
			    cell.setCellValue(columnNames_zh[i]);
			}
		}
		else
		{
			log.debug("## 无表头-有序号列  ##");
			//set default column style-有序号
		    sheet.setDefaultColumnStyle( (short)0, cs.numberNCP0);
			for (int i = 0, j = columnNames_zh.length; i < j; i ++) {
				//Sets the default column style for a given column.
				POICellUtil.setDefaultColumnStyle(sheet, i+1,  cs, columnTypes[i], columnScales[i]);
			}
		}
		
		//第二层：行循环
//		for (int i = 0, j=v.size();  i < j; i++ ) {
	    for (int i = 0;  i < currentSheetRecordCount; i++ ) {
	    	row = sheet.createRow(i+1);
	    	Object[] oa_Row = (Object[])list.get(i);//this vector first column is record number
	    	//有序号
	    	cell = row.createCell(0);
	    	/* i + (sheetNumber - 1) * fileRecordSize //start from 1
	    	 * currentRecordNumber
	    	 */
	    	/* i + sheetNumber * fileRecordSize+1  //start from 0
	    	 * currentRecordNumber
	    	 */
	    	currentRecordNumber ++;
	    	cell.setCellValue( currentRecordNumber  );
	    	//第一层：列循环
	    	for (int j = 0, n = this.columnTypes.length; j < n; j++ ) {//如果j减一是因为要少导出一列(相对配置文件中的列)
				cell = row.createCell( j + 1 );
				POICellUtil.setCellValueT(oa_Row[j], cell, columnTypes[j] );
			}//第一层：列循环 column number
	    	
		}//第二层：行循环 row number
	    
	    if( sheetAutoSizeColumn ){
	    	log.info("开始autoSizeColumn sheet......");
		    for(int i = 0; i < columnNames_zh.length + 1 ; i ++ ){
		    	sheet.autoSizeColumn( (short)i );
		    }
		    log.info("完成 autoSizeColumn sheet!");
	    }
	    
	    log.debug("第<"+sheetNumber+">个sheet创建完成!");
	    endMili=System.currentTimeMillis();
        log.info("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	}
}
