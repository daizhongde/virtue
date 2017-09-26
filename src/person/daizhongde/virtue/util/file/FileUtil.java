package person.daizhongde.virtue.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import person.daizhongde.virtue.util.test.Printer;

public class FileUtil {

	public static void copy(String source, String destination)
			throws IOException {
		copy(new File(source), destination);
	}

	/**
	 * if destination is directory, it must end with '/'
	 * 
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public static void copy(File source, String destination) throws IOException {
		java.io.File file = new java.io.File(destination);
		// file.getAbsoluteFile();
		// file.getAbsolutePath();
		if (file.isDirectory()) {
			destination += source.getName();
		}

		FileOutputStream fos = new FileOutputStream(destination);
		// 输入流用来读文件，下面按一次读1M用来让fos写，提高效率
		FileInputStream fis = new FileInputStream(source);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
	}

	// public boolean checkIfFileIsExists(String file){
	// return new File(file).exists();
	// }
	/**
	 * Java文件操作 获取文件扩展名
	 *
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return "";
	}

	/**
	 * Java文件操作 获取不带扩展名的文件名
	 *
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	/** InputStreamReader+BufferedReader读取字符串 ， InputStreamReader类是从字节流到字符流的桥梁 
 		按行读对于要处理的格式化数据是一种读取的好方式 
 		<br>read by line
 */
	public static String read2String(String file)
	{
		int len = 0;
		StringBuffer str = new StringBuffer("");
//		File file = new File(FILE_IN);
		try {
			FileInputStream is = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader in = new BufferedReader(isr);
			String line = null;
			while ((line = in.readLine()) != null)
			{
				if (len != 0) // 处理换行符的问题
				{
					str.append(" \r\n" + line);
				}
				else
				{
					str.append(line);
				}
				len++;
			}
			in.close();
			isr.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
/**
 * read by byte
 * @param FILE_IN
 * @return
 */
	public static String read2String2(String file)
	{
		int len = 0;
		StringBuilder  str = new StringBuilder("");
//		File file = new File(FILE_IN);
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream( file ), 1*1024*1024);
	        
	        byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
	        int n;// 每次读取到的字节数组的长度
	        while ((n = in.read(bb)) != -1) {
//	        	System.out.println("n:"+n);
	        	str.append( new String( bb,0,n ) );
	        }
	        in.close();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	/**
	 * read by byte
	 * @param FILE_IN
	 * @return
	 */
		public static String read2String(InputStream is)
		{
			int len = 0;
			StringBuilder  str = new StringBuilder("");
//			File file = new File(FILE_IN);
			try {
				BufferedInputStream in = new BufferedInputStream(is, 1*1024*1024);
		        
		        byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
		        int n;// 每次读取到的字节数组的长度
		        while ((n = in.read(bb)) != -1) {
//		        	System.out.println("n:"+n);
		        	str.append( new String( bb,0,n ) );
		        }
		        in.close();
		        
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str.toString();
		}
	public static InputStream readFile(String file){
		InputStream is = null;
		try {
			if( file.startsWith("com/") )
			{
//				System.out.println("1 starts with com/");
//				is = FileUtil.class.getResourceAsStream( fname );
//				Properties  properties = System.getProperties();
////				properties.list(System.out);
//				if( properties.get("sun.java.command").toString().indexOf("junit") != -1 ){
//					
//				}
				String path =FileUtil.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径

//				同目录文件夹：classes，m2e-jee，test-classes
				int index = path.indexOf("/test-classes/");
				
				System.out.println("path:"+path);
//				path:/D:/daizd/Workspaces/MyEclipse%202015%20CI/migration/target/test-classes/
//				or: D:/tomcat/webapps/migration/WEB-INF/classes/
				
//				D:\AppData\Workspaces\MyEclipse 2015\message\bin\com\isoftstone\mq\mdm\xml4test
				
				int binIndex = path.indexOf("/bin/");
				int classesIndex = path.indexOf("/classes/");
				
				if( binIndex != -1 ){
					path=path.substring(1, 
				    		index==-1 ? path.indexOf("/bin/") : index
				    	);//从路径字符串中取出工程路径
					if(file.indexOf("authority") != -1){
				    	is = FileUtil.class.getResourceAsStream( "/"+file );
				    }else{
				    	is = new FileInputStream( java.net.URLDecoder.decode( path +"/bin/"+ file,"UTF-8") );
				    }
				}else if( classesIndex != -1 ){
					path=path.substring(1, 
				    		index==-1 ? path.indexOf("/classes/") : index
				    	);//从路径字符串中取出工程路径
					if(file.indexOf("authority") != -1){
				    	is = FileUtil.class.getResourceAsStream( "/"+file );
				    }else{
				    	is = new FileInputStream( java.net.URLDecoder.decode( path +"/classes/"+ file,"UTF-8") );
				    }
				}			    
			}
			else if( file.startsWith("/WEB-INF/") )
			{
//				System.out.println("2...");
				String path =FileUtil.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
				
//				System.out.println("path:"+path);
			    path=path.substring(0, path.indexOf("/WEB-INF/"));//从路径字符串中取出工程路径
				is = new FileInputStream( java.net.URLDecoder.decode( path + file,"UTF-8") );
			}
			else if( file.indexOf("/")==-1 )
			{
//				System.out.println("3...");
				is = FileUtil.class.getResourceAsStream( "/"+file );
			}
			else
			{
				is = new FileInputStream( file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			try {
//				is.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		return is;
	}

	private byte[] readInstream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream byteArrayOutPutStream = new ByteArrayOutputStream();// 创建ByteArrayOutputStream类
		byte[] buffer = new byte[1024];// 声明缓存区
		int length = -1;// 定义读取的默认长度
		while ((length = inputStream.read(buffer)) != -1) {
			byteArrayOutPutStream.write(buffer, 0, length);// 把缓存区中的输入到内存中
		}
		byteArrayOutPutStream.close();// 关闭输入流
		inputStream.close();// 关闭输入流

		return byteArrayOutPutStream.toByteArray();// 返回这个输入流的字节数组
	}
	 
	
	public static String getFilePath(String file){
			String absPath = "";
			String classpath =FileUtil.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
			
			try {
				classpath = java.net.URLDecoder.decode( classpath,"UTF-8");
				if( file.startsWith("com/") )
				{
//					同目录文件夹：classes，m2e-jee，test-classes
					int index = classpath.indexOf("/test-classes/");
					
					System.out.println("classpath:"+classpath);
//					path:/D:/daizd/Workspaces/MyEclipse%202015%20CI/migration/target/test-classes/
//					or: D:/tomcat/webapps/migration/WEB-INF/classes/
//					or: D:\AppData\Workspaces\MyEclipse 2015\message\bin\
					
					int binIndex = classpath.indexOf("/bin/");
					int classesIndex = classpath.indexOf("/classes/");
					
					if( binIndex != -1 ){
						classpath = classpath.substring(1, 
					    		index==-1 ? classpath.indexOf("/bin/") : index
					    	);//从路径字符串中取出工程路径
						if(file.indexOf("authority") != -1){
							absPath = classpath + "/" + file;
					    }else{
					    	absPath = classpath +"/bin/"+ file;
					    }
					}else if( classesIndex != -1 ){
						classpath = classpath.substring(1, 
					    		index==-1 ? classpath.indexOf("/classes/") : index
					    	);//从路径字符串中取出工程路径
						if(file.indexOf("authority") != -1){
							absPath = classpath + "/" + file;
					    }else{
					    	absPath = classpath +"/classes/"+ file;
					    }
					}			    
				}
				else if( file.startsWith("/WEB-INF/") )
				{
					
//					System.out.println("path:"+path);
					classpath = classpath.substring(0, classpath.indexOf("/WEB-INF/"));//从路径字符串中取出工程路径
					absPath = classpath + "/" + file;
				}
				else if( file.indexOf("/")==-1 )
				{
					absPath = classpath + "/" + file;
				}
				else
				{
					absPath = file;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
			return absPath;
	}
	
	private void sort(String s){
		String[] a = s.split("\r\n");
		List<String> l = Arrays.asList(a);
//		SortedSet set = new SortedSet(l);
//		set.addAll(l);
		java.util.Collections.sort(l);
		
//		Printer.printJSON(a);
		Printer.printJSON(l);
	}
    /**
     * DOC 写入信息.
     * 
     * @throws IOException
     */
    public static void write2File(InputStream is, String path ) throws IOException {
//    	StreamReader sr = new StreamReader( 你的Stream );
    	File file = new File(path);
    	FileOutputStream fos = new FileOutputStream(file);
    	byte[] b = new byte[1024];
    	while((is.read(b)) != -1){
    	fos.write(b);
    	}
    	is.close();
    	fos.close();
    }
    /**
     * String 写入信息.
     * 
     * @throws IOException
     */
    public static void write2File(String data, String path ) throws IOException {
//    	StreamReader sr = new StreamReader( 你的Stream );
    	File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		// true = append file
		FileWriter fileWritter = new FileWriter(path, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data);
		bufferWritter.close();
		fileWritter.close();
    }
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileUtil util = new FileUtil();
//		util.readFile("com/isoftstone/mq/mdm/xml4test/T_GB_ORGANIZATION.xml");/
		String s = FileUtil.read2String2("C:/Users/daizd/Desktop/ssh-lib_20161108.txt");
		String[] a = s.split("\r\n");
		List<String> l = Arrays.asList(a);
//		SortedSet set = new SortedSet(l);
//		set.addAll(l);
		java.util.Collections.sort(l);
		
		Printer.printJSON(a);
		Printer.printJSON(l);
	}

}
