package person.daizhongde.virtue.struts2.converter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;

/**
 * Export Options, struts2 type converter
 * <p>data format:
 * <br>String:<br> '{
		"IncludeColumnTitles" : true,
		"ContinueOnError" : false,
		"RecordDelimiter" : "CRLF",
		"FieldDelimiter" : "\t",
		"TextQualifier" : null,
		"DateOrder" : "YMD",
		"DateDelimiter" : "/",
		"ZeroPaddingDate" : false,
		"TimeDelimiter" : ":",
		"DecimalSymbol" : ".",
		"BinaryDataEncoding" : null
	}';
 * <br><br>--> Map:<br> {
		"IncludeColumnTitles" : true,
		"ContinueOnError" : false,
		"RecordDelimiter" : "CRLF",
		"FieldDelimiter" : "\t",
		"TextQualifier" : null,
		"DateOrder" : "YMD",
		"DateDelimiter" : "/",
		"ZeroPaddingDate" : false,
		"TimeDelimiter" : ":",
		"DecimalSymbol" : ".",
		"BinaryDataEncoding" : null
	}
 * @author dzd
 * @date 2013/9/30
 *
 */
public class TypeConverter_ExportOptions {

	private static Logger log = LogManager.getLogger(TypeConverter_ExportOptions.class.getName() );
	
	/**
	 * 如果request中没有相应的参数，这个方法就不会执行
	 */
	public Object convertFromString(Map context, String[] values, Class toClass) {
		System.out.println("-----I am TypeConverter_ExportOptions.class-----");
		Map options = null;
		if(values[0].trim().equalsIgnoreCase(""))
		{
			
		}
		else
		{
			System.out.println("values[0]:"+values[0]);
//			log.debug("encoded jdata:" + jdata.toString());
			String decode=null;
			try {
				decode = java.net.URLDecoder.decode(values[0], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			log.debug("#######");
			log.debug("decoded options:" + decode.toString());
//			log.debug("#######");
			
			options = JSONObject.fromObject(decode);
		}
		return options;
	}

	public String convertToString(Map context, Object o) {
		if (o instanceof HashMap )
		{
			JSONObject jsonObject = JSONObject.fromObject(o);
			return jsonObject.toString();
		}
		else
		{
			return "";
		}
	}

}
