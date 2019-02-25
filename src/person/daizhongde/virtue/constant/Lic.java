package person.daizhongde.virtue.constant;

import java.text.SimpleDateFormat;
import java.util.Date;

import person.daizhongde.virtue.codec.SHA1_Encoding;
import person.daizhongde.virtue.configutils.ConfigReader_PROP;
import person.daizhongde.virtue.sigar.ComputerIdentifier;

/**
 * lic reader
 * 
 * @author dzd
 *
 */
public class Lic {
	private final static String CFName = "dandflow.lic";
	
	private final static String lic = ConfigReader_PROP.findProperty(CFName, "lic");
	private static int year = 0;
	static {
		String identifier="";
		try {
			identifier = ComputerIdentifier.generateLicenseKey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String yr = (new SimpleDateFormat("yyyy")).format(new Date());
		int n = Integer.valueOf(yr);

		int n_1 = n-1;
		int n_2 = n-2;
		int n_3 = n-3;
		int n_4 = n-4;
		int n_5 = n-5;
		
		int n1 = n+1;
		int n2 = n+2;
		int n3 = n+3;
		int n4 = n+4;
		int n5 = n+5;
		
		StringBuffer sb_1 = new StringBuffer(String.valueOf(n_1));
		sb_1 = sb_1.reverse();
		String y_1=sb_1.toString();
		
		StringBuffer sb_2 = new StringBuffer(String.valueOf(n_2));
		sb_2 = sb_2.reverse();
		String y_2=sb_2.toString();
		
		StringBuffer sb_3 = new StringBuffer(String.valueOf(n_3));
		sb_3 = sb_3.reverse();
		String y_3=sb_3.toString();
		
		StringBuffer sb_4 = new StringBuffer(String.valueOf(n_4));
		sb_4 = sb_4.reverse();
		String y_4=sb_4.toString();
		
		StringBuffer sb_5 = new StringBuffer(String.valueOf(n_5));
		sb_5 = sb_5.reverse();
		String y_5=sb_5.toString();
		
		//##################################################
		StringBuffer sb = new StringBuffer(yr);
		sb = sb.reverse();
		String y=sb.toString();
		
		StringBuffer sb1 = new StringBuffer(String.valueOf(n1));
		sb1 = sb1.reverse();
		String y1=sb1.toString();
		
		StringBuffer sb2 = new StringBuffer(String.valueOf(n2));
		sb2 = sb2.reverse();
		String y2=sb2.toString();
		
		StringBuffer sb3 = new StringBuffer(String.valueOf(n3));
		sb3 = sb3.reverse();
		String y3=sb3.toString();
		
		StringBuffer sb4 = new StringBuffer(String.valueOf(n4));
		sb4 = sb4.reverse();
		String y4=sb4.toString();
		
		StringBuffer sb5 = new StringBuffer(String.valueOf(n5));
		sb5 = sb5.reverse();
		String y5=sb5.toString();
		
		if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_1)).equalsIgnoreCase(lic) ){
			year=n_1;
		}else if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_2)).equalsIgnoreCase(lic) ){
			year=n_2;
		}else  if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_3)).equalsIgnoreCase(lic) ){
			year=n_3;
		}else  if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_4)).equalsIgnoreCase(lic) ){
			year=n_4;
		}else  if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_5)).equalsIgnoreCase(lic) ){
			year=n_5;
		}else  if( (SHA1_Encoding.toSHA1(identifier+"8201"+y)).equalsIgnoreCase(lic) ){
			year=n;
		}else  if( (SHA1_Encoding.toSHA1(identifier+"8201"+y_1)).equalsIgnoreCase(lic) ){
			year=n1;
		}else if((SHA1_Encoding.toSHA1(identifier+"8201"+y_2)).equalsIgnoreCase(lic) ){
			year=n2;
		}else if((SHA1_Encoding.toSHA1(identifier+"8201"+y_3)).equalsIgnoreCase(lic) ){
			year=n3;
		}else if((SHA1_Encoding.toSHA1(identifier+"8201"+y_4)).equalsIgnoreCase(lic) ){
			year=n4;
		}else if((SHA1_Encoding.toSHA1(identifier+"8201"+y_5)).equalsIgnoreCase(lic) ){
			year=n5;
		}else{
			throw new RuntimeException("请购买正版license!QQ:413881461;公众号：德软集团");
		}
		
	}
	
    public static int getYear() {
		return year;
	}

	// Prevent instantiation
    private Lic() {}
    
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\").getName():"+Lic.class.getField("TAuthorityModule_query").getName());
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\"):"+Lic.class.getField("TAuthorityModule_query"));

		
	}
}