package person.daizhongde.virtue.codec;

/**
 * encryption algorithm interface,
 * all off this program's encryption algorithm are implement this interface
 * @author dzd
 *
 */
public interface EncryptionAlgorithm {
	public abstract String encode(String data);
	
	public abstract String decode(String data);
}
