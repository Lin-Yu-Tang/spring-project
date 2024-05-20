package com.tom.util;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource.PSpecified;

public class RSAUtils {

	private static String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtWd56rQ/e0VPehkc73Fybb88SQOPgyOhW7pOOFn7EtFr31zAcjlbtlu4tAc6t7WOSRF2GsflV/Uky/q4elBEA1zysAjjrR2Mq8v+5ZzdBcwv+9O1h1G3l9A4/unqk901xK9x+Sm4TgqYOX6+m5jVWbayHnwfgToJ1Ds2UbB5oPo1OWWKfyzQRfbjHos5cHpyKJD3NZhDLV3YnIdOFuJOOlJzQKCt+gFsUyQTMLHXlC8Y6cRqpL0wrWYMfhpdTMtz61kVoj1vyh9O2plLvXcduupqKE9IULv9JLx/IVa4dJMGfsvHtMw8kFurT75n3mz0LqJ7/WaJrtyT2LYt3mebNwIDAQAB";
    private static String privateKeyStr = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1Z3nqtD97RU96GRzvcXJtvzxJA4+DI6Fbuk44WfsS0WvfXMByOVu2W7i0Bzq3tY5JEXYax+VX9STL+rh6UEQDXPKwCOOtHYyry/7lnN0FzC/707WHUbeX0Dj+6eqT3TXEr3H5KbhOCpg5fr6bmNVZtrIefB+BOgnUOzZRsHmg+jU5ZYp/LNBF9uMeizlwenIokPc1mEMtXdich04W4k46UnNAoK36AWxTJBMwsdeULxjpxGqkvTCtZgx+Gl1My3PrWRWiPW/KH07amUu9dx266mooT0hQu/0kvH8hVrh0kwZ+y8e0zDyQW6tPvmfebPQuonv9Zomu3JPYti3eZ5s3AgMBAAECggEATkDP/+Y3iEay7TnOGKB4u00GhXyquOhy3+3zdi2iuFBs11LclamWnYjdmZOQydThBzYoG49lqNbQQ8npMKJVZJoA+t0q0e/wUd/TGlvboHefr2N5aGFg8HVbUOO5VIXVfv4sDU7o+Ylqd0rskJSSXHwKREpJEObnfIz6dqzJGx555VcWVz3QNU2Vc1qn248nwpWqG2AtGeapeM7H3RD6AO9JpUzU4PL1V6oWL1dnc6ZTv7aXeRvGVw0wtK2Y1D0FLcwyXJNIL1QCZHxKggVNeL041T7mPZy0OAAqiXenvf7sbkcNHziWZOjW65IGx454Zep2JrNP+uWhUPxeoPmO0QKBgQDmePLUKTCCaXw+ozUwkDu5RGyBieMGjRfpG+OHI4W89Vye4gckFaKZK2ZjaqJV8wz6ZFfjw6uebpYVpEK5W9+aOjm31MoqKd1CFM6NEBM/Z5fXHaEaES8azB2+Q0I8LXY/MuTt5jUaRhLRkRpCwNlA5CwQ3mbaagnqu21q2QpJaQKBgQDJfzJE6EMaUWI//yI++ElXUtatmrxlKmKut5yXHQIKta1NFEwiDtKeytbxZul+k8wExI3Go8DnQd5Dv8BkHMScPq3nMwtBw1FrS6h4jQdK41k5i3dR25ceOyIDTxZd9s4DKvIKGJL41wcVhgT4H7rWYFdbwxDmyNMFWGGj1OiLnwKBgCWW+nwSD8Bldgc9VVKLY8JO36etFqMd5hV2IAENkSWFm0qqsOF4yeFTi8qRPNLp5mb8cL1MT7RycvYqqVtUGb8s2fcatLJ1y4mYnSuywIDp44EJaxo2TUmAmyVKEQaBn7SlF2OZ1XVokm8tzGuwyn5j+K/zpjDfdKidUzZHGf9pAoGBAIN+175yWMBHFjgvdxjjDXHIZMMctQ/MTmQ4/R781kVI9NTVhy2+wKLSOt2FTKHHMCB4aRGV8iC5MqTB8Xsj7wcMPI8iF5mOLoc387TgTD8KCUNtiavJA2C+NF82cKFXnHAiyZvfF6L/KOdZ88o9Yr/eVYRT06bYriXR3L5/Bld/AoGBAMvMo3phSWLY3O6Tw10Jq5YoLJO5K1iZ+G7eBT9x4hoqL5ENLpDZy/xUn5wb9NiY8vw/y+9TL+kHSScRCFYvTxej6rrezzzxYxh43CUJZxILU3Tqhmh8CZ/AHcJl5TQBeiPEu1n4le0HzSjkyKfMFLHgA19AhqVobwjF0v5BOWKd";

	
    static void test() throws Exception {
//		genKeyPair();
//		System.out.println(publicKeyStr.length());
//		System.out.println(privateKeyStr.length());
		String encode = encodeBase64("helloworld1234");
		System.out.println("encode: " + encode);
		String decodeBase64 = decodeBase64(encode);
		System.out.println("decode: " + decodeBase64);
    }
    
    static void genKeyPair() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048); // speedy generation, but not secure anymore
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		
        System.out.println("Public Key: " + publicKeyString);
        System.out.println("Private Key: " + privateKeyString);
	}
	
	static String encodeBase64(String message) throws Exception {
		Cipher oaepFromAlgo = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
		
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr.getBytes()));
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
        
		oaepFromAlgo.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] ct = oaepFromAlgo.doFinal(message.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(ct);
	}
	
	static String decodeBase64(String base64Message) throws Exception {
		Cipher oaepFromInit = Cipher.getInstance("RSA/ECB/OAEPPadding");
		OAEPParameterSpec oaepParams = new OAEPParameterSpec("SHA-256", "MGF1", new MGF1ParameterSpec("SHA-1"), PSpecified.DEFAULT);
		
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		oaepFromInit.init(Cipher.DECRYPT_MODE, privateKey, oaepParams);
		byte[] pt = oaepFromInit.doFinal(Base64.getDecoder().decode(base64Message));
		
		return new String(pt, StandardCharsets.UTF_8);
	}

	
}
