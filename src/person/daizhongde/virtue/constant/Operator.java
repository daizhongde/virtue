package person.daizhongde.virtue.constant;
/**
 * operator
 * <p>
 *      0      1=   2!=    3>   4>=      5<   6<=       7like s%  8   9like %s  10  11like %s%  12   13      14          15         16             
 * {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    between not between in (1,2,3) not in (1,2,3) 
 * {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
 * {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
 * </p><p>
 * <br>     1    equal  
 * <br>     2    notEqual 
 * <br>     3    greater then (greaterThen, gt)
 * <br>     4    greater then or equal (greaterThenOrEqual, gE)
 * <br>     5    less then (lessThen, lt)
 * <br>     6    less then or equal (lessThenOrEqual, lE)
 * <br>     7    startWith
 * <br>     8    not startWith (notStartWith)
 * <br>     9    endWith
 * <br>    10    not endWith (notEndWith)
 * <br>    11    contain
 * <br>    12    notContain
 * <br>    13    between
 * <br>    14    notBetween
 * <br>    15    inList
 * <br>    16    notInList
 * <br>    17    exists
 * <br>    18    notExists
 * </p>
 * 
 * <p>
 * javascripts:
 * <br>
 * VIRTUE.operator = {
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;	    equal: 1,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notEqual: 2,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		gt: 3,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		gtE: 4,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		lt: 5,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		ltE: 6,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		startWith: 7,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notStartWith:8,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		endWith: 9,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notEndWith: 10,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		contain: 11,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notContain: 12,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		between: 13,
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notBetween: 14
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		inList: 15
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notInList: 16
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		exists: 17
 *<br>&nbsp;&nbsp;&nbsp;&nbsp;		notExists: 18
 *<br>};
 * </p>
 * @author dzd
 * @date 2013/10/16
 */
public class Operator {
	public final static int EQUAL              =   1;
	public final static int NOTEQUAL           =   2;
	
	/** greater then **/
    public final static int GT                 =   3;
    
    /** greater than or equal to **/
    public final static int GE                 =   4;
    
    /** less-than **/
    public final static int LT                 =   5;
    
    /** less-than-or-equal-to **/
    public final static int LE                 =   6;
    
    public final static int STARTWITH          =   7;
    public final static int NOTSTARTWITH       =   8;
    public final static int ENDWITH            =   9;
    public final static int NOTENDWITH         =  10;
    public final static int CONTAIN            =  11;
    public final static int NOTCONTAIN         =  12;
    
    /** between **/
    public final static int BETWEEN            =  13;
    /** not between **/
    public final static int NOTBETWEEN         =  14;
    
    /** in, join list or Nested Subqueries   **/
    public final static int IN                 =  15;
    /** not in  **/
    public final static int NOTIN              =  16;
    
    /** exists, join Nested Subqueries   **/
    public final static int EXISTS             =  17;
    /** not exists  **/
    public final static int NOTEXISTS          =  18;
    // Prevent instantiation
    private Operator() {}
}
