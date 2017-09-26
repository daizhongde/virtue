package person.daizhongde.virtue.util.character;

public class CalcCharCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename1 = "2015-11-24 11:30:21";
//		String filename2 = "card_app_6ae_000000001_430200901000000000000.aft";
//		String fileheader="9!0005!1!05!201205211420_AB!430200901! ! ! !20121105!b8!05!20121105162216!00800332!card_app_6ae_000000001_430200901000000000000.bat! ! !";
		
//		String filerecord="田学军!01!640321197901101116! ! ! !05!00!公民身份号码与姓名一致，且存在照片! ! !1!1!6217970430000001766!20301231! ! ! ! ! !";
		int count1 = 0;
		for(int i=0,j=filename1.length(); i<j; i++){
			if(filename1.charAt(i)=='-'){
				count1++;
			}
		}
//		int count2 = 0;
//		for(int i=0,j=fileheader.length(); i<j; i++){
//			if(fileheader.charAt(i)=='!'){
//				count2++;
//			}
//		}
//		int count3 = 0;
//		for(int i=0,j=filerecord.length(); i<j; i++){
//			if(filerecord.charAt(i)=='!'){
//				count3++;
//			}
//		}
		System.out.println("count1:"+count1);
//		System.out.println("count2:"+count2);
//		System.out.println("count3:"+count3);
		System.out.println("filename1.length():"+filename1.length());
//		System.out.println("filename2.length():"+filename2.length());
	}

}
