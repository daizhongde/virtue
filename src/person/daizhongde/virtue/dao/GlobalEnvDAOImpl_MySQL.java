package person.daizhongde.virtue.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author dzd
 *
 */
public class GlobalEnvDAOImpl_MySQL extends SpringHibernateDaoSupport implements GlobalEnvDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GlobalEnvDAOImpl_MySQL.class);

	protected void initDao() {
		// do nothing
	}

	public String systemVersion;//当前的系统版本号
	public String systemDBDate;// 当前的数据库日期
	public String systemDBDateW;// 当前的数据库日期 带周
	public String systemDBTime;// 当前的数据库时间
	
	GlobalEnvDAOImpl_MySQL() {

	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}
	
	public String getSystemDBDate() {
		log.debug("getSystemDBDate");
		try {
			//mysql
			String queryString = "select DATE_FORMAT(curdate(),'%Y-%m-%d');";
			//oracle 
//			String queryString = "select TO_CHAR(SYSDATE,'YYYY/mm/dd') \"date\" from dual";
			return super.sqlQueryfindaValue(queryString).toString();
		} catch (RuntimeException re) {
			log.error("getSystemDBDate failed", re);
			throw re;
		}
	}
	/**
	 * %H:%i:%S
	 */
	public String getSystemDBDate( String fmt ) {
		log.debug("getSystemDBDate");
		try {
			//mysql
			String queryString = "select DATE_FORMAT(now(),'"+fmt+"');";
			//oracle 
//			String queryString = "select TO_CHAR(SYSDATE,'"+fmt+"') \"date\" from dual";
			return super.sqlQueryfindaValue(queryString).toString();
		} catch (RuntimeException re) {
			log.error("getSystemDBDate failed", re);
			throw re;
		}
	}
	
	public String getSystemDBTime() {
		log.debug("getSystemDBTime");
		try {
			//mysql
			String queryString = "select now();";
			//oracle  
//			String queryString = "select TO_CHAR(SYSDATE, 'YYYY/MM/DD HH24:MI:SS') \"datetime\" from dual";
			return super.sqlQueryfindaValue(queryString).toString();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public String getSystemDBDateW() {
		log.debug("getSystemDBDateW");
		try {
			//mysql
			String queryString = "select DATE_FORMAT(curdate(),'%Y年%m月%d日 %W');";
			//oracle  
			//select TO_CHAR(SYSDATE,'YYYY"年"mm"月"dd"日" day') "date" from dual 
//			String queryString = "select TO_CHAR(SYSDATE,'YYYY\"年\"mm\"月\"dd\"日\" day') \"date\" from dual";
			return super.sqlQueryfindaValue(queryString).toString();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 取数据库时间，带星期几
	 * @return
	 */
	public String getSystemDBTimeWithWeek() {
		log.debug("getSystemDBTimeWithWeek");
		try {
			//mysql
			String queryString = "select now();";
			//oracle  
//			String queryString = "select TO_CHAR(SYSDATE,'YYYY\"年\"mm\"月\"dd\"日\" day HH24:MI:SS') \"date\" from dual";
			return super.sqlQueryfindaValue(queryString).toString();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}
