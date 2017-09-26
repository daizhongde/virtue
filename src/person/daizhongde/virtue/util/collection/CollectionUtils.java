package person.daizhongde.virtue.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author dzd
 *
 */
public class CollectionUtils {
	/**
	 * 将Collection转化为List对象
	 * @param coll
	 * @return
	 */
	public List CvColltoArrayList(Collection coll){
		List ls = new ArrayList();
		Iterator it= coll.iterator();
		while(it.hasNext()){
			ls.add(it.next());
			
		}
		return ls;
	}
}
