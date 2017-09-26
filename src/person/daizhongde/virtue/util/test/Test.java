package person.daizhongde.virtue.util.test;

public class Test {
	   public static void main(String[] args)
	    {
		   /*
ll \
-lh \
-a
		    * */
		   String s = "ll \\\n-lh \\\n-a";
		   System.out.println("s1:"+s);
		   s = s.replaceAll("\t|\r|\n", " ");
		   System.out.println("s2:"+s);
		   s = s.replace(" \\ ", " ");
		   System.out.println("s3:"+s);
	    }
}
