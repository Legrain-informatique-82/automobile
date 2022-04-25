package fr.careco.aaa.ws.clientsample;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Encoder;



public class TestHashJava {

	public static void main(String[] args) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			
			BASE64Encoder enc = new sun.misc.BASE64Encoder();
		
		byte[] digest = md.digest("lgrxxxxx".getBytes()); // Missing charset
		//String hex = Hex.encodeHexString(digest);
		String base64 = enc.encode(digest);
		System.out.println(base64);
		
		System.out.println("");
		System.out.println("VLICnfhhmeKqsofW4g8gD18GdffdsuPR1U6PBjf/1BgoeYW4=");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
