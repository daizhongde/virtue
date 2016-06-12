package person.daizhongde.virtue.spring;

import java.util.Map;

public interface CommonService {
	/**
	 * get DB table names
	 * @return
	 */
	public abstract String getTableNames();
	public abstract Map getTableNames_Map();
}
