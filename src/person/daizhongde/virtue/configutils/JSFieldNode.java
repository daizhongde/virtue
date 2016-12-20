package person.daizhongde.virtue.configutils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
*
 * ColumnLabel: [ columnTypes, precision, scale, columnNames_zh, front, back,index ] attemtion:
 * ColumnLabel, front, back, index. All of the four field are unique.
 *
AuthorityModule.Field = {
	N_MID : [ 2, 8, 0, "模块ID", "id", "NMid", 0 ],
	C_MNAME : [ 12, 90, 0, "模块名称", "name", "CMname", 1 ],
	N_MLEVEL : [ 2, 4, 0, "模块级别", "level", "NMlevel", 2 ],
	C_MLEAF : [ 12, 5, 0, "模块类型", "leaf", "CMleaf", 3 ],
	N_MORDER : [ 2, 4, 0, "模块次序号", "order", "NMorder", 4 ],
	N_MPARENT : [ 2, 8, 0, "上级模块", "parent", "NMparent", 5 ],
	C_MTARGET : [ 12, 3, 0, "模块链接目标", "target", "CMtarget", 6 ],
	C_MICONCLS : [ 12, 30, 0, "模块图标样式", "iconcls", "CMiconcls", 7 ],
	C_MEXPANDED : [ 12, 5, 0, "模块结点展开状态", "expanded", "CMexpanded", 8 ],
	C_MCHECKED : [ 12, 5, 0, "模块结点是否被选定", "checked", "CMchecked", 9 ],
	C_MPATH : [ 12, 120, 0, "模块url路径", "path", "CMpath", 10 ],
	C_MNOTE : [ 12, 120, 0, "备注", "note", "CMnote", 11 ],
	C_MCTIME : [ 93, 0, 0, "模块创建时间", "ctime", "CMctime", 12 ],
	C_MCIP : [ 12, 60, 0, "模块创建者创建时所使用的IP", "cip", "CMcip", 13 ],
	N_MCUSER : [ 2, 8, 0, "模块创建者", "cuser", "NMcuser", 14 ],
	C_MMTIME : [ 93, 0, 0, "模块修改时间,M 触发器自动写入", "mtime", "CMmtime", 15 ],
	C_MMIP : [ 12, 60, 0, "模块个最后修改者使用的IP", "mip", "CMmip", 16 ],
	N_MMUSER : [ 2, 8, 0, "模块最后一次的个修改者", "muser", "NMmuser", 17 ]
};
 * @date 20131113
 * @author dzd
 *
 */
public class JSFieldNode {

	private Map field;
	/** column name-type map<p>{N_MID:2,C_MNAME:12,C_MCTIME:93,N_MCUSER:2,C_MMTIME:93} */
	private Map columnTypes;
	/** pojo field-type map<p>{NMid:2,CMname:12,CMctime:93,NMcuser:2,CMmtime:93} */
	private Map columnTypes2;
	/** column name-Precisions map<p>  */
	private Map Precisions;
	/** column name-Scales map<p>  */
	private Map Scales;
	private Map col2front;
	private Map col2back;
	private Map front2back;
	private Map front2col;
	private Map back2front;
	private Map back2col;
	
	/**
	 * construction assemble seven map
	 * @param map
	 */
	public JSFieldNode(Map map){
		this.field = map;
		this.columnTypes = new HashMap();
		this.columnTypes2 = new HashMap();
		this.Precisions = new HashMap();
		this.Scales = new HashMap();
		
		this.col2front = new HashMap();
		this.col2back = new HashMap();
		this.front2back = new HashMap();
		this.front2col = new HashMap();
		this.back2front = new HashMap();
		this.back2col = new HashMap();
		
		Set set = map.keySet();
		int size = set.size();
		
		Iterator it = set.iterator();
		String key;
		for( int i=0, j=size; i<j; i++ ) {
			key = it.next().toString();
			List list = (List)map.get( key );
			this.columnTypes.put( key, (Integer)list.get(0) );//{N_MID:2}
			this.columnTypes2.put( (String)list.get(5), (Integer)list.get(0) );//{NMid:2}
			this.Precisions.put( key, (Integer)list.get(1) );
			this.Scales.put( key, (Integer)list.get(2) );
			
			this.col2front.put( key, (String)list.get(4) );
			this.col2back.put( key,  (String)list.get(5) );
			this.front2back.put( (String)list.get(4), (String)list.get(5) );
			this.front2col.put( (String)list.get(4),  key );
			this.back2front.put( (String)list.get(5),  (String)list.get(4) );
			this.back2col.put( (String)list.get(5),  key );
		}
//		JSONArray jsonArray = JSONArray.fromObject(dataList);
//		System.out.println("columnTypes:"+JSONObject.fromObject(columnTypes).toString() );
//		System.out.println("c2front:"+JSONObject.fromObject(c2front).toString());
//		System.out.println("c2back:"+JSONObject.fromObject(c2back).toString());
//		System.out.println("front2back:"+JSONObject.fromObject(front2back).toString());
//		System.out.println("front2c:"+JSONObject.fromObject(front2c).toString());
//		System.out.println("back2front:"+JSONObject.fromObject(back2front).toString());
//		System.out.println("back2c:"+JSONObject.fromObject(back2c).toString());
	}
	
	public Map getField() {
		return field;
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
