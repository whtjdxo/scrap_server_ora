package sales.api.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
//import javax.servlet.http.HttpServletRequest;

//import net.owra.spr25.frame.base.BaseController;

public class StringPath {
	
	
	private static final String AES = "AES";  
	// 키값
	private static final String userMsg = "alclssusdkTLqkf!";  // 왜 이랬냐.. 누구냐? 첫... 개발자
	// 암호화
	private static byte[] encrypt(byte[] src, String key) {
		try {
	        Cipher cipher = Cipher.getInstance(AES);  
	        SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);  
	        cipher.init(Cipher.ENCRYPT_MODE, securekey);  
	        return cipher.doFinal(src);  

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}  
	
	// 암호화 복구
	private static byte[] decrypt(byte[] src, String key) {
		try {
			  Cipher cipher = Cipher.getInstance(AES);  
			  SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);//设置加密Key  
			  cipher.init(Cipher.DECRYPT_MODE, securekey);//设置密钥和解密形式  
			  return cipher.doFinal(src); 

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 2 => 16
	@SuppressWarnings("unused")
	private static String parseByte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();

	} 
	
	// 16 => 2
	private static byte[] parseHexStr2Byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("length error!");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;

	}
	
	
	//decrypt
	public final static String decrypt(String data, String key) {
		try {
			return new String(decrypt(parseHexStr2Byte(data.getBytes()), key));
		} catch (Exception e) {
		}
		return null;
	}  
	  
	//encrypt
	public final static String encrypt(String data , String key) {
		try {
			return parseByte2HexStr(encrypt(data.getBytes(), key));
		} catch (Exception e) {
		}
		return null;
	} 

	public static String getUserMsg(String sub_domain) {
		String rst = null;
		String key = StringInfo.getLocal(sub_domain);
		try {
			rst = decrypt(key, userMsg);
		} catch (Exception e) {
			System.out.println("Wrong!");
			e.printStackTrace();
		}

		return rst;
	}

	public static String getUsermsg() {
		return userMsg;
	}
}

