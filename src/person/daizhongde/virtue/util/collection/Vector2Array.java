package person.daizhongde.virtue.util.collection;

import java.util.Vector;

public class Vector2Array {
	public String[] toStringArray(Vector v){
		String[] arr = new String[v.size()];
		for(int i=0, j=v.size(); i<j; i++){
			arr[i] = String.valueOf(v.get(i));
		}
		return arr;
	}
	public Integer[] toIntegerArray(Vector v){
		Integer[] arr = new Integer[v.size()];
		for(int i=0, j=v.size(); i<j; i++){
			arr[i] = Integer.valueOf(v.get(i).toString());
		}
		return arr;
	}
	public static void print(Vector[] v){
		for(int i=0,j=v.length; i<j; i++){
			for(int m=0,n=((Vector)(v[i])).size(); m<n; m++){
				System.out.print(((Vector)(v[i])).get(m)+"    ");
			}
			System.out.println("");
		}
	}
}
