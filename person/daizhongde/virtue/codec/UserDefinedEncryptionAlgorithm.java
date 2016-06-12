package person.daizhongde.virtue.codec;

/**
 * encryption algorithm interface,
 * all off this program's encryption algorithm are implement this interface
 * @author dzd
 *
 */
public class UserDefinedEncryptionAlgorithm implements EncryptionAlgorithm{
	public String encode(String data){
		//encrypt
		return data;
	}
	public String decode(String data){
		//encrypt
		return data;
	}
}
