package wallet;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
	 
public class AES256 {

	  private static final String SALT = "CryptoFamilyMiner";
	 
	  public static String encrypt(String strToEncrypt, String KEY) {
	    try {
	      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	      IvParameterSpec ivspec = new IvParameterSpec(iv);
	 
	      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	      KeySpec spec = new PBEKeySpec(KEY.toCharArray(), SALT.getBytes(), 65536, 256);
	      SecretKey tmp = factory.generateSecret(spec);
	      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	 
	      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	      cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	      return Base64.getEncoder()
	          .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
	    } catch (Exception e) {
	      System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	  }
	  
	  public static String decrypt(String strToDecrypt, String KEY) {
		    try {
		      byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		      IvParameterSpec ivspec = new IvParameterSpec(iv);
		 
		      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		      KeySpec spec = new PBEKeySpec(KEY.toCharArray(), SALT.getBytes(), 65536, 256);
		      SecretKey tmp = factory.generateSecret(spec);
		      SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		 
		      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		      cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
		      return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		    } catch (Exception e) {
		      System.out.println("Error while decrypting: " + e.toString());
		    }
		    return null;
		  }
	  
	  public static void main(String[] args) throws NoSuchAlgorithmException {
		    String originalString = "xcWSF/r6TG6ObJsW5ur6xi9ZYrKvLVtByeWzeeSbB9vh8YOxwRKeJ2XY563wNerutcE9DciVrWsU7papXZgAs5FJ54Qcv5AWI7ai618Juksxhclt+OXmZJf9RUBjZLpTSPP9QmRf0DL6EfPnpJDoaGJBhhsB+bNvCiGB4tOWhT1JdyweWFPp4VR4mMkhPDPLySqJ3iDTyRB4MpLlHsfHgg==";
		 
		    String encryptedString = AES256.encrypt(originalString, "t");
		    String decryptedString = null;
		    decryptedString = AES256.decrypt(originalString, "no");
		 
		    //1q3yV7YG2IhwX6L0U1EGg24vfJ6WucpqIqPUR01zups=
		    //Tg2Nn7wUZOQ6Xc+1lenkZTQ9ZDf9a2/RBRiqJBCIX6o=
		    System.out.println(originalString);
		    System.out.println(decryptedString);
		   // System.out.println(decryptedString);
		  }
	}