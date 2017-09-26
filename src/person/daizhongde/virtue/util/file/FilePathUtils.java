package person.daizhongde.virtue.util.file;

public class FilePathUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilePathUtils tt = new FilePathUtils();
		try {
			System.out.println("path:"+tt.getWebRoot());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;
	}

	public String getWebRoot() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			throw new IllegalAccessException("");
		}
		return path;
	}

	public String getShopXMLPath() {
		String path = null;
		try {
			path = getWebRoot();
			path = path + "shop/" + "shop.xml";
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return path;
	}
}
