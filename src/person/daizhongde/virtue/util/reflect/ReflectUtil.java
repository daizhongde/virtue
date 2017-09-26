package person.daizhongde.virtue.util.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
	public static Object invokeMethod(Object obj, String methodName) 
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class clazz = obj.getClass(); 

        Method m = clazz.getDeclaredMethod( methodName );
        
        return m.invoke(obj); 
	}

	public static Object reflectStartMethod(String className,
			String methodName, Object[] methodParameters,
			Class[] parameterClasses) throws Exception {
		Object result = null;
		Class cls = Class.forName(className);

		Object obj = cls.newInstance();

		Method method = obj.getClass().getMethod(methodName, parameterClasses);

		result = method.invoke(obj, methodParameters);
		return result;
	}
	/**
	 * 
	 * @param className eg:"org.test.GetClass"
	 * @return
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException 
	 * @throws InstantiationException 
	 */
	public static Object newInstance( String className ) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
//		创建的这个类的需要继承一个接口(Interface) ，因为获取了实例需要强制转换成某一个借口的实例才可以调用其中的方法。
//		创建一个Class类的引用，调用Class类的静态方法forName(String)，参数为类的绝对路径(包含包路径如:org.test.GetClass)。
		Class onwClass = Class.forName( className );
//		现在我们有了一个Class类的实例了。下面就是获取一个Object类的实例对象。
		return onwClass.newInstance();
//		现在我们已经有了一个Object对象，下一步就可以对这个Object进行强制转换了。
//		假如org.test.GetClass 继承一个叫GetInterface的接口。我们就可以把这个object对象强制转换成这个接口，然后就可以调用其中的方法了。
	}
	
	
    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Foo foo = new Foo("这个一个Foo对象！");
        Class clazz = foo.getClass(); 
        Method m1 = clazz.getDeclaredMethod("outInfo");
        Method m2 = clazz.getDeclaredMethod("setMsg", String.class);
        Method m3 = clazz.getDeclaredMethod("getMsg");
        m1.invoke(foo); 
        m2.invoke(foo, "重新设置msg信息！"); 
        String msg = (String) m3.invoke(foo); 
        System.out.println(msg); 
    }
}

class Foo { 
    private String msg; 

    public Foo(String msg) { 
        this.msg = msg; 
    } 

    public void setMsg(String msg) {
        this.msg = msg; 
    } 

    public String getMsg() { 
        return msg; 
    } 

    public void outInfo() {
        System.out.println("这是测试Java反射的测试类"); 
    } 
}