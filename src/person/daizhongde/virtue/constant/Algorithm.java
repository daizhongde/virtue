package person.daizhongde.virtue.constant;
/**
 * algorithm
 * <p>
 *       0       1(+) 2(-) 3(*) 4(/)
 * {" - 选择 - ","加","减","乘","除"}; add, subtract, multiply and divide 
 *           1    plus  
 *           2    minus 
 *           3    multiply
 *           4    divide
 *           5    common
 * </p>
 * 
 * <p>
 * javascripts:
 * <br>
 * VIRTUE.algorithm = {
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;	    plus: 1,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		minus: 2,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		multiply: 3,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		divide: 4
 *<br>};
 * </p>
 * @author dzd
 * @date 2013/10/17
 */
public class Algorithm {
	/** plus **/
	public final static int PLUS            =   1;
	
	/** minus **/
	public final static int MINUS           =   2;
	
	/** multiply **/
    public final static int MULTIPLY        =   3;
    
    /** divide **/
    public final static int DIVIDE          =   4;

//    /** normal **/
//    public final static int COMMON          =   5;
    
    // Prevent instantiation
    private Algorithm() {}
}
