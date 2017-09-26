package person.daizhongde.virtue.util.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import person.daizhongde.virtue.util.character.CharacterConvert;
import person.daizhongde.virtue.util.collection.MapUtils;

public class FileNameUtil {
	/**
	 * file rename 
	 * <p>
	 * substring caoqun image tie(blog) title
	 * @param source
	 * @param str
	 * @param orientation front\ back
	 * @throws IOException
	 */
	public void renameFile(File file, String str, String orientation, boolean isStrInRet) throws IOException {
//		java.io.File file = new java.io.File(source);
		String absPath = file.getAbsolutePath();
		String oldFileName = file.getName();
		String fileEx = FileUtil.getExtensionName( oldFileName );
		String parentDirectory = absPath.substring(0,absPath.indexOf(oldFileName));
		
		String oldFileNameNoEx = FileUtil.getFileNameNoEx( oldFileName );
		String newFileNameNoEx = "";
		String newFileName = "";
		
		if("front".trim().toLowerCase().equalsIgnoreCase(orientation))
		{
			if(isStrInRet){
				newFileNameNoEx = oldFileNameNoEx.substring(0, oldFileNameNoEx.indexOf(str)+str.length() );
			}else{
				newFileNameNoEx = oldFileNameNoEx.substring(0, oldFileNameNoEx.indexOf(str));
			}
		}
		else if("back".trim().toLowerCase().equalsIgnoreCase(orientation))
		{
			if(isStrInRet){
				newFileNameNoEx = oldFileNameNoEx.substring( oldFileNameNoEx.indexOf(str) );
			}else{
				newFileNameNoEx = oldFileNameNoEx.substring( oldFileNameNoEx.indexOf(str)+ str.length() );
			}
		}
		else
		{
			newFileNameNoEx = oldFileNameNoEx;
		}
		if(file.isDirectory()){
			newFileName = newFileNameNoEx;
		}else{
			newFileName = newFileNameNoEx + "."+fileEx;
		}
		System.out.println( parentDirectory + newFileName);
		if(!file.exists()){
            return;//重命名文件不存在
        }
		File newfile =new File( parentDirectory + newFileName);
		if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            System.out.println(newFileName+"已经存在！"); 
        else{
    		file.renameTo( newfile );
        }
	}
	/**
	 * 重命名指定目录下所有文件
	 * @param file
	 * @param str
	 * @param orientation
	 * @param isStrInRet
	 * @throws IOException
	 */
	public void renameFiles(File file, String str, String orientation, boolean isStrInRet) throws IOException {
		FileNameUtil util = new FileNameUtil();
		File[] tempList = file.listFiles();
		System.out.println("该目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
//			if (tempList[i].isFile()) {
//				System.out.println("文     件：" + tempList[i]);
//			}
//			if (tempList[i].isDirectory()) {
//				System.out.println("文件夹：" + tempList[i]);
//			}
			util.renameFile( tempList[i], "P]", "front", true );
		}
	}
	/**
	 * validate file name 
	 * <p>for caoqun title
	 * @param fileName
	 * @return
	 */
	public static boolean isValidFileName(String fileName) {
		if (fileName == null || fileName.length() > 255)
			return false;
		else
			return fileName
					.matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$");
	}

	/**
	 * file rename 
	 * <p>
	 * substring caoqun image tie(blog) title
	 * @param source
	 * @param str
	 * @param orientation front\ back
	 * @throws IOException
	 */
	public void renameFileByConvert(File file, Map<String,String> map ) throws IOException {
//		java.io.File file = new java.io.File(source);
		String absPath = file.getAbsolutePath();
		String oldFileName = file.getName();
		String fileEx = FileUtil.getExtensionName( oldFileName );
		String parentDirectory = absPath.substring(0,absPath.indexOf(oldFileName));
		
		String oldFileNameNoEx = FileUtil.getFileNameNoEx( oldFileName );
		String newFileNameNoEx = "";
		String newFileName = "";
		
		newFileNameNoEx = map.get(oldFileNameNoEx);
		
		if(file.isDirectory()){
			newFileName = newFileNameNoEx;
		}else{
			newFileName = newFileNameNoEx + "."+fileEx;
		}
		System.out.println( parentDirectory + newFileName);
		if(!file.exists()){
            return;//重命名文件不存在
        }
		File newfile =new File( parentDirectory + newFileName);
		if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            System.out.println(newFileName+"已经存在！"); 
        else{
    		file.renameTo( newfile );
        }
	}
	
	/**
	 * file rename 
	 * <p>
	 * simple rename directory
	 * @param source
	 * @param str
	 * @param orientation front\ back
	 * @throws IOException
	 */
	public void renameFile(File file_source, File file_target ) throws IOException {
		String oldAbsPath = file_source.getAbsolutePath();
//		String oldFileName = file_source.getName();
//		String oldFileEx = FileUtil.getExtensionName( oldFileName );
//		String oldParentDirectory = oldAbsPath.substring(0,oldAbsPath.indexOf(oldFileName));
//		String oldFileNameNoEx = FileUtil.getFileNameNoEx( oldFileName );
//				
		String newAbsPath = file_target.getAbsolutePath();
//		String newFileName = file_target.getName();
//		String newFileEx = FileUtil.getExtensionName( newFileName );
//		String newParentDirectory = newAbsPath.substring(0,newAbsPath.indexOf(newFileName));
//		String newFileNameNoEx = FileUtil.getFileNameNoEx( newFileName );
//		
//		if(file_source.isDirectory()){
//			newFileName = newFileNameNoEx;
//		}else{
//			newFileName = newFileNameNoEx + "." + newFileEx;
//		}
//		System.out.println( "old:"+oldParentDirectory + oldFileName);
//		System.out.println( "new:"+newParentDirectory + newFileName);
//		System.out.println( "old:"+ oldAbsPath );
//		System.out.println( "new:"+ newAbsPath );
		
		if(!file_source.exists()){
			 System.out.println("源："+oldAbsPath+"不存在！"); 
            return;//重命名文件不存在
        }

		if(file_target.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
            System.out.println(newAbsPath+"已经存在！"); 
        else{
        	file_source.renameTo( file_target );
        }
	}
	
	public String calcDirectoryByParameter(int q, int range){
		String subDirectory = "";
		int begin, end;
		int n = (q-1)%range;
		begin = q-n;
		end = begin + range-1;
//		System.out.println("q:"+q+",n:"+n+",begin:"+begin+",end:"+end );
		subDirectory = String.valueOf(begin)+"-"+String.valueOf(end);
		return subDirectory;
	}
	/**
	 * 把字符串转换为合法的文件名 
	 * <p>eg:title-> filename
	 * <br>matches("[^\\s\\\\/:\\*\\?\\\"<>\\|](\\x20|[^\\s\\\\/:\\*\\?\\\"<>\\|])*[^\\s\\\\/:\\*\\?\\\"<>\\|\\.]$")
	 * @param s
	 * @return
	 */
	public String cvS2legalFilename( String s ){
		s = s.trim();
		if( !isValidFileName(s) ){
//			CharacterConvert.testCharSet(s);
			s = s.replaceAll("[\\s\\\\/:\\*\\?\\\"<>\\|\\.]", "-").trim();
		}
		return s;
	}
	/**
	 * 
	 * @param url
	 * @param suffixArr eg: 's /cC/f
	 * @return
	 */
	public String cvUrl2legalFilename( String url, String[] suffixArr ){
		
		for(String s : suffixArr){
			int index = url.indexOf( s );
			if( index !=-1 ){
				url = url.substring( 0, index );
			}
		}
		String fileName = url.substring(url.lastIndexOf("/")+1);
		return cvS2legalFilename(fileName);
	}
	/**
	 *  D:/usr/http/[自创]第一贴 夫妻间的真实诱惑  [16P]113
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileNameUtil u = new FileNameUtil();
		String url = "http://im1.shutterfly.com/media/47a0db05b3127ccefa65756a409c00000030O10AYsXDNi3aM2YPbz4M/cC/f%3D0/ps%3D50/r%3D1/rx%3D550/ry%3D400/";
		String[] arr = {"'s","/cC/f"};
		String r = u.cvUrl2legalFilename(url, arr);
		System.out.println("r:"+r);

		
	}
}
