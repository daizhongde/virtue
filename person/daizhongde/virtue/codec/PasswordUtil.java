package person.daizhongde.virtue.codec;

import org.springframework.context.ApplicationContext;

public class PasswordUtil {

	private EncryptionAlgorithm EA;
	
	public PasswordUtil(){
	}
	
	public PasswordUtil(EncryptionAlgorithm EA){
		this.EA = EA;
	}
	
	public EncryptionAlgorithm getEA() {
		return EA;
	}
	
	public void setEA(EncryptionAlgorithm EA) {
		this.EA = EA;
	}
	
	/**
	 * 校验输入的密码是否正确
	 * @param plaintext 明文
	 * @param cryptograph 密文
	 * @param algorithm 加密算法
	 * @return 
	 * true, if plaintext equals cryptograph
	 * <br/>
	 *  false, if plaintext not equals cryptograph
	 */
	public boolean check(String plaintext, String cryptograph, EncryptionAlgorithm EA){

		if( EA.encode(plaintext).equalsIgnoreCase( cryptograph ) )
			return true;
		else
			return false;
	}
	
	/**
	 * 校验输入的密码是否正确
	 * @param plaintext 明文
	 * @param cryptograph 密文
	 * @return 
	 * true, if plaintext equals cryptograph
	 * <br/>
	 *  false, if plaintext not equals cryptograph
	 */
	public boolean check(String plaintext, String cryptograph ){
//		System.out.println("plaintext:"+plaintext);
//		System.out.println("EA.encode(plaintext):"+EA.encode(plaintext));
//		System.out.println("cryptograph:"+cryptograph);
		
		if( EA.encode(plaintext).equalsIgnoreCase( cryptograph ) )
			return true;
		else
			return false;
	}
	
	public String encode(String plaintext){
		return EA.encode(plaintext);
	}
	
	public String decode(String cryptograph){
		return EA.decode( cryptograph );
	}
	
	public static PasswordUtil getFromApplicationContext(
			ApplicationContext ctx) {
		return (PasswordUtil) ctx.getBean("pwdUtil");
	}
	
}
