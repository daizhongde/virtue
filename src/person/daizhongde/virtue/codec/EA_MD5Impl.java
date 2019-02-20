package person.daizhongde.virtue.codec;

import java.util.HashMap;
import java.util.Map;

/**
 * 32bit
 * encryption algorithm interface,
 * all off this program's encryption algorithm are implement this interface
 * @author dzd
 *
 */
public class EA_MD5Impl implements EncryptionAlgorithm{
	
	private long startMili = 0;
	private long endMili = 0;
	
	private MD5_Encoding MD5 = new MD5_Encoding();
	
	/**
	 * encrypt
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param plaintext 明文
	 */
	public String encode(String plaintext){
		//encrypt
		return MD5.getMD5ofStr(plaintext).toUpperCase();
	}
	/**
	 * decrypt
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param ciphertext 密文32位
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
									MD5.getMD5ofStr(plaintext).toUpperCase()
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
	 * decrypt,密文32位
	 * <p>
	 * plaintext, ciphertext, and keys. 明文、密文和密钥
	 * @param ciphertext 密文32位
	 */
	public String decodeInflexible(String ciphertext){
		Map map = new HashMap();
		map.put("698D51A19D8A121CE581499D7B701668", "111");
		map.put("BCBE3365E6AC95EA2C0343A2395834DD", "222");
		map.put("310DCBBF4CCE62F762A2AAA148D556BD", "333");
		map.put("550A141F12DE6341FBA65B0AD0433500", "444");
		map.put("15DE21C670AE7C3F6F3F1F37029303C9", "555");
		
		map.put("FAE0B27C451C728867A567E8C1BB4E53", "666");
		map.put("F1C1592588411002AF340CBAEDD6FC33", "777");
		map.put("0A113EF6B61820DAA5611C870ED8D5EE", "888");
		map.put("B706835DE79A2B4E80506F582AF3676A", "999");
		map.put("C6F057B86584942E415435FFB1FA93D4", "000");
		
		map.put("202CB962AC59075B964B07152D234B70", "123");
		map.put("65DED5353C5EE48D0B7D48C591B8F430", "132");
		map.put("979D472A84804B9F647BC185A877A8B5", "213");
		map.put("9B04D152845EC0A378394003C96DA594", "231");
		map.put("950A4152C2B4AA3AD78BDD6B366CC179", "312");
		map.put("CAF1A3DFB505FFED0D024130F58C5CFA", "321");
		
		
		map.put("96E79218965EB72C92A549DD5A330112", "111111");
		map.put("E3CEB5881A0A1FDAAD01296D7554868D", "222222");
		map.put("1A100D2C0DAB19C4430E7D73762B3423", "333333");
		map.put("73882AB1FA529D7273DA0DB6B49CC4F3", "444444");
		map.put("5B1B68A9ABF4D2CD155C81A9225FD158", "555555");
		
		map.put("F379EAF3C831B04DE153469D1BEC345E", "666666");
		map.put("F63F4FBC9F8C85D409F2F59F2B9E12D5", "777777");
		map.put("21218CCA77804D2BA1922C33E0151105", "888888");
		map.put("52C69E3A57331081823331C4E69D3F2E", "999999");
		map.put("670B14728AD9902AECBA32E22FA4F6BD", "000000");
		
		map.put("D437DF002F7A5C8555C107AF8A643977", "123456");
		map.put("4D1C61B1DF4F3C589AA06177EE125D32", "246135");
		map.put("21232F297A57A5A743894A0E4A801FC3", "admin");
		
		
		map.put("57BA172A6BE125CCA2F449826F9980CA", "123qweasd");
		
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
		

		String[] sa = {"111","222","333","444","555",
				"666","777","888","999","000",
				"123","132","213","231","312","321",
				"111111","222222","333333","444444","555555",
				"666666","777777","888888","999999","000000",
				"135246","246135","admin","jjk_02","123qweasd"};
		
//		MD5_Encoding MD5 = new MD5_Encoding();
//		for(int i=0, j=sa.length; i<j; i++ ){
//			System.out.println(sa[i]+":"+MD5.getMD5ofStr(sa[i]).toUpperCase());
//		}
		
		String plaintext="", ciphertext="7abcd6393f501860d571af6095bafeae";
		EA_MD5Impl d = new EA_MD5Impl();
		plaintext = d.decode(ciphertext);
	    
		System.out.println("plaintext:"+plaintext);
	}
}
