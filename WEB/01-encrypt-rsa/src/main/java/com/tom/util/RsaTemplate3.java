package com.tom.util;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RsaTemplate3 {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < publicKey.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
        }
        
        System.out.println(retString);
	}

}
