package person.daizhongde.virtue.sigar;

import person.daizhongde.virtue.util.codec.AES;
import person.daizhongde.virtue.util.codec.SHA1_Encoding;
import person.daizhongde.virtue.util.string.StringUtils2;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * <br>类描述：
 *   第三代机器码  生成快、无依赖
 *
 * @author dzd daizhongde@copote.com	2021/4/18 21:21
 * @ClassName ComputerIdentifier
 * @see #
 * @since {修改人、修改时间、修改事由}
 */

public class ComputerIdentifier2 {
	
	private static Map<String, String> map = System.getenv();

    private static Properties prop = System.getProperties();
	
	/**
	 * 获取本机机器码（明文）
	 * 
	 */
	public static String generateLicenseKey() throws Exception {
		
		String USERNAME = map.get("USERNAME");// 获取用户名;
		String COMPUTERNAME =  map.get("COMPUTERNAME");// 获取计算机名
		String USERDOMAIN = map.get("USERDOMAIN");// 获取计算机域名
		
		String PROCESSOR_ARCHITECTURE = map.get("PROCESSOR_ARCHITECTURE");
		String PROCESSOR_IDENTIFIER = map.get("PROCESSOR_IDENTIFIER");
		String PROCESSOR_LEVEL = map.get("PROCESSOR_LEVEL");
		String PROCESSOR_REVISION = map.get("PROCESSOR_REVISION");

		String delimiter = "#";

		System.out.println("USERNAME:"+USERNAME);
		System.out.println("COMPUTERNAME:"+COMPUTERNAME);
		System.out.println("USERDOMAIN:"+USERDOMAIN);
		System.out.println("PROCESSOR_ARCHITECTURE:"+PROCESSOR_ARCHITECTURE);
		System.out.println("PROCESSOR_IDENTIFIER:"+PROCESSOR_IDENTIFIER);
		System.out.println("PROCESSOR_LEVEL:"+PROCESSOR_LEVEL);
		System.out.println("PROCESSOR_REVISION:"+PROCESSOR_REVISION);

		String code = USERNAME + 
				delimiter + COMPUTERNAME +
				delimiter + USERDOMAIN + 
				delimiter + PROCESSOR_ARCHITECTURE + 
				delimiter + PROCESSOR_IDENTIFIER + 
				delimiter + PROCESSOR_LEVEL + 
				delimiter + PROCESSOR_REVISION;
		
		System.out.println("Local Machine code:<"+code+">");
		System.out.println("Local Machine code.length:<"+code.length()+">");
		return code;
	}

	/**
	 * 获取（本机）160位由license文件名（带扩展名）、机器码（明文）组成的串
	 * 
	 */
    public static String generateLicenseKey(String licfilename) throws Exception {
    	String code = generateLicenseKey();
        return generateLicenseKey( licfilename,  code);
    }

	/**
	 * 获取160位由license文件名（带扩展名）、机器码（明文）组成的串
	 * 
	 */
    public static String generateLicenseKey(String licfilename, String code) throws Exception {
        return StringUtils2.leftPad(licfilename, 32, ' ')+StringUtils2.leftPad(code, 128, ' ');
    }

    public static void printJvm() {
        MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        System.out.println("jvm.heap.init is " + (heapMemoryUsage.getInit()));
        System.out.println("jvm.heap.used is " + (heapMemoryUsage.getUsed()));
        System.out.println("jvm.heap.committed is " + (heapMemoryUsage.getCommitted()));
        System.out.println("jvm.heap.max is " + (heapMemoryUsage.getMax()));
    }

    public static void printNoJvm() {
        MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
        System.out.println("jvm.nonheap.init is " + (nonHeapMemoryUsage.getInit()));
        System.out.println("jvm.nonheap.used is " + (nonHeapMemoryUsage.getUsed()));
        System.out.println("jvm.nonheap.committed is " + (nonHeapMemoryUsage.getCommitted()));
        System.out.println("jvm.nonheap.max is " + (nonHeapMemoryUsage.getMax()));
    }

    public static void printdiffpartJvm() {
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            final String kind = pool.getType() == MemoryType.HEAP ? "heap" : "nonheap";
            final MemoryUsage usage = pool.getUsage();
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName()
                    + ".init is " + usage.getInit());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName()
                    + ".used is " + usage.getUsed());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName()
                    + ".committed is " + usage.getCommitted());
            System.out.println("kind is " + kind + ", pool name is " + pool.getName() + ", jvm." + pool.getName()
                    + ".max is " + usage.getMax());
        }
    }
    
    public void printEnv(){
        System.out.println("###############################  1  ####################");
        Iterator it =  map.keySet().iterator();
        while( it.hasNext() ){
        	String key = it.next().toString();
        	System.out.println(key + ":" + map.get(key));
        }
        
    }

    /**
     * 
     * @Description:仅用于测试
     * @param:       
     * @return: void      
     * @throws
     */
    public void printProp(){

        System.out.println("###############################  3  #################### ");
        Enumeration enum2 =  prop.propertyNames();
        while( enum2.hasMoreElements()  ){
        	String key = enum2.nextElement().toString();
        	System.out.println(key + ":" + prop.getProperty(key));
        }

        System.out.println("###############################  4  ####################   ");
        
    }

    /**
     * 
     * @Description:仅用于测试
     * @param:       
     * @return: void      
     * @throws
     */
    public static void main(String[] arguments) throws Exception
    {
        // 20210418 生成License的identifier 改用license文件名加 原机器码identifier
        String identifier = generateLicenseKey("devops.lic");
//		String identifier = "GNU/Linux#6b139ad3-c61c-4e46-b82e-0286ce4b93e5#Intel64 Family 6 Model 85 Stepping 7#1";
//        String identifier = "devops.lic"+ "Microsoft#CNG124SL23      #Intel64 Family 6 Model 44 Stepping 2#24";
//        String identifier = "devops.lic"+ "GNU/Linux#unknown#Intel64 Family 6 Model 85 Stepping 4#48";
        // appl-stamp-22 "GNU/Linux#06CWV70#Intel64 Family 6 Model 44 Stepping 2#8";
        // aliyun www 45: "GNU/Linux#unknown#Intel64 Family 6 Model 79 Stepping 1#2"
        // v3 10.4.229.186  :  GNU/Linux#unknown#Intel64 Family 6 Model 79 Stepping 1#4
//		 200.200.200.55 : Microsoft#CNG124SL23      #Intel64 Family 6 Model 44 Stepping 2#24
        // 192.168.2.100  GNU/Linux#unknown#Intel64 Family 6 Model 158 Stepping 9#4
        // mycomputer： Microsoft#PF0YXYP0#Intel64 Family 6 Model 142 Stepping 9#4
        // mycomputer： mockserver.licMicrosoft#PF0YXYP0#Intel64 Family 6 Model 142 Stepping 9#4
        /* 47.107.250.74
         * Local Machine code:<GNU/Linux#6b139ad3-c61c-4e46-b82e-0286ce4b93e5#Intel64 Family 6 Model 85 Stepping 7#1>
         *
         * */
        System.out.println("############################### ");
        System.out.println(identifier);

        String sha1= SHA1_Encoding.toSHA1(identifier+"82012202");
        System.out.println("sha1:"+sha1);//lic

        String y1 = (new SimpleDateFormat("yyyy")).format(new Date());
        StringBuffer sb = new StringBuffer(y1);
        sb = sb.reverse();
        String y11=sb.toString();
        System.out.println("y11:"+y11);

        System.out.println("############################### ");
        String code = generateLicenseKey();
        AES aes = new AES();
        String MachineCodeMiWen = aes.encrypt( code,"          zddaic","           98974");
        System.out.println("###############################  MachineCodeMiWen:"+MachineCodeMiWen);
        String machineCode = aes.decrypt( MachineCodeMiWen,"          zddaic","           98974");
        System.out.println("###############################  machineCode:"+machineCode);

 
        
    }
    
    
}