package person.daizhongde.virtue.util.ie;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * old class , use POICellStyle instead
 * <p>It will cause file open error alert.
 * @author daizd
 *
 */
public class POICellStyle_Constructor {
	//org.apache.poi.ss.usermodel.CellStyle
	//org.apache.poi.hssf.usermodel.HSSFCellStyle
	
	public org.apache.poi.hssf.usermodel.HSSFCellStyle textHeader;
	public org.apache.poi.hssf.usermodel.HSSFCellStyle text;
	/**  cs_text.setBorderBottom(cs_text.BORDER_THIN);  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle textBB;
	
	
	/** #,##0;[Red]-#,##0 C Comma*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP0;
	/** #,##0.0;[Red]-#,##0.0  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP1;
	/** #,##0.00;[Red]-#,##0.00  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP2;
	/** #,##0.000;[Red]-#,##0.000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP3;
	/** #,##0.0000;[Red]-#,##0.0000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP4;
	/** #,##0.00000;[Red]-#,##0.00000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP5;
	/** #,##0.000000;[Red]-#,##0.000000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP6;
	
	
	/** 0.00;[Red]-0.00 NC No comma 逗号 */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP0;
	/** 0.0;[Red]-0.0  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP1;
	/** 0.00;[Red]-0.00  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP2;
	/** 0.000;[Red]-0.000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP3;
	/** 0.0000;[Red]-0.0000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP4;
	/** 0.00000;[Red]-0.00000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP5;
	/** 0.000000;[Red]-0.000000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP6;
	/** 0.0000000000;[Red]-0.0000000000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP10;
	
	/** ￥#,##0;[Red]￥-#,##0  C Comma */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP0;
	/** ￥#,##0.0;[Red]￥-#,##0.0  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP1;
	/** ￥#,##0.00;[Red]￥-#,##0.00  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP2;
	/** ￥#,##0.000;[Red]￥-#,##0.000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP3;
	/** ￥#,##0.0000;[Red]￥-#,##0.0000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP4;
	/** ￥#,##0.00000;[Red]￥-#,##0.00000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP5;
	/** ￥#,##0.000000;[Red]￥-#,##0.000000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP6;
	
	
	/** ￥0;[Red]￥-0  NC No comma 逗号 */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP0;
	/** ￥0.0;[Red]￥-0.0  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP1;
	/** ￥0.00;[Red]￥-0.00  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP2;
	/** ￥0.000;[Red]￥-0.000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP3;
	/** ￥0.0000;[Red]￥-0.0000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP4;
	/** ￥0.00000;[Red]￥-0.00000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP5;
	/** ￥0.000000;[Red]￥-0.000000  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP6;
	
	
	/** yyyy/m/d ,options: DateDelimiter, ZeroPaddingDate,  TimeDelimiter. */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle date;
	/** yyyy/mm/dd ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle dateZF;
	/** yyyy/m/d Not ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle dateNZF;
	
	/** yyyy-m-d ,transverse line  横线 */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle dateTL;
	/** yyyy-mm-dd ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle dateTLZF;
	/** yyyy-m-d Not ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle dateTLNZF;
	
	
	/** h:mm:ss Whenever time's minute and second is ZeroFilled*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle time;
	/** hh:mm:ss Hour ZeroFilled */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle timeZF;
	/** h:mm:ss Hour Not ZeroFilled*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle timeNZF;
	
	
	/** yyyy/m/d h:mm:ss Not ZeroPaddingDate  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetime;
	/** yyyy/mm/dd hh:mm:ss ZeroPaddingDate*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeZF;
	/** yyyy/m/d h:mm:ss Not ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeNZF;
	
	/** yyyy-m-d h:mm:ss Not ZeroPaddingDate. transverse line  横线  */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTL;
	/** yyyy-mm-dd hh:mm:ss ZeroPaddingDate*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTLZF;
	/** yyyy-m-d h:mm:ss Not ZeroPaddingDate */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTLNZF;
	
	
	/** 百分比  0.00%;[Red]-0.00%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rate;
	/** 百分比  0%;[Red]-0%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rateP0;
	/** 百分比  0.0%;[Red]-0.0%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rateP1;
	/** 百分比  0.00%;[Red]-0.00%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rateP2;
	/** 百分比  0.0000000000%;[Red]-0.0000000000%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rateP10;
	
	/** 分数 */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle fraction;
	/**
	 * <p>如果样式定义了在表单中不没有用到就会出现下面的问题:
	 * <br>&nbsp;文件错误，可能某些数字格式已丢失
	 * <br>解决办法就是用最新的POICellStyle类，在使用前使用按需初始化的方法
	 * @param wb
	 */
	public POICellStyle_Constructor(HSSFWorkbook wb){
//		POIFont font = new POIFont(wb);
		
		text = wb.createCellStyle();
		text.setDataFormat( wb.createDataFormat().getFormat("text"));
		
		textBB = wb.createCellStyle();
		textBB.setDataFormat( wb.createDataFormat().getFormat("text"));
		textBB.setBorderBottom( HSSFCellStyle.BORDER_THIN );
		org.apache.poi.hssf.usermodel.HSSFFont BBF = wb.createFont();//border bottom font
		BBF.setFontHeightInPoints((short) 12);
		BBF.setColor( HSSFFont.COLOR_NORMAL  );
		BBF.setBoldweight( org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD );
		textBB.setFont(BBF);
		
		textHeader = wb.createCellStyle();
		textHeader.setDataFormat( wb.createDataFormat().getFormat("text"));
		org.apache.poi.hssf.usermodel.HSSFFont headerF = wb.createFont();
		headerF.setFontHeightInPoints((short) 12);
		headerF.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);
		textHeader.setFont(headerF);
		
		
		numberCP0 = wb.createCellStyle();
		numberCP0.setDataFormat( wb.createDataFormat().getFormat("#,##0;[Red]-#,##0"));
		
		numberCP1 = wb.createCellStyle();
		numberCP1.setDataFormat( wb.createDataFormat().getFormat("#,##0.0;[Red]-#,##0.0"));
		
		numberCP2 = wb.createCellStyle();
		numberCP2.setDataFormat( wb.createDataFormat().getFormat("#,##0.00;[Red]-#,##0.00"));
		
		numberCP3 = wb.createCellStyle();
		numberCP3.setDataFormat( wb.createDataFormat().getFormat("#,##0.000;[Red]-#,##0.000"));
		
		numberCP4 = wb.createCellStyle();
		numberCP4.setDataFormat( wb.createDataFormat().getFormat("#,##0.0000;[Red]-#,##0.0000"));
		
		numberCP5 = wb.createCellStyle();
		numberCP5.setDataFormat( wb.createDataFormat().getFormat("#,##0.00000;[Red]-#,##0.00000"));
		
		numberCP6 = wb.createCellStyle();
		numberCP6.setDataFormat( wb.createDataFormat().getFormat("#,##0.000000;[Red]-#,##0.000000"));
		
		
		numberNCP0 = wb.createCellStyle();
		numberNCP0.setDataFormat( wb.createDataFormat().getFormat("0;[Red]-0"));
		
		numberNCP1 = wb.createCellStyle();
		numberNCP1.setDataFormat( wb.createDataFormat().getFormat("0.0;[Red]-0.0"));
		
		numberNCP2 = wb.createCellStyle();
		numberNCP2.setDataFormat( wb.createDataFormat().getFormat("0.00;[Red]-0.00"));
		
		numberNCP3 = wb.createCellStyle();
		numberNCP3.setDataFormat( wb.createDataFormat().getFormat("0.000;[Red]-0.000"));
		
		numberNCP4 = wb.createCellStyle();
		numberNCP4.setDataFormat( wb.createDataFormat().getFormat("0.0000;[Red]-0.0000"));
		
		numberNCP5 = wb.createCellStyle();
		numberNCP5.setDataFormat( wb.createDataFormat().getFormat("0.00000;[Red]-0.00000"));
		
		numberNCP6 = wb.createCellStyle();
		numberNCP6.setDataFormat( wb.createDataFormat().getFormat("0.000000;[Red]-0.000000"));
		
		numberNCP10 = wb.createCellStyle();
		numberNCP10.setDataFormat( wb.createDataFormat().getFormat("0.0000000000;[Red]-0.0000000000"));
		
		org.apache.poi.hssf.usermodel.HSSFFont moneyF = wb.createFont();
		moneyF.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);
		
		
		moneyCP0 = wb.createCellStyle();
		moneyCP0.setDataFormat( wb.createDataFormat().getFormat("￥#,##0;[Red]￥-#,##0"));
		moneyCP0.setFont(moneyF);
		
		moneyCP1 = wb.createCellStyle();
		moneyCP1.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.0;[Red]￥-#,##0.0"));
		moneyCP1.setFont(moneyF);
		
		moneyCP2 = wb.createCellStyle();
		moneyCP2.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.00;[Red]￥-#,##0.00"));
		moneyCP2.setFont(moneyF);
		
		moneyCP3 = wb.createCellStyle();
		moneyCP3.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.000;[Red]￥-#,##0.000"));
		moneyCP3.setFont(moneyF);
		
		moneyCP4 = wb.createCellStyle();
		moneyCP4.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.0000;[Red]￥-#,##0.0000"));
		moneyCP4.setFont(moneyF);
		
		moneyCP5 = wb.createCellStyle();
		moneyCP5.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.00000;[Red]￥-#,##0.00000"));
		moneyCP5.setFont(moneyF);
		
		moneyCP6 = wb.createCellStyle();
		moneyCP6.setDataFormat( wb.createDataFormat().getFormat("￥#,##0.000000;[Red]￥-#,##0.000000"));
		moneyCP6.setFont(moneyF);
		
		
		moneyNCP0 = wb.createCellStyle();
		moneyNCP0.setDataFormat( wb.createDataFormat().getFormat("￥0;[Red]￥-0"));
		moneyNCP0.setFont(moneyF);
		
		moneyNCP1 = wb.createCellStyle();//￥0.00;[红色]￥-0.00
		moneyNCP1.setDataFormat( wb.createDataFormat().getFormat("￥0.00;[红色]￥-0.00"));
		moneyNCP1.setFont(moneyF);
		
		moneyNCP2 = wb.createCellStyle();
		moneyNCP2.setDataFormat( wb.createDataFormat().getFormat("￥0.00;[Red]￥-0.00"));
		moneyNCP2.setFont(moneyF);
		
		moneyNCP3 = wb.createCellStyle();
		moneyNCP3.setDataFormat( wb.createDataFormat().getFormat("￥0.000;[Red]￥-0.000"));
		moneyNCP3.setFont(moneyF);
		
		moneyNCP4 = wb.createCellStyle();
		moneyNCP4.setDataFormat( wb.createDataFormat().getFormat("￥0.0000;[Red]￥-0.0000"));
		moneyNCP4.setFont(moneyF);
		
		moneyNCP5 = wb.createCellStyle();
		moneyNCP5.setDataFormat( wb.createDataFormat().getFormat("￥0.00000;[Red]￥-0.00000"));
		moneyNCP5.setFont(moneyF);
		
		moneyNCP6 = wb.createCellStyle();
		moneyNCP6.setDataFormat( wb.createDataFormat().getFormat("￥0.000000;[Red]￥-0.000000"));
		moneyNCP6.setFont(moneyF);
		
		
		date = wb.createCellStyle();
		date.setDataFormat( wb.createDataFormat().getFormat("yyyy/m/d"));
		dateZF = wb.createCellStyle();
		dateZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/mm/dd"));
		dateNZF = wb.createCellStyle();
		dateNZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/m/d"));
		
		dateTL = wb.createCellStyle();
		dateTL.setDataFormat( wb.createDataFormat().getFormat("yyyy-m-d"));
		dateTLZF = wb.createCellStyle();
		dateTLZF.setDataFormat( wb.createDataFormat().getFormat("yyyy-mm-dd"));
		dateTLNZF = wb.createCellStyle();
		dateTLNZF.setDataFormat( wb.createDataFormat().getFormat("yyyy-m-d"));
		
		
		time = wb.createCellStyle();
		time.setDataFormat( wb.createDataFormat().getFormat("h:m:s"));
		timeZF = wb.createCellStyle();
		timeZF.setDataFormat( wb.createDataFormat().getFormat("hh:mm:ss"));
		timeNZF = wb.createCellStyle();
		timeNZF.setDataFormat( wb.createDataFormat().getFormat("h:m:s"));
		
		
		datetime = wb.createCellStyle();
		datetime.setDataFormat( wb.createDataFormat().getFormat("yyyy/m/d h:mm:ss"));
		datetimeZF = wb.createCellStyle();
		datetimeZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
		datetimeNZF = wb.createCellStyle();
		datetimeNZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/m/d h:mm:ss"));
		
		datetimeTL = wb.createCellStyle();
		datetimeTL.setDataFormat( wb.createDataFormat().getFormat("yyyy-m-d h:mm:ss"));
		datetimeTLZF = wb.createCellStyle();
		datetimeTLZF.setDataFormat( wb.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
		datetimeTLNZF = wb.createCellStyle();
		datetimeTLNZF.setDataFormat( wb.createDataFormat().getFormat("yyyy-m-d h:mm:ss"));
		
		
		rate = wb.createCellStyle();
		rate.setDataFormat( wb.createDataFormat().getFormat("0.00%;[Red]-0.00%"));
		
		rateP0 = wb.createCellStyle();
		rateP0.setDataFormat( wb.createDataFormat().getFormat("0%;[Red]-0%"));
		
		rateP1 = wb.createCellStyle();
		rateP1.setDataFormat( wb.createDataFormat().getFormat("0.0%;[Red]-0.0%"));
		
		rateP2 = wb.createCellStyle();
		rateP2.setDataFormat( wb.createDataFormat().getFormat("0.00%;[Red]-0.00%"));
		
		rateP10 = wb.createCellStyle();
		rateP10.setDataFormat( wb.createDataFormat().getFormat("0.0000000000%;[Red]-0.0000000000%"));
		
		fraction = wb.createCellStyle();
		fraction.setDataFormat( wb.createDataFormat().getFormat("# ?/?;[Red]-# ?/?"));
	}
	public static void main(String args[]) {
				
		HSSFWorkbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("缴款书表单1");
//	    Sheet sheet2 = wb.createSheet("second sheet");
	    sheet1.createFreezePane(0, 1, 0, 1);
	    HSSFRow row = null;
	    HSSFCell cell = null;
		org.apache.poi.hssf.usermodel.HSSFCellStyle cs1 = wb.createCellStyle();
		HSSFCellStyle  cs_text = wb.createCellStyle();
		HSSFCellStyle cs_date = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();

	    // create 2 fonts objects
	    org.apache.poi.hssf.usermodel.HSSFFont f1 = wb.createFont();
	    org.apache.poi.hssf.usermodel.HSSFFont f2 = wb.createFont();

	    // Set font 1 to 12 point type, blue and bold
	    f1.setFontHeightInPoints((short) 12);
	    f1.setColor( HSSFFont.COLOR_RED );
	    f1.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);

	    // Set font 2 to 10 point type, red and bold
	    f2.setFontHeightInPoints((short) 10);
	    f2.setColor( HSSFFont.COLOR_RED );
	    f2.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);

	    // Set cell style and formatting
	    cs1.setFont(f1);

	    cs_text.setBorderBottom(cs_text.BORDER_THIN);
	    cs_text.setFont(f2);
	    
	    cs_date.setBorderBottom(cs_date.BORDER_THIN);
	    cs_date.setFont(f2);
	}
}
