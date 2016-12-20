package person.daizhongde.virtue.spring;

import java.util.List;
import java.util.Map;

public interface BaseExportService {

	/**
	 * 生成excel,并返回生成文件的服务器路径
	 * @param jdata
	 * @param _
	 * @return
	 * @throws Exception
	 */
	public abstract String exportXLS(String jdata, String _ ) throws Exception;
	public abstract String exportXLS(String jdata, String _, List columnNames) throws Exception;
	public abstract String exportXLS(String jdata, String _, Map options ) throws Exception;
	public abstract String exportXLS(String jdata, String _, List columnNames, Map options) throws Exception;
	
	public abstract String exportXLS1(String jdata, String _, List columnNames, Map options) throws Exception;
	public abstract String exportXLS2(String jdata, String _, List columnNames, Map options) throws Exception;
	public abstract String exportXLS3(String jdata, String _, List columnNames, Map options) throws Exception;
	public abstract String exportXLS4(String jdata, String _, List columnNames, Map options) throws Exception;
	public abstract String exportXLS5(String jdata, String _, List columnNames, Map options) throws Exception;
	public abstract String exportXLS6(String jdata, String _, List columnNames, Map options) throws Exception;
	
	/**
	 * 生成txt,并返回生成文件的服务器路径
	 * @param jdata
	 * @param _
	 * @return
	 * @throws Exception
	 */
	public abstract String exportTXT(String jdata, String _) throws Exception;
	public abstract String exportTXT(String jdata, String _, List columnNames ) throws Exception;
	public abstract String exportTXT(String jdata, String _, Map options ) throws Exception;
	public abstract String exportTXT(String jdata, String _, List columnNames, Map options ) throws Exception;
	
	public abstract String exportTXT1(String jdata, String _, List columnNames, Map options ) throws Exception;
	public abstract String exportTXT2(String jdata, String _, List columnNames, Map options ) throws Exception;
	public abstract String exportTXT3(String jdata, String _, List columnNames, Map options ) throws Exception;
	public abstract String exportTXT4(String jdata, String _, List columnNames, Map options ) throws Exception;
	public abstract String exportTXT5(String jdata, String _, List columnNames, Map options ) throws Exception;
	public abstract String exportTXT6(String jdata, String _, List columnNames, Map options ) throws Exception;
}
