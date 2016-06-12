package person.daizhongde.virtue.configutils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 *
PayerCommiInfo.Import={};
PayerCommiInfo.Import.import={};

//array's order is import file field order
PayerCommiInfo.Import.import.DefaultColumns=
	[
	"PAY_ID",
	"ACC_ID",
	"ACC_CARD_ID",
	"COMMI_INST_ID",
	"MERCH_ID",
	"PREV_CHARGE_AT",
	"PREV_CHARGE_LVL","PREPAY_AT","AMOUNT1"];
 * @date 20131113
 * @author dzd
 *
 */
public class JSImportNode {
	
	private List defaultColumns;
	
	public JSImportNode(){

	}
	
	public JSImportNode( List defaultColumns ){

		this.defaultColumns = defaultColumns;
		
	}

	public List getDefaultColumns() {
		return defaultColumns;
	}

	public void setDefaultColumns(List defaultColumns) {
		this.defaultColumns = defaultColumns;
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
