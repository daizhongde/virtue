package person.daizhongde.virtue.util.ie;

import java.sql.Types;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * Create style auto by need
 * <p>
 * scale=9,用百分比表示，小数点后精度为10用小数表示
 * @author daizd
 *
 */
public class POICellStyle {
	//CellStyle
	//CellStyle
	
	private Workbook wb;
	
	public CellStyle textHeader;
	public CellStyle text;
	/**  cs_text.setBorderBottom(cs_text.BORDER_THIN);  */
	public CellStyle textBB;
	
	
	/** #,##0;[Red]-#,##0 C Comma*/
	public CellStyle numberCP0;
	/** #,##0.0;[Red]-#,##0.0  */
	public CellStyle numberCP1;
	/** #,##0.00;[Red]-#,##0.00  */
	public CellStyle numberCP2;
	/** #,##0.000;[Red]-#,##0.000  */
	public CellStyle numberCP3;
	/** #,##0.0000;[Red]-#,##0.0000  */
	public CellStyle numberCP4;
	/** #,##0.00000;[Red]-#,##0.00000  */
	public CellStyle numberCP5;
	/** #,##0.00000;[Red]-#,##0.000000  */
	public CellStyle numberCP6;
	
	
	/** 0.00;[Red]-0.00 NC No comma 逗号 */
	public CellStyle numberNCP0;
	/** 0.0;[Red]-0.0  */
	public CellStyle numberNCP1;
	/** 0.00;[Red]-0.00  */
	public CellStyle numberNCP2;
	/** 0.000;[Red]-0.000  */
	public CellStyle numberNCP3;
	/** 0.0000;[Red]-0.0000  */
	public CellStyle numberNCP4;
	/** 0.00000;[Red]-0.00000  */
	public CellStyle numberNCP5;
	/** 0.000000;[Red]-0.000000  */
	public CellStyle numberNCP6;
	/** 0.0000000000;[Red]-0.0000000000  */
	public CellStyle numberNCP10;
	
	/** ￥#,##0;[Red]￥-#,##0  C Comma */
	public CellStyle moneyCP0;
	/** ￥#,##0.0;[Red]￥-#,##0.0  */
	public CellStyle moneyCP1;
	/** ￥#,##0.00;[Red]￥-#,##0.00  */
	public CellStyle moneyCP2;
	/** ￥#,##0.000;[Red]￥-#,##0.000  */
	public CellStyle moneyCP3;
	/** ￥#,##0.0000;[Red]￥-#,##0.0000  */
	public CellStyle moneyCP4;
	/** ￥#,##0.00000;[Red]￥-#,##0.00000  */
	public CellStyle moneyCP5;
	/** ￥#,##0.000000;[Red]￥-#,##0.000000  */
	public CellStyle moneyCP6;
	
	
	/** ￥0;[Red]￥-0  NC No comma 逗号 */
	public CellStyle moneyNCP0;
	/** ￥0.0;[Red]￥-0.0  */
	public CellStyle moneyNCP1;
	/** ￥0.00;[Red]￥-0.00  */
	public CellStyle moneyNCP2;
	/** ￥0.000;[Red]￥-0.000  */
	public CellStyle moneyNCP3;
	/** ￥0.0000;[Red]￥-0.0000  */
	public CellStyle moneyNCP4;
	/** ￥0.00000;[Red]￥-0.00000  */
	public CellStyle moneyNCP5;
	/** ￥0.000000;[Red]￥-0.000000  */
	public CellStyle moneyNCP6;
	
	
	/** yyyy/m/d ,options: DateDelimiter, ZeroPaddingDate,  TimeDelimiter. */
	public CellStyle date;
	/** yyyy/mm/dd ZeroPaddingDate */
	public CellStyle dateZF;
	/** yyyy/m/d Not ZeroPaddingDate */
	public CellStyle dateNZF;
	
	/** yyyy-m-d ,transverse line  横线 */
	public CellStyle dateTL;
	/** yyyy-mm-dd ZeroPaddingDate */
	public CellStyle dateTLZF;
	/** yyyy-m-d Not ZeroPaddingDate */
	public CellStyle dateTLNZF;
	
	
	/** h:mm:ss Whenever time's minute and second is ZeroFilled*/
	public CellStyle time;
	/** hh:mm:ss Hour ZeroFilled */
	public CellStyle timeZF;
	/** h:mm:ss Hour Not ZeroFilled*/
	public CellStyle timeNZF;
	
	
	/** yyyy/m/d h:mm:ss Not ZeroPaddingDate  */
	public CellStyle datetime;
	/** yyyy/mm/dd hh:mm:ss ZeroPaddingDate*/
	public CellStyle datetimeZF;
	/** yyyy/m/d h:mm:ss Not ZeroPaddingDate */
	public CellStyle datetimeNZF;
	
	/** yyyy-m-d h:mm:ss Not ZeroPaddingDate. transverse line  横线  */
	public CellStyle datetimeTL;
	/** yyyy-mm-dd hh:mm:ss ZeroPaddingDate*/
	public CellStyle datetimeTLZF;
	/** yyyy-m-d h:mm:ss Not ZeroPaddingDate */
	public CellStyle datetimeTLNZF;
	
	
	/** 百分比  0.00%;[Red]-0.00%*/
	public CellStyle rate;
	/** 百分比  0%;[Red]-0%*/
	public CellStyle rateP0;
	/** 百分比  0.0%;[Red]-0.0%*/
	public CellStyle rateP1;
	/** 百分比  0.00%;[Red]-0.00%*/
	public CellStyle rateP2;
	/** 百分比  0.000000000%;[Red]-0.000000000%*/
	public CellStyle rateP9;
	
	/** 分数 */
	public CellStyle fraction;
	
	public POICellStyle(Workbook wb){
		this.wb = wb;
	}
	
	public Workbook getWb() {
		return wb;
	}

	public void setWb(Workbook wb) {
		this.wb = wb;
	}

	public CellStyle getTextHeader() {
		return textHeader;
	}

	public void setTextHeader(CellStyle textHeader) {
		this.textHeader = textHeader;
	}

	public CellStyle getText() {
		return text;
	}

	public void setText(CellStyle text) {
		this.text = text;
	}

	public CellStyle getTextBB() {
		return textBB;
	}

	public void setTextBB(CellStyle textBB) {
		this.textBB = textBB;
	}

	public CellStyle getNumberCP0() {
		return numberCP0;
	}

	public void setNumberCP0(CellStyle numberCP0) {
		this.numberCP0 = numberCP0;
	}

	public CellStyle getNumberCP1() {
		return numberCP1;
	}

	public void setNumberCP1(CellStyle numberCP1) {
		this.numberCP1 = numberCP1;
	}

	public CellStyle getNumberCP2() {
		return numberCP2;
	}

	public void setNumberCP2(CellStyle numberCP2) {
		this.numberCP2 = numberCP2;
	}

	public CellStyle getNumberCP3() {
		return numberCP3;
	}

	public void setNumberCP3(CellStyle numberCP3) {
		this.numberCP3 = numberCP3;
	}

	public CellStyle getNumberCP4() {
		return numberCP4;
	}

	public void setNumberCP4(CellStyle numberCP4) {
		this.numberCP4 = numberCP4;
	}

	public CellStyle getNumberCP5() {
		return numberCP5;
	}

	public void setNumberCP5(CellStyle numberCP5) {
		this.numberCP5 = numberCP5;
	}

	public CellStyle getNumberCP6() {
		return numberCP6;
	}

	public void setNumberCP6(CellStyle numberCP6) {
		this.numberCP6 = numberCP6;
	}

	public CellStyle getNumberNCP0() {
		return numberNCP0;
	}

	public void setNumberNCP0(CellStyle numberNCP0) {
		this.numberNCP0 = numberNCP0;
	}

	public CellStyle getNumberNCP1() {
		return numberNCP1;
	}

	public void setNumberNCP1(CellStyle numberNCP1) {
		this.numberNCP1 = numberNCP1;
	}

	public CellStyle getNumberNCP2() {
		return numberNCP2;
	}

	public void setNumberNCP2(CellStyle numberNCP2) {
		this.numberNCP2 = numberNCP2;
	}

	public CellStyle getNumberNCP3() {
		return numberNCP3;
	}

	public void setNumberNCP3(CellStyle numberNCP3) {
		this.numberNCP3 = numberNCP3;
	}

	public CellStyle getNumberNCP4() {
		return numberNCP4;
	}

	public void setNumberNCP4(CellStyle numberNCP4) {
		this.numberNCP4 = numberNCP4;
	}

	public CellStyle getNumberNCP5() {
		return numberNCP5;
	}

	public void setNumberNCP5(CellStyle numberNCP5) {
		this.numberNCP5 = numberNCP5;
	}

	public CellStyle getNumberNCP6() {
		return numberNCP6;
	}

	public void setNumberNCP6(CellStyle numberNCP6) {
		this.numberNCP6 = numberNCP6;
	}

	public CellStyle getMoneyCP0() {
		return moneyCP0;
	}

	public void setMoneyCP0(CellStyle moneyCP0) {
		this.moneyCP0 = moneyCP0;
	}

	public CellStyle getMoneyCP1() {
		return moneyCP1;
	}

	public void setMoneyCP1(CellStyle moneyCP1) {
		this.moneyCP1 = moneyCP1;
	}

	public CellStyle getMoneyCP2() {
		return moneyCP2;
	}

	public void setMoneyCP2(CellStyle moneyCP2) {
		this.moneyCP2 = moneyCP2;
	}

	public CellStyle getMoneyCP3() {
		return moneyCP3;
	}

	public void setMoneyCP3(CellStyle moneyCP3) {
		this.moneyCP3 = moneyCP3;
	}

	public CellStyle getMoneyCP4() {
		return moneyCP4;
	}

	public void setMoneyCP4(CellStyle moneyCP4) {
		this.moneyCP4 = moneyCP4;
	}

	public CellStyle getMoneyCP5() {
		return moneyCP5;
	}

	public void setMoneyCP5(CellStyle moneyCP5) {
		this.moneyCP5 = moneyCP5;
	}

	public CellStyle getMoneyCP6() {
		return moneyCP6;
	}

	public void setMoneyCP6(CellStyle moneyCP6) {
		this.moneyCP6 = moneyCP6;
	}

	public CellStyle getMoneyNCP0() {
		return moneyNCP0;
	}

	public void setMoneyNCP0(CellStyle moneyNCP0) {
		this.moneyNCP0 = moneyNCP0;
	}

	public CellStyle getMoneyNCP1() {
		return moneyNCP1;
	}

	public void setMoneyNCP1(CellStyle moneyNCP1) {
		this.moneyNCP1 = moneyNCP1;
	}

	public CellStyle getMoneyNCP2() {
		return moneyNCP2;
	}

	public void setMoneyNCP2(CellStyle moneyNCP2) {
		this.moneyNCP2 = moneyNCP2;
	}

	public CellStyle getMoneyNCP3() {
		return moneyNCP3;
	}

	public void setMoneyNCP3(CellStyle moneyNCP3) {
		this.moneyNCP3 = moneyNCP3;
	}

	public CellStyle getMoneyNCP4() {
		return moneyNCP4;
	}

	public void setMoneyNCP4(CellStyle moneyNCP4) {
		this.moneyNCP4 = moneyNCP4;
	}

	public CellStyle getMoneyNCP5() {
		return moneyNCP5;
	}

	public void setMoneyNCP5(CellStyle moneyNCP5) {
		this.moneyNCP5 = moneyNCP5;
	}

	public CellStyle getMoneyNCP6() {
		return moneyNCP6;
	}

	public void setMoneyNCP6(CellStyle moneyNCP6) {
		this.moneyNCP6 = moneyNCP6;
	}

	public CellStyle getDate() {
		return date;
	}

	public void setDate(CellStyle date) {
		this.date = date;
	}

	public CellStyle getDateZF() {
		return dateZF;
	}

	public void setDateZF(CellStyle dateZF) {
		this.dateZF = dateZF;
	}

	public CellStyle getDateNZF() {
		return dateNZF;
	}

	public void setDateNZF(CellStyle dateNZF) {
		this.dateNZF = dateNZF;
	}

	public CellStyle getDateTL() {
		return dateTL;
	}

	public void setDateTL(CellStyle dateTL) {
		this.dateTL = dateTL;
	}

	public CellStyle getDateTLZF() {
		return dateTLZF;
	}

	public void setDateTLZF(CellStyle dateTLZF) {
		this.dateTLZF = dateTLZF;
	}

	public CellStyle getDateTLNZF() {
		return dateTLNZF;
	}

	public void setDateTLNZF(CellStyle dateTLNZF) {
		this.dateTLNZF = dateTLNZF;
	}

	public CellStyle getTime() {
		return time;
	}

	public void setTime(CellStyle time) {
		this.time = time;
	}

	public CellStyle getTimeZF() {
		return timeZF;
	}

	public void setTimeZF(CellStyle timeZF) {
		this.timeZF = timeZF;
	}

	public CellStyle getTimeNZF() {
		return timeNZF;
	}

	public void setTimeNZF(CellStyle timeNZF) {
		this.timeNZF = timeNZF;
	}

	public CellStyle getDatetime() {
		return datetime;
	}

	public void setDatetime(CellStyle datetime) {
		this.datetime = datetime;
	}

	public CellStyle getDatetimeZF() {
		return datetimeZF;
	}

	public void setDatetimeZF(CellStyle datetimeZF) {
		this.datetimeZF = datetimeZF;
	}

	public CellStyle getDatetimeNZF() {
		return datetimeNZF;
	}

	public void setDatetimeNZF(
			CellStyle datetimeNZF) {
		this.datetimeNZF = datetimeNZF;
	}

	public CellStyle getDatetimeTL() {
		return datetimeTL;
	}

	public void setDatetimeTL(CellStyle datetimeTL) {
		this.datetimeTL = datetimeTL;
	}

	public CellStyle getDatetimeTLZF() {
		return datetimeTLZF;
	}

	public void setDatetimeTLZF(
			CellStyle datetimeTLZF) {
		this.datetimeTLZF = datetimeTLZF;
	}

	public CellStyle getDatetimeTLNZF() {
		return datetimeTLNZF;
	}

	public void setDatetimeTLNZF(
			CellStyle datetimeTLNZF) {
		this.datetimeTLNZF = datetimeTLNZF;
	}

	public CellStyle getRate() {
		return rate;
	}

	public void setRate(CellStyle rate) {
		this.rate = rate;
	}

	public CellStyle getRateP0() {
		return rateP0;
	}

	public void setRateP0(CellStyle rateP0) {
		this.rateP0 = rateP0;
	}

	public CellStyle getRateP1() {
		return rateP1;
	}

	public void setRateP1(CellStyle rateP1) {
		this.rateP1 = rateP1;
	}

	public CellStyle getRateP2() {
		return rateP2;
	}

	public void setRateP2(CellStyle rateP2) {
		this.rateP2 = rateP2;
	}
	public CellStyle getRateP10() {
		return rateP9;
	}

	public void setRateP9(CellStyle rateP9) {
		this.rateP9 = rateP9;
	}

	public CellStyle getFraction() {
		return fraction;
	}

	public void setFraction(CellStyle fraction) {
		this.fraction = fraction;
	}

	/**
	 * initialize needs style
	 * <p>解决下面的问题:
	 * <br>&nbsp;文件错误，可能某些数字格式已丢失
	 * @param columnTypes
	 * @param scales number of digits to right of the decimal point
	 */
	public void initializeRequiredStyle( int columnTypes, int scales ){
		switch(columnTypes){
		case Types.VARCHAR: //12
		case 1:
		case -9:
		case -15:
		case -1:
			text = wb.createCellStyle();
			text.setDataFormat( wb.createDataFormat().getFormat("text"));
			break;
		case Types.NUMERIC: //2
		case 3: 
		case 4: 
		case 5: 
		case 7:
		case 8:
		case -5:
		case -6:
		case 100:  	
		case 101:
			switch(scales){
			case 0: 
				numberNCP0 = wb.createCellStyle();
				numberNCP0.setDataFormat( wb.createDataFormat().getFormat("0;[Red]-0"));
				break;
			case 2: 
				numberNCP2 = wb.createCellStyle();
				numberNCP2.setDataFormat( wb.createDataFormat().getFormat("0.00;[Red]-0.00"));
				break;
			case 1: 
				numberNCP1 = wb.createCellStyle();
				numberNCP1.setDataFormat( wb.createDataFormat().getFormat("0.0;[Red]-0.0"));
				break;
			case 3:
				numberNCP3 = wb.createCellStyle();
				numberNCP3.setDataFormat( wb.createDataFormat().getFormat("0.000;[Red]-0.000"));
				break;
			case 4: 
				numberNCP4 = wb.createCellStyle();
				numberNCP4.setDataFormat( wb.createDataFormat().getFormat("0.0000;[Red]-0.0000"));
				break;
			case 5:
				numberNCP5 = wb.createCellStyle();
				numberNCP5.setDataFormat( wb.createDataFormat().getFormat("0.00000;[Red]-0.00000"));
				break;
			case 6: 
				numberNCP6 = wb.createCellStyle();
				numberNCP6.setDataFormat( wb.createDataFormat().getFormat("0.000000;[Red]-0.000000"));
				break;
			case 9: 
				rateP9 = wb.createCellStyle();
				rateP9.setDataFormat( wb.createDataFormat().getFormat("0.000000000%;[Red]-0.000000000%"));
				break;
			case 10: 
				numberNCP10 = wb.createCellStyle();
				numberNCP10.setDataFormat( wb.createDataFormat().getFormat("0.0000000000;[Red]-0.0000000000"));
				break;
			default:
				numberNCP2 = wb.createCellStyle();
				numberNCP2.setDataFormat( wb.createDataFormat().getFormat("0.00;[Red]-0.00"));
			}
			break;
		case Types.DATE://91
		case Types.TIMESTAMP: //93
			datetimeZF = wb.createCellStyle();
			datetimeZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
			break;
		default : System.out.println("没有对应的列类型！");
		}
	}
	/**
	 * initialize needs style
	 * <p>解决下面的问题:
	 * <br>&nbsp;文件错误，可能某些数字格式已丢失
	 * @param columnTypes
	 * @param scales number of digits to right of the decimal point
	 */
	public void initializeRequiredStyle( int[] columnTypes, int[] scales ){
		 //set default column style, create表头-无序号列
		for (int i = 0, j = columnTypes.length; i < j; i ++) 
		{
			switch(columnTypes[i]){
			case Types.VARCHAR: //12
			case 1:
			case -9:
			case -15:
			case -1:
				text = wb.createCellStyle();
				text.setDataFormat( wb.createDataFormat().getFormat("text"));
				break;
			case Types.NUMERIC: //2
			case 3: 
			case 4: 
			case 5: 
			case 7:
			case 8:
			case -5:
			case -6:
			case 100:  	
			case 101:
				switch(scales[i]){
				case 0: 
					numberNCP0 = wb.createCellStyle();
					numberNCP0.setDataFormat( wb.createDataFormat().getFormat("0;[Red]-0"));
					break;
				case 2: 
					numberNCP2 = wb.createCellStyle();
					numberNCP2.setDataFormat( wb.createDataFormat().getFormat("0.00;[Red]-0.00"));
					break;
				case 1: 
					numberNCP1 = wb.createCellStyle();
					numberNCP1.setDataFormat( wb.createDataFormat().getFormat("0.0;[Red]-0.0"));
					break;
				case 3:
					numberNCP3 = wb.createCellStyle();
					numberNCP3.setDataFormat( wb.createDataFormat().getFormat("0.000;[Red]-0.000"));
					break;
				case 4: 
					numberNCP4 = wb.createCellStyle();
					numberNCP4.setDataFormat( wb.createDataFormat().getFormat("0.0000;[Red]-0.0000"));
					break;
				case 5:
					numberNCP5 = wb.createCellStyle();
					numberNCP5.setDataFormat( wb.createDataFormat().getFormat("0.00000;[Red]-0.00000"));
					break;
				case 6: 
					numberNCP6 = wb.createCellStyle();
					numberNCP6.setDataFormat( wb.createDataFormat().getFormat("0.000000;[Red]-0.000000"));
					break;
				case 9: 
					rateP9 = wb.createCellStyle();
					rateP9.setDataFormat( wb.createDataFormat().getFormat("0.000000000%;[Red]-0.000000000%"));
					break;
				case 10: 
					numberNCP10 = wb.createCellStyle();
					numberNCP10.setDataFormat( wb.createDataFormat().getFormat("0.0000000000;[Red]-0.0000000000"));
					break;
				default:
					numberNCP2 = wb.createCellStyle();
					numberNCP2.setDataFormat( wb.createDataFormat().getFormat("0.00;[Red]-0.00"));
				}
				break;
			case Types.DATE://91
			case Types.TIMESTAMP: //93
				datetimeZF = wb.createCellStyle();
				datetimeZF.setDataFormat( wb.createDataFormat().getFormat("yyyy/mm/dd hh:mm:ss"));
				break;
			default : System.out.println("没有对应的列类型！");
			}
		}
	}
		
		
	public static void main(String args[]) {
				
		Workbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
		Sheet sheet1 = wb.createSheet("缴款书表单1");
//	    Sheet sheet2 = wb.createSheet("second sheet");
	    sheet1.createFreezePane(0, 1, 0, 1);
	    HSSFRow row = null;
	    HSSFCell cell = null;
		CellStyle cs1 = wb.createCellStyle();
	    CellStyle cs_text = wb.createCellStyle();
	    CellStyle cs_date = wb.createCellStyle();
	    DataFormat df = wb.createDataFormat();

	    // create 2 fonts objects
	    org.apache.poi.ss.usermodel.Font f1 = wb.createFont();
	    org.apache.poi.ss.usermodel.Font f2 = wb.createFont();

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
