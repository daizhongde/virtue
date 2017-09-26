package person.daizhongde.virtue.util.ie;

import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import person.daizhongde.virtue.util.date.DateUtils;

/**
 * HSSFWorkbook util
 * <br>To construtor a HSSFWorkbook and generate a sheet
 * @author dzd
 *
 */
public class HSSFWorkbookUtil4Import {
	private static Logger log = LogManager.getLogger(HSSFWorkbookUtil4Import.class.getName() );
	
	private long startMili = 0;
	private long endMili = 0;
		
	/** workbook object **/
	private HSSFWorkbook wb;
	
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
    
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
	
	private HSSFFormulaEvaluator evaluator;
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
	public HSSFWorkbookUtil4Import(
			HSSFWorkbook wb,

			int[] columnTypes,
			int[] columnPrecisions,
			int[] columnScales,
			String[] columnNames_zh,
			
			Map options,
			
			HSSFFormulaEvaluator evaluator
			){
		this.wb = wb;
		this.columnNames_zh = columnNames_zh;
		this.columnTypes = columnTypes;
		this.columnPrecisions = columnPrecisions;
		this.columnScales = columnScales;
		
		this.options = options;
		

		this.evaluator = evaluator;
	}

	/**
	 * No RecordNo, include Column title or not
	 * @param sheetNumber
	 * @param list
	 * @param currentSheetRecordCount
	 * @throws ParseException 
	 */
	public List<Object[]> readASheetData( int sheetNumber ) throws ParseException{
		/* 用于存放数据 */
		List<Object[]> list = new ArrayList<Object[]>();
		
		Sheet sheet = wb.getSheetAt(sheetNumber);
		int rows = sheet.getPhysicalNumberOfRows();
		System.out.println("Sheet " + sheetNumber + " \"" + wb.getSheetName(sheetNumber) + "\" has " + rows
				+ " row(s).");
		
		for (int r = 1; r < rows; r++) {
			Row row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			
//			int cells = row.getPhysicalNumberOfCells();
			int cells = columnNames_zh.length;
			Object[] oa_Row = new Object[cells];
			System.out.println("\nROW " + row.getRowNum() + " has " + cells
					+ " cell(s).");
			for (int c = 0; c < cells; c++) {
				
				Cell cell = row.getCell(c);
				
				if( null == cell ){
					oa_Row[c] = null;
					continue;
				}
				System.out.println("c:"+c+", cell:"+cell+", type:"+cell.getCellType() );
				
				String value = null;
				
				switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_FORMULA:
						oa_Row[c] = POICellUtil.getCellValueT(evaluator.evaluate(cell), columnTypes[c]);
						break;
						
					case HSSFCell.CELL_TYPE_STRING:
						if(columnTypes[c] == Types.DATE ){
							/* 根据前台传来的日期格式将string 转换成 Date  
							 * default 
							 *   DateOrder				: "YMD",
							 *   DateDelimiter			: "/",
							 *   TimeDelimiter			: ":",
							 *   DateTimeOrder			: "DT",
							 *   
							 * to format is :  yyyy/MM/dd HH:mm:ss 
							 * */
							String DateOrder = options.get("DateOrder").toString();
							String DateDelimiter = options.get("DateDelimiter").toString();
							String TimeDelimiter = options.get("TimeDelimiter").toString();
							String DateTimeOrder = options.get("DateTimeOrder").toString();
							
							boolean ZeroPaddingDate = Boolean.valueOf( options.get("ZeroPaddingDate").toString()).booleanValue();

							//Whenever time's minute and second is ZeroFilled
							String formatS = DateformatConverter4IE.convertCFG2format(DateOrder, DateDelimiter, TimeDelimiter, 
									DateTimeOrder, ZeroPaddingDate);
							
							oa_Row[c] = DateUtils.Str2Date(cell.getStringCellValue(), formatS );
							
						}else if( columnTypes[c] == Types.TIMESTAMP){
							/* 根据前台传来的日期格式将string 转换成 TIMESTAMP   */
							String DateOrder = options.get("DateOrder").toString();
							String DateDelimiter = options.get("DateDelimiter").toString();
							String TimeDelimiter = options.get("TimeDelimiter").toString();
							String DateTimeOrder = options.get("DateTimeOrder").toString();
							
							boolean ZeroPaddingDate = true;

							//Whenever time's minute and second is ZeroFilled
							String formatS = DateformatConverter4IE.convertCFG2format(DateOrder, DateDelimiter, TimeDelimiter, 
									DateTimeOrder, ZeroPaddingDate);
							
							Date date = DateUtils.Str2Date(cell.getStringCellValue(), formatS );
							
							oa_Row[c] = DateUtils.Date2Timestamp(date);;
						}else{
							oa_Row[c] = cell.getStringCellValue();
						}
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						/* 针对日期的处理，要么excel的column是时间类型，要么字符串符合系统默认的格式  */
						if(HSSFDateUtil.isCellDateFormatted(cell)){
							System.out.println("Date Cell: Yes!" );//对于日期的处理待完善。。。。对于工具导出的excel直接导入bug
							oa_Row[c] = cell.getDateCellValue();
						}else{
							oa_Row[c] = cell.getNumericCellValue();
						}
						break;

					case HSSFCell.CELL_TYPE_BLANK:
						oa_Row[c] = null;
						break;
						
					case HSSFCell.CELL_TYPE_BOOLEAN:
						oa_Row[c] = cell.getBooleanCellValue();
						break;
						
					default:

				}
			}
			list.add(oa_Row);
		}
		
		return list;
	}
//	/**
//	 * No RecordNo, include Column title
//	 * @param sheetNumber sheet number, start from 0  
//	 * @param list cells data(array), list.size() equals currentSheetRecordCount, 
//	 *  count pass in parameter to reduce excute time, because calculator large list's size need a little time
//	 * @param currentSheetRecordCount 
//	 */
//	public void generateASheetWithColumnTitle( int sheetNumber, List list, int currentSheetRecordCount ){
//				
//    	logger.debug("开始创建第<"+sheetNumber+">个sheet......");
//    	startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
//    	
//    	sheet = wb.createSheet(sheetName+"("+sheetNumber+")");
//		sheet.createFreezePane( fixColumnCount, 1, fixColumnCount, 1 );
//		
//		logger.debug("## No RecordNo, include Column title   ##");
//		
//	    row = sheet.createRow(0);
//	    row.setHeightInPoints(18);
//	    
//	    //set default column style, create表头-无序号列
//		for (int i = 0, j = columnNames_zh.length; i < j; i ++) 
//		{
//			//Sets the default column style for a given column.
//			POICellUtil.setDefaultColumnStyle(sheet, i,  cs, columnTypes[i], columnScales[i]);
//		    cell = row.createCell(i);
//		    cell.setCellStyle(cs.textHeader); 
//		    cell.setCellValue(columnNames_zh[i]);
//		}
//		//第二层：行循环
//	    for (int i = 0;  i < currentSheetRecordCount; i++ ) 
//	    {
//	    	row = sheet.createRow(i+1);
//	    	Object[] oa_Row = (Object[]) list.get(i);//this vector first column is record number
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
//	}
}
