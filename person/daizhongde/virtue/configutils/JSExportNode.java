package person.daizhongde.virtue.configutils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 *
 * ColumnLabel: [ columnTypes, columnPrecisions, columnScales, columnNames_zh, index ]
 * <br>key whom double quotation marks illustrate it's converted. 
 * <br>eg: decode, case when and other process table's column.
 * 
 *<p>
AuthorityModule.Export.export.ColumnMap = {
	N_MID : [ 2, 8, 0, "模块ID", 0 ],
	C_MNAME : [ 12, 90, 0, "模块名称", 1 ],
	N_MLEVEL : [ 2, 4, 0, "模块级别", 2 ],
	"level" : [ 12, 41, 0, "模块级别", 3 ],
	C_MLEAF : [ 12, 5, 0, "叶子节点", 4 ],
	"leaf" : [ 12, 5, 0, "叶子节点", 5 ],
	N_MORDER : [ 2, 4, 0, "模块次序号", 6 ],
	N_MPARENT : [ 2, 8, 0, "上级模块", 7 ],
	"parent" : [ 12, 90, 0, "上级模块", 8 ],
	C_MTARGET : [ 12, 3, 0, "模块" +"链接" +"目标", 9 ],
	"target" : [ 12, 7, 0, "链接目录", 10 ],
	C_MICONCLS : [ 12, 30, 0, "图标样式", 11 ],
	C_MEXPANDED : [ 12, 5, 0, "模块结点" +"展开" +"状态", 12 ],
	"expanded" : [ 12, 5, 0, "展开状态", 13 ],
	C_MCHECKED : [ 12, 5, 0, "模块结点是否被选定", 14 ],
	"checked" : [ 12, 5, 0, "选定状态", 15 ],
	C_MPATH : [ 12, 120, 0, "模块路径", 16 ],
	C_MNOTE : [ 12, 120, 0, "备注", 17 ],
	C_MCTIME : [ 93, 0, 0, "创建时间", 18 ],
	C_MCIP : [ 12, 60, 0, "创建者IP", 19 ],
	N_MCUSER : [ 2, 8, 0, "创建者", 20 ],
	C_MMTIME : [ 93, 0, 0, "修改时间", 21 ],
	C_MMIP : [ 12, 60, 0, "修改者" +"IP", 22 ],
	N_MMUSER : [ 2, 8, 0, "修改" +"者", 23 ]};
 * @date 20131113
 * @author dzd
 *
 */
public class JSExportNode {

	private Map ColumnMap;
	private String[] columnNames;
	private int[] columnTypes;
	private int[] columnPrecisions;
	private int[] columnScales;
	private String[] columnNames_zh;
	
	private List defaultColumns;
	
	public JSExportNode(){

	}
	
	public JSExportNode(Map ColumnMap){
		this.ColumnMap = ColumnMap;
		Set set = ColumnMap.keySet();
		int size = set.size();
		this.columnNames = new String[size];
		this.columnTypes = new int[size];
		this.columnPrecisions = new int[size];
		this.columnScales = new int[size];
		this.columnNames_zh = new String[size];

		Iterator it = set.iterator();
		Object key;
		for( int i=0, j=size; i<j; i++ ) {
			key = it.next();
			List list = (List)ColumnMap.get( key );
			this.columnNames[i] = (String)key;
			this.columnTypes[i] = Integer.valueOf(list.get(0).toString()).intValue();
			this.columnPrecisions[i] = Integer.valueOf(list.get(1).toString()).intValue();
			this.columnScales[i] = Integer.valueOf(list.get(2).toString()).intValue();
			this.columnNames_zh[i] = (String)list.get(3);
		}
//		System.out.println("columnNames_zh.length:"+columnNames_zh.length);
//		System.out.println("columnTypes.length:"+columnTypes.length);
//		System.out.println("columnPrecisions.length:"+columnPrecisions.length);
//		System.out.println("columnScales.length:"+columnScales.length);
	}
	
	public JSExportNode(Map ColumnMap, List defaultColumns ){
		this.ColumnMap = ColumnMap;
		this.defaultColumns = defaultColumns;
		Set set = ColumnMap.keySet();
		int size = set.size();
		this.columnNames = new String[size];
		this.columnTypes = new int[size];
		this.columnPrecisions = new int[size];
		this.columnScales = new int[size];
		this.columnNames_zh = new String[size];

		Iterator it = set.iterator();
		Object key;
		for( int i=0, j=size; i<j; i++ ) {
			key = it.next();
			List list = (List)ColumnMap.get( key );
			this.columnNames[i] = (String)key;
			this.columnTypes[i] = Integer.valueOf(list.get(0).toString()).intValue();
			this.columnPrecisions[i] = Integer.valueOf(list.get(1).toString()).intValue();
			this.columnScales[i] = Integer.valueOf(list.get(2).toString()).intValue();
			this.columnNames_zh[i] = (String)list.get(3);
		}
//		System.out.println("columnNames_zh.length:"+columnNames_zh.length);
//		System.out.println("columnTypes.length:"+columnTypes.length);
//		System.out.println("columnPrecisions.length:"+columnPrecisions.length);
//		System.out.println("columnScales.length:"+columnScales.length);
	}
	
	public Map getColumnMap() {
		return ColumnMap;
	}

	public void setColumnMap(Map ColumnMap) {
		this.ColumnMap = ColumnMap;
		Set set = ColumnMap.keySet();
		int size = set.size();
		this.columnNames = new String[size];
		this.columnTypes = new int[size];
		this.columnPrecisions = new int[size];
		this.columnScales = new int[size];
		this.columnNames_zh = new String[size];

		Iterator it = set.iterator();
		Object key;
		for( int i=0, j=size; i<j; i++ ) {
			key = it.next();
			List list = (List)ColumnMap.get( key );
			this.columnNames[i] = (String)key;
			this.columnTypes[i] = Integer.valueOf(list.get(0).toString()).intValue();
			this.columnPrecisions[i] = Integer.valueOf(list.get(1).toString()).intValue();
			this.columnScales[i] = Integer.valueOf(list.get(2).toString()).intValue();
			this.columnNames_zh[i] = (String)list.get(3);
		}
	}

	public List getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(List defaultColumns) {
		this.defaultColumns = defaultColumns;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public int[] getColumnTypes() {
		return columnTypes;
	}

	public int[] getColumnPrecisions() {
		return columnPrecisions;
	}

	public int[] getColumnScales() {
		return columnScales;
	}

	public String[] getColumnNames_zh() {
		return columnNames_zh;
	}

	//	/**
//	 * @deprecated
//	 * @param map
//	 * @param index
//	 * @return
//	 */
//	public static Object[] getArray( Map map, int index ){
//		Set set = map.keySet();
//		int size = set.size();
//		Object[] ret = new Object[size];
//		
//		Iterator it = set.iterator();
//		for( int i=0, j=size; i<j; i++ ) {
//			List list = (List)map.get( it.next() );
//			ret[i] = list.get(index);
//		}
//		return ret;
//	}
//	/**
//	 * @deprecated
//	 * @param map
//	 * @param index
//	 * @return
//	 */
//	public static String[] getStringArray( Map map, int index ){
//		Set set = map.keySet();
//		int size = set.size();
//		String[] ret = new String[size];
//		
//		Iterator it = set.iterator();
//		for( int i=0, j=size; i<j; i++ ) {
//			List list = (List)map.get( it.next() );
//			ret[i] = (String)list.get(index);
//		}
//		return ret;
//	}
//	/**
//	 * @deprecated
//	 * @param map
//	 * @param index
//	 * @return
//	 */
//	public static int[] getIntArray( Map map, int index ){
//		Set set = map.keySet();
//		int size = set.size();
//		int[] ret = new int[size];
//		
//		Iterator it = set.iterator();
//		for( int i=0, j=size; i<j; i++ ) {
//			List list = (List)map.get( it.next() );
//			ret[i] = (int)list.get(index);
//		}
//		return ret;
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] ret = new String[2];
//		List list = new LinkedList();
		ret[0] = "abc";
		ret[1] = "def";
		System.out.println("ret.length:"+ret.length);
		System.out.println("ret:"+ret.toString());
	}

}
