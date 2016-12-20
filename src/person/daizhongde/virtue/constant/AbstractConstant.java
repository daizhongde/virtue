package person.daizhongde.virtue.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.configutils.ConfigDocument_JS;
import person.daizhongde.virtue.configutils.ConfigDocument_SQL;

/**
 * the new field add in this class, getter method generate by this class
 * <br/>Date:20130518
 * <p/>add field nest_SQL
 * <br/>update 2013/10/25 ,by dai zhongde
 * @author dzd
 *
 */
public abstract class AbstractConstant {
	/* below fourteen field are get from sql file	 */
	private String tableName;
	private String pojoName;
	private String tableName_zh;

	private String primaryKeyColumnName;
	private String primaryKeySequence;
	
	private String EXPfileName;
	private String EXPsheetName;
	
	/** fixed column count range:0~columnCount, recomend 0~4,the column start at this first and obey the original order **/
	private int EXPfixColumnCount;

	/** whether office excel file's sheet autoSizeColumn ,if data hurge care and suggest set false **/
	private boolean EXPsheetAutoSizeColumn;
	
	/** if the export file compressed, value can be:  true,false **/
	private boolean EXPcompress;

	/** the max record size of single excel sheet , range :1~65534, one row remain for column label **/
	private int EXPsingleSheetRecordSize ;

	/** 
	 * the max record size of single excel file , the max size is depend on the one row data size, 150000 is recommend
	 * <br>singleFileRecordSize>=singleSheetRecordSize  **/
	private int EXPsingleFileRecordSize;
	
	private int EXPlevel1MinCount;
	private int EXPlevel2MinCount;
	private int EXPlevel3MinCount;
	
	/** export data mode. 
	 * value scope: true- asynchronous / false - synchronous, 
	 * default:false 
	 * 
	 * note:
	 * 		Current don't consider asynchronous mutil-thread
	 * **/
	private boolean EXPasynchronous;

	/** The maximum number of threads ,use of export data file,  **/
	private int EXPmaxThreadNumber;

	/** export data file timeout ,if overstepping the time limit ,program auto close, unit: millisecond ,eg: 5 * 60 * 60 * 1000=5hours **/
	private int EXPtimeout;

	/** All AuthorityModule.sql's config  */
	private ConfigDocument_SQL SQLDOC ;
	
	/* below twenty-two field are get from sql file */
	private String query_SQL;
	/** count(*) use, In order to improve the efficiency  **/
	private String query_SQL_fromSQL;
	private String query_HQL;
	/** count(*) use  **/
	private String query_HQL_fromHQL;
	
	/** Read and Combobox don't need assemble count SQL, so it don't need **_fromSQL  */
	private String read_SQL;
	private String read_HQL;
	
	private String combobox_SQL;
	private String combobox_HQL;
	
	private String nest_SQL;
	private String nest_HQL;
	
	private String EXP_SQL;
	/** count(*) use  **/
	private String EXP_SQL_fromSQL;
	private String EXP_HQL;
	/** count(*) use  **/
	private String EXP_HQL_fromHQL;
	
	/** All AuthorityModule.js's config  */
	private ConfigDocument_JS JSDOC;

	/** {N_MID:2,C_MNAME:12,C_MCTIME:93,N_MCUSER:2,C_MMTIME:93} */
	private Map columnTypes;
	/** {N_MID:2,C_MNAME:12,C_MCTIME:93,N_MCUSER:2,C_MMTIME:93} */
	private Map columnTypes2;
	
	/** c:columnName, front, back  **/
	private Map col2front;
	private Map col2back;
	private Map front2back;
	private Map front2col;
	private Map back2front;
	private Map back2col;
	
	private Map EXPcolumnMap;
	
	/** @deprecated JSDOC's exportMap instead */
	private String[] EXPcolumnNames;
	/** @deprecated */
	private String[] EXPcolumnNames_zh;
	/** @deprecated */
	private int[] EXPcolumnTypes;
	/** @deprecated */
	private int[] EXPcolumnPrecisions;
	/** @deprecated */
	private int[] EXPcolumnScales;

	/** Export All Columns SQL Map	 */
	private HashMap EXPAllColumnSQLMap;
	// Constructors

	/** default constructor */
	public AbstractConstant() {
	}

	public String getTableName() {
		return tableName;
	}

	public String getPojoName() {
		return pojoName;
	}

	public String getTableName_zh() {
		return tableName_zh;
	}

	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	public String getPrimaryKeySequence() {
		return primaryKeySequence;
	}
	public String getEXPfileName() {
		return EXPfileName;
	}

	public String getEXPsheetName() {
		return EXPsheetName;
	}

	public int getEXPfixColumnCount() {
		return EXPfixColumnCount;
	}

	public int getEXPsingleSheetRecordSize() {
		return EXPsingleSheetRecordSize;
	}

	public int getEXPsingleFileRecordSize() {
		return EXPsingleFileRecordSize;
	}

	public int getEXPlevel1MinCount() {
		return EXPlevel1MinCount;
	}

	public int getEXPlevel2MinCount() {
		return EXPlevel2MinCount;
	}

	public int getEXPlevel3MinCount() {
		return EXPlevel3MinCount;
	}

	public boolean isEXPsheetAutoSizeColumn() {
		return EXPsheetAutoSizeColumn;
	}

	public boolean isEXPcompress() {
		return EXPcompress;
	}

	public boolean isEXPasynchronous() {
		return EXPasynchronous;
	}

	public int getEXPmaxThreadNumber() {
		return EXPmaxThreadNumber;
	}

	public int getEXPtimeout() {
		return EXPtimeout;
	}
	
	public ConfigDocument_SQL getSQLDOC() {
		return SQLDOC;
	}

	public String getQuery_SQL() {
		return query_SQL;
	}
	public String getQuery_SQL_fromSQL() {
		return query_SQL_fromSQL;
	}
	public String getQuery_HQL() {
		return query_HQL;
	}
	public String getQuery_HQL_fromHQL() {
		return query_HQL_fromHQL;
	}
	
	public String getRead_SQL() {
		return read_SQL;
	}
	public String getRead_HQL() {
		return read_HQL;
	}
	public String getCombobox_SQL() {
		return combobox_SQL;
	}
	public String getCombobox_HQL() {
		return combobox_HQL;
	}
	
	public String getNest_SQL() {
		return nest_SQL;
	}

	public String getNest_HQL() {
		return nest_HQL;
	}
	
	public String getEXP_SQL() {
		return EXP_SQL;
	}

	public String getEXP_SQL_fromSQL() {
		return EXP_SQL_fromSQL;
	}

	public String getEXP_HQL() {
		return EXP_HQL;
	}

	public String getEXP_HQL_fromHQL() {
		return EXP_HQL_fromHQL;
	}
	
	public ConfigDocument_JS getJSDOC() {
		return JSDOC;
	}

	public Map getColumnTypes() {
		return columnTypes;
	}

	public Map getColumnTypes2() {
		return columnTypes2;
	}
	
	public Map getCol2front() {
		return col2front;
	}

	public Map getCol2back() {
		return col2back;
	}

	public Map getFront2back() {
		return front2back;
	}

	public Map getFront2col() {
		return front2col;
	}

	public Map getBack2front() {
		return back2front;
	}

	public Map getBack2col() {
		return back2col;
	}

	public Map getEXPcolumnMap() {
		return EXPcolumnMap;
	}

	/** @deprecated */
	public String[] getEXPcolumnNames() {
		return EXPcolumnNames;
	}
	/** @deprecated */
	public String[] getEXPcolumnNames_zh() {
		return EXPcolumnNames_zh;
	}
	
	/** @deprecated */
	public int[] getEXPcolumnTypes() {
		return EXPcolumnTypes;
	}
	/** @deprecated */
	public int[] getEXPcolumnPrecisions() {
		return EXPcolumnPrecisions;
	}
	/** @deprecated */
	public int[] getEXPcolumnScales() {
		return EXPcolumnScales;
	}

	public void setEXPfileName(String eXPfileName) {
		EXPfileName = eXPfileName;
	}
	public void setEXPsheetName(String eXPsheetName) {
		EXPsheetName = eXPsheetName;
	}
	
	public HashMap getEXPAllColumnSQLMap() {
		return EXPAllColumnSQLMap;
	}
	
}