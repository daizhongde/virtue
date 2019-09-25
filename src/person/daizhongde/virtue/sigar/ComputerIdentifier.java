package person.daizhongde.virtue.sigar;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.lang.management.MemoryUsage;
import java.text.SimpleDateFormat;
import java.util.Date;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import person.daizhongde.virtue.codec.SHA1_Encoding;

public class ComputerIdentifier {
	public static String generateLicenseKey() throws Exception {
		SystemInfo systemInfo = new SystemInfo();
		OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
		HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
		CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();

		ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
		
		String vendor = operatingSystem.getManufacturer();
//		String processorSerialNumber = centralProcessor.getSystemSerialNumber();
		String processorSerialNumber = computerSystem.getSerialNumber();
		
		String processorIdentifier = centralProcessor.getIdentifier();
		int processors = centralProcessor.getLogicalProcessorCount();

		String delimiter = "#";

		System.out.println("vendor:"+vendor);
		System.out.println("processorSerialNumber:"+processorSerialNumber);
		System.out.println("delimiter:"+delimiter);
		System.out.println("processorIdentifier:"+processorIdentifier);
		System.out.println("delimiter:"+delimiter);
		System.out.println("processors:"+processors);

		String code = vendor + delimiter + processorSerialNumber + delimiter + processorIdentifier + delimiter + processors;
		System.out.println("Local Machine code:<"+code+">");
		return code;
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

	public static void main(String[] arguments) throws Exception
    {
//        String identifier = generateLicenseKey();
		String identifier = "Microsoft#CNG124SL23      #Intel64 Family 6 Model 44 Stepping 2#24";
		// appl-stamp-22 "GNU/Linux#06CWV70#Intel64 Family 6 Model 44 Stepping 2#8";
		// aliyun www 45: "GNU/Linux#unknown#Intel64 Family 6 Model 79 Stepping 1#2" 
		// v3 10.4.229.186  :  GNU/Linux#unknown#Intel64 Family 6 Model 79 Stepping 1#4
//		 200.200.200.55 : Microsoft#CNG124SL23      #Intel64 Family 6 Model 44 Stepping 2#24
        System.out.println();
        System.out.println(identifier);


        String sha1= SHA1_Encoding.toSHA1(identifier+"82019102");
        System.out.println("sha1:"+sha1);//lic
        
        String y1 = (new SimpleDateFormat("yyyy")).format(new Date());
		StringBuffer sb = new StringBuffer(y1);
		sb = sb.reverse();
		String y11=sb.toString();
        System.out.println("y11:"+y11);
		
        
    }
}