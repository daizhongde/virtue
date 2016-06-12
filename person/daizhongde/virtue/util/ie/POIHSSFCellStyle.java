package person.daizhongde.virtue.util.ie;

import java.sql.Types;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * Create style auto by need
 * <p>
 * scale=9,用百分比表示，小数点后精度为10用小数表示
 * @author daizd
 *
 */
public class POIHSSFCellStyle {
	//org.apache.poi.hssf.usermodel.HSSFCellStyle
	//org.apache.poi.hssf.usermodel.HSSFCellStyle
	
	private HSSFWorkbook wb;
	
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
	/** #,##0.00000;[Red]-#,##0.000000  */
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
	/** 百分比  0.000000000%;[Red]-0.000000000%*/
	public org.apache.poi.hssf.usermodel.HSSFCellStyle rateP9;
	
	/** 分数 */
	public org.apache.poi.hssf.usermodel.HSSFCellStyle fraction;
	
	public POIHSSFCellStyle(HSSFWorkbook wb){
		this.wb = wb;
	}
	
	public HSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(HSSFWorkbook wb) {
		this.wb = wb;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getTextHeader() {
		return textHeader;
	}

	public void setTextHeader(org.apache.poi.hssf.usermodel.HSSFCellStyle textHeader) {
		this.textHeader = textHeader;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getText() {
		return text;
	}

	public void setText(org.apache.poi.hssf.usermodel.HSSFCellStyle text) {
		this.text = text;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getTextBB() {
		return textBB;
	}

	public void setTextBB(org.apache.poi.hssf.usermodel.HSSFCellStyle textBB) {
		this.textBB = textBB;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP0() {
		return numberCP0;
	}

	public void setNumberCP0(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP0) {
		this.numberCP0 = numberCP0;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP1() {
		return numberCP1;
	}

	public void setNumberCP1(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP1) {
		this.numberCP1 = numberCP1;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP2() {
		return numberCP2;
	}

	public void setNumberCP2(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP2) {
		this.numberCP2 = numberCP2;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP3() {
		return numberCP3;
	}

	public void setNumberCP3(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP3) {
		this.numberCP3 = numberCP3;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP4() {
		return numberCP4;
	}

	public void setNumberCP4(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP4) {
		this.numberCP4 = numberCP4;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP5() {
		return numberCP5;
	}

	public void setNumberCP5(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP5) {
		this.numberCP5 = numberCP5;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberCP6() {
		return numberCP6;
	}

	public void setNumberCP6(org.apache.poi.hssf.usermodel.HSSFCellStyle numberCP6) {
		this.numberCP6 = numberCP6;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP0() {
		return numberNCP0;
	}

	public void setNumberNCP0(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP0) {
		this.numberNCP0 = numberNCP0;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP1() {
		return numberNCP1;
	}

	public void setNumberNCP1(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP1) {
		this.numberNCP1 = numberNCP1;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP2() {
		return numberNCP2;
	}

	public void setNumberNCP2(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP2) {
		this.numberNCP2 = numberNCP2;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP3() {
		return numberNCP3;
	}

	public void setNumberNCP3(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP3) {
		this.numberNCP3 = numberNCP3;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP4() {
		return numberNCP4;
	}

	public void setNumberNCP4(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP4) {
		this.numberNCP4 = numberNCP4;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP5() {
		return numberNCP5;
	}

	public void setNumberNCP5(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP5) {
		this.numberNCP5 = numberNCP5;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getNumberNCP6() {
		return numberNCP6;
	}

	public void setNumberNCP6(org.apache.poi.hssf.usermodel.HSSFCellStyle numberNCP6) {
		this.numberNCP6 = numberNCP6;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP0() {
		return moneyCP0;
	}

	public void setMoneyCP0(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP0) {
		this.moneyCP0 = moneyCP0;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP1() {
		return moneyCP1;
	}

	public void setMoneyCP1(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP1) {
		this.moneyCP1 = moneyCP1;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP2() {
		return moneyCP2;
	}

	public void setMoneyCP2(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP2) {
		this.moneyCP2 = moneyCP2;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP3() {
		return moneyCP3;
	}

	public void setMoneyCP3(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP3) {
		this.moneyCP3 = moneyCP3;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP4() {
		return moneyCP4;
	}

	public void setMoneyCP4(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP4) {
		this.moneyCP4 = moneyCP4;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP5() {
		return moneyCP5;
	}

	public void setMoneyCP5(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP5) {
		this.moneyCP5 = moneyCP5;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyCP6() {
		return moneyCP6;
	}

	public void setMoneyCP6(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyCP6) {
		this.moneyCP6 = moneyCP6;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP0() {
		return moneyNCP0;
	}

	public void setMoneyNCP0(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP0) {
		this.moneyNCP0 = moneyNCP0;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP1() {
		return moneyNCP1;
	}

	public void setMoneyNCP1(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP1) {
		this.moneyNCP1 = moneyNCP1;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP2() {
		return moneyNCP2;
	}

	public void setMoneyNCP2(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP2) {
		this.moneyNCP2 = moneyNCP2;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP3() {
		return moneyNCP3;
	}

	public void setMoneyNCP3(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP3) {
		this.moneyNCP3 = moneyNCP3;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP4() {
		return moneyNCP4;
	}

	public void setMoneyNCP4(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP4) {
		this.moneyNCP4 = moneyNCP4;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP5() {
		return moneyNCP5;
	}

	public void setMoneyNCP5(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP5) {
		this.moneyNCP5 = moneyNCP5;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getMoneyNCP6() {
		return moneyNCP6;
	}

	public void setMoneyNCP6(org.apache.poi.hssf.usermodel.HSSFCellStyle moneyNCP6) {
		this.moneyNCP6 = moneyNCP6;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDate() {
		return date;
	}

	public void setDate(org.apache.poi.hssf.usermodel.HSSFCellStyle date) {
		this.date = date;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDateZF() {
		return dateZF;
	}

	public void setDateZF(org.apache.poi.hssf.usermodel.HSSFCellStyle dateZF) {
		this.dateZF = dateZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDateNZF() {
		return dateNZF;
	}

	public void setDateNZF(org.apache.poi.hssf.usermodel.HSSFCellStyle dateNZF) {
		this.dateNZF = dateNZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDateTL() {
		return dateTL;
	}

	public void setDateTL(org.apache.poi.hssf.usermodel.HSSFCellStyle dateTL) {
		this.dateTL = dateTL;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDateTLZF() {
		return dateTLZF;
	}

	public void setDateTLZF(org.apache.poi.hssf.usermodel.HSSFCellStyle dateTLZF) {
		this.dateTLZF = dateTLZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDateTLNZF() {
		return dateTLNZF;
	}

	public void setDateTLNZF(org.apache.poi.hssf.usermodel.HSSFCellStyle dateTLNZF) {
		this.dateTLNZF = dateTLNZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getTime() {
		return time;
	}

	public void setTime(org.apache.poi.hssf.usermodel.HSSFCellStyle time) {
		this.time = time;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getTimeZF() {
		return timeZF;
	}

	public void setTimeZF(org.apache.poi.hssf.usermodel.HSSFCellStyle timeZF) {
		this.timeZF = timeZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getTimeNZF() {
		return timeNZF;
	}

	public void setTimeNZF(org.apache.poi.hssf.usermodel.HSSFCellStyle timeNZF) {
		this.timeNZF = timeNZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetime() {
		return datetime;
	}

	public void setDatetime(org.apache.poi.hssf.usermodel.HSSFCellStyle datetime) {
		this.datetime = datetime;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetimeZF() {
		return datetimeZF;
	}

	public void setDatetimeZF(org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeZF) {
		this.datetimeZF = datetimeZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetimeNZF() {
		return datetimeNZF;
	}

	public void setDatetimeNZF(
			org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeNZF) {
		this.datetimeNZF = datetimeNZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetimeTL() {
		return datetimeTL;
	}

	public void setDatetimeTL(org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTL) {
		this.datetimeTL = datetimeTL;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetimeTLZF() {
		return datetimeTLZF;
	}

	public void setDatetimeTLZF(
			org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTLZF) {
		this.datetimeTLZF = datetimeTLZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getDatetimeTLNZF() {
		return datetimeTLNZF;
	}

	public void setDatetimeTLNZF(
			org.apache.poi.hssf.usermodel.HSSFCellStyle datetimeTLNZF) {
		this.datetimeTLNZF = datetimeTLNZF;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getRate() {
		return rate;
	}

	public void setRate(org.apache.poi.hssf.usermodel.HSSFCellStyle rate) {
		this.rate = rate;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getRateP0() {
		return rateP0;
	}

	public void setRateP0(org.apache.poi.hssf.usermodel.HSSFCellStyle rateP0) {
		this.rateP0 = rateP0;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getRateP1() {
		return rateP1;
	}

	public void setRateP1(org.apache.poi.hssf.usermodel.HSSFCellStyle rateP1) {
		this.rateP1 = rateP1;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getRateP2() {
		return rateP2;
	}

	public void setRateP2(org.apache.poi.hssf.usermodel.HSSFCellStyle rateP2) {
		this.rateP2 = rateP2;
	}
	public org.apache.poi.hssf.usermodel.HSSFCellStyle getRateP10() {
		return rateP9;
	}

	public void setRateP9(org.apache.poi.hssf.usermodel.HSSFCellStyle rateP9) {
		this.rateP9 = rateP9;
	}

	public org.apache.poi.hssf.usermodel.HSSFCellStyle getFraction() {
		return fraction;
	}

	public void setFraction(org.apache.poi.hssf.usermodel.HSSFCellStyle fraction) {
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
				
		HSSFWorkbook wb = new HSSFWorkbook();
	    //Workbook wb = new XSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("缴款书表单1");
//	    Sheet sheet2 = wb.createSheet("second sheet");
	    sheet1.createFreezePane(0, 1, 0, 1);
	    HSSFRow row = null;
	    HSSFCell cell = null;
		org.apache.poi.hssf.usermodel.HSSFCellStyle cs1 = wb.createCellStyle();
	    org.apache.poi.hssf.usermodel.HSSFCellStyle cs_text = wb.createCellStyle();
	    org.apache.poi.hssf.usermodel.HSSFCellStyle cs_date = wb.createCellStyle();
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
