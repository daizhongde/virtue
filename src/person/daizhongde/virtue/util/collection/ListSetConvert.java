package person.daizhongde.virtue.util.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Set与List之间转化 
 * 
	Set与List之间转化 
	List list = new ArrayList(set);
	Set set = new HashSet(list);

	但是有一点,转换当中可能要丢失数据,尤其是从list转换到set的时候,因为set不能有重复数据 //还有转换到set之后,他们原先在list上的顺序就没了,
 
 * @author dzd
 *
 */
public class ListSetConvert {

    public static void main(String[] args) { 
        List list = new ArrayList(); 
//        Collections.addAll(list, "zhao","long","ri");//填充 
        Set set=new HashSet(); 
        set.addAll(list);//给set填充 
        list.clear();//清空list，不然下次把set元素加入此list的时候是在原来的基础上追加元素的 
        list.addAll(set);//把set的 
    }
}
