package person.daizhongde.virtue.codec;

import java.util.HashMap;
import java.util.Map;

/**
 * 40bit
 * encryption algorithm interface,
 * all off this program's encryption algorithm are implement this interface
 * @author dzd
 *
 */
public class EA_SHA1Impl implements EncryptionAlgorithm{
	
	private long startMili = 0;
	private long endMili = 0;
	
	private SHA1_Encoding SHA1 = new SHA1_Encoding();
	
	/**
	 * encrypt,
	 * <br>ciphertext密文40位
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param plaintext 明文
	 */
	public String encode(String plaintext){
		//encrypt
		return SHA1.toSHA1(plaintext).toUpperCase() ;
	}
	/**
	 * decrypt,
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param ciphertext 密文40位
	 */
	public String decode(String ciphertext){
		startMili=System.currentTimeMillis();// 当前时间对应的毫秒数
		

//		String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
		char[] cs = {'0','1','2','3','4','5','6','7','8','9'};
//		char[] cs = {'0','1','2','3','4','5','6','7','8','9',
//				'a','b','c','d','e','f','g',
//				'h','i','j','k','l','m','n',
//				'o','p','q',    'r','s','t',
//				'u','v','w',    'x','y','z',
//				'_'};
				
		String plaintext="";
		char[] leftCS;//剩下的字符
		
//		char[] tempCS;
//		tempCS =Arrays.copyOfRange(cs, i+1, j);
		
		for( int i=0,j=cs.length; i<j; i++ ){
			plaintext=String.valueOf( cs[i] );//密码明文第一位
			
			for(int m=0,n=cs.length; m<n; m++ ){
				plaintext+=String.valueOf( cs[m] );//密码明文第2位
				
				for(int a=0,b=cs.length; a<b; a++ ){
					plaintext+=String.valueOf( cs[a] );//密码明文第3位
					
					for(int g=0,k=cs.length; g<k; g++ ){
						plaintext+=String.valueOf( cs[g] );//密码明文第4位
						
						for(int c=0,d=cs.length; c<d; c++ ){
							plaintext+=String.valueOf( cs[c] );//密码明文第5位
							
							for(int e=0,f=cs.length; e<f; e++ ){
								plaintext+=String.valueOf( cs[e] );//密码明文第6
								if(
									SHA1.toSHA1( plaintext ).toUpperCase()
									.equalsIgnoreCase(ciphertext) 
								)
								{
									endMili=System.currentTimeMillis();
									System.out.println("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
								
//									System.out.println("plaintext:"+plaintext);
									return plaintext;
								}
								else
								{
									plaintext=plaintext.substring(
											0,
											plaintext.length()-1
											);
								}
							}//for 6
							plaintext=plaintext.substring(
									0,
									plaintext.length()-1
									);
						}//for 5
						plaintext=plaintext.substring(
								0,
								plaintext.length()-1
								);
					}//for 4
					plaintext=plaintext.substring(
							0,
							plaintext.length()-1
							);
				}//for 3
				plaintext=plaintext.substring(
						0,
						plaintext.length()-1
						);
			}//for 2
			plaintext=plaintext.substring(
					0,
					plaintext.length()-1
					);
		}//for 1
		
		endMili=System.currentTimeMillis();
	    System.out.println("总耗时为："+(endMili-startMili)+"毫秒，"+(endMili-startMili)/1000+"秒!");
	
		return "原文非6位数字组合";
	}
	/**
	 * decrypt
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param ciphertext 密文40位
	 */
	public String decodeInflexible(String ciphertext){
		Map map = new HashMap();
		map.put("6216F8A75FD5BB3D5F22B6F9958CDEDE3FC086C2", "111");
		map.put("1C6637A8F2E1F75E06FF9984894D6BD16A3A36A9", "222");
		map.put("43814346E21444AAF4F70841BF7ED5AE93F55A9D", "333");
		map.put("9A3E61B6BCC8ABEC08F195526C3132D5A4A98CC0", "444");
		map.put("CFA1150F1787186742A9A884B73A43D8CF219F9B", "555");
		
		map.put("CD3F0C85B158C08A2B113464991810CF2CDFC387", "666");
		map.put("FC7A734DBA518F032608DFEB04F4EEB79F025AA7", "777");
		map.put("EAA67F3A93D0ACB08D8A5E8FF9866F51983B3C3B", "888");
		map.put("AFC97EA131FD7E2695A98EF34013608F97F34E1D", "999");
		map.put("8AEFB06C426E07A0A671A1E2488B4858D694A730", "000");
		
		map.put("40BD001563085FC35165329EA1FF5C5ECBDBBEEF", "123");
		map.put("91DFDE1D6E005E422F64A59776234F1F4C80B5E4", "132");
		map.put("19187DC98DCE52FA4C4E8E05B341A9B77A51FD26", "213");
		map.put("EADC1DD8FC279583D5552700AE5D248E3FA123BD", "231");
		map.put("A93C168323147D1135503939396CAC628DC194C5", "312");
		map.put("5F6955D227A320C7F1F6C7DA2A6D96A851A8118F", "321");
		
		
		map.put("3D4F2BF07DC1BE38B20CD6E46949A1071F9D0E3D", "111111");
		map.put("273A0C7BD3C679BA9A6F5D99078E36E85D02B952", "222222");
		map.put("77BCE9FB18F977EA576BBCD143B2B521073F0CD6", "333333");
		map.put("42CFE854913594FE572CB9712A188E829830291F", "444444");
		map.put("B7C40B9C66BC88D38A59E554C639D743E77F1B65", "555555");
		
		map.put("1411678A0B9E25EE2F7C8B2F7AC92B6A74B3F9C5", "666666");
		map.put("FBA9F1C9AE2A8AFE7815C9CDD492512622A66302", "777777");
		map.put("1F82C942BEFDA29B6ED487A51DA199F78FCE7F05", "888888");
		map.put("1F5523A8F535289B3401B29958D01B2966ED61D2", "999999");
		map.put("C984AED014AEC7623A54F0591DA07A85FD4B762D", "000000");
		
		map.put("2435FFE718B1A45742F70673AE202EA0524BB5E0", "123456");
		map.put("03036450BD2172B4CE7AA641C95D2A2E21E86329", "246135");
		map.put("D033E22AE348AEB5660FC2140AEC35850C4DA997", "admin");
		
		//decrypt
		if( map.containsKey( ciphertext.toUpperCase() ) )
		{
			return map.get(ciphertext).toString();
		}
		return ciphertext;
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

		if( this.encode(plaintext).equalsIgnoreCase( cryptograph ) )
			return true;
		else
			return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//加密
		String[] sa = {"111","222","333","444","555",
				"666","777","888","999","000",
				"123","132","213","231","312","321",
				"111111","222222","333333","444444","555555",
				"666666","777777","888888","999999","000000",
				"135246","246135","admin","jjk_02","virtue123"};
		
		SHA1_Encoding SHA1 = new SHA1_Encoding();
		for(int i=0, j=sa.length; i<j; i++ ){
			System.out.println(sa[i]+":"+SHA1.toSHA1( sa[i] ).toUpperCase());
		}
		
		//解密
//		String plaintext="", ciphertext="40BD001563085FC35165329EA1FF5C5ECBDBBEEF";
//		EA_SHA1Impl d = new EA_SHA1Impl();
//		plaintext = d.decode(ciphertext);
//		System.out.println("plaintext:"+plaintext);
		

	}
}
