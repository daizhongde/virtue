package person.daizhongde.virtue.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
		return filename;
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
 */
	private static String readString4(String FILE_IN)
	{
		int len = 0;
		StringBuffer str = new StringBuffer("");
		File file = new File(FILE_IN);
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
