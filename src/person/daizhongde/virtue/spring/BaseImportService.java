package person.daizhongde.virtue.spring;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface BaseImportService {

	/**
	 * 导入excel
	 * @param _
	 * @return
	 * @throws Exception
	 */
	public abstract void importXLS(File file, String uploadContentType, String _ ) throws Exception;
	public abstract void importXLS(File file, String uploadContentType, String _, List columnNames) throws Exception;
	public abstract void importXLS(File file, String uploadContentType, String _, Map options ) throws Exception;
	public abstract void importXLS(File file, String uploadContentType, String _, List columnNames, Map options) throws Exception;
	
	/**
	 * 导入txt
	 * @param _
	 * @return
	 * @throws Exception
	 */
	public abstract void importTXT(File file, String _) throws Exception;
	public abstract void importTXT(File file, String _, List columnNames ) throws Exception;
	public abstract void importTXT(File file, String _, Map options ) throws Exception;
	public abstract void importTXT(File file, String _, List columnNames, Map options ) throws Exception;
}
