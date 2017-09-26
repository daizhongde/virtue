package person.daizhongde.virtue.util.collection;

public class StringArray2IntegerArray {
	public static int[] toIntegerArray(String[] sa){
		int[] arr = new int[sa.length];
		for(int i=0, j = sa.length; i < j; i++){
			arr[i] = Integer.valueOf( sa[i] ).intValue();
		}
		return arr;
	}

	public static void print(String[] arr){
		for(int i=0,j=arr.length; i<j; i++){
		
			System.out.println(arr[i]);
		}
	}
	
	// Prevent instantiation
    private void StringArray2IntegerArray() {}
}
