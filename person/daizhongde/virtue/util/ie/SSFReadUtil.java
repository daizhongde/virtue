package person.daizhongde.virtue.util.ie;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SSFReadUtil {
	/**
	 * creates an {@link HSSFWorkbook} the specified OS filename.
	 */
	public static Workbook readFile(String filename, String uploadContentType) throws IOException {
		if (uploadContentType.toLowerCase().equals("application/vnd.ms-excel")) {
			return readHSSFFile(filename);  
	    }  
	    else if(uploadContentType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))  
	    {
	    	return readXSSFFile(filename);  
	    }  
        else  
        {  
            System.out.println("The file with the wrong format！");
            throw new RuntimeException("The file with the wrong format");
        }
	}
	
//	/**
//	 * creates an {@link HSSFWorkbook} the specified OS filename.
//	 */
//	public static Workbook readFile(File filename, String uploadContentType) throws IOException {
//		if (uploadContentType.toLowerCase().equals("application/vnd.ms-excel")) {
//			return readHSSFFile(filename.getAbsolutePath());  
//	    }  
//	    else if(uploadContentType.toLowerCase().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))  
//	    {
//	    	return readXSSFFile(filename.getAbsolutePath());  
//	    }  
//        else  
//        {  
//            System.out.println("The file with the wrong format！");
//            throw new RuntimeException("The file with the wrong format");
//        }
//	}
	/**
	 * creates an {@link HSSFWorkbook} the specified OS filename.
	 */
	public static HSSFWorkbook readHSSFFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new HSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
	/**
	 * creates an {@link HSSFWorkbook} the specified OS filename.
	 */
	public static XSSFWorkbook readXSSFFile(String filename) throws IOException {
	    FileInputStream fis = new FileInputStream(filename);
	    try {
	        return new XSSFWorkbook(fis);
	    } finally {
	        fis.close();
	    }
	}
}
