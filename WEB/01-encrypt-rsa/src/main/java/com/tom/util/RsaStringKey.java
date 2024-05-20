package com.tom.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class RsaStringKey {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		// Generate RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Specify key size
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Convert keys to base64 encoded strings
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // Print the keys
        System.out.println("Public Key: " + publicKeyString);
        System.out.println("Private Key: " + privateKeyString);
        
        
        // bytes to hex
        String publicKeyHex = bytesToHex(publicKey.getEncoded());
        String privateKeyHex = bytesToHex(privateKey.getEncoded());
        System.out.println("Hex Public Key: " + publicKeyHex);
        System.out.println("Hex Private Key: " + publicKeyHex);
        
        // hex to bytes
        String publicKeyStringRe = Base64.getEncoder().encodeToString(hexStringToByteArray(publicKeyHex));
        String privateKeyStringRe = Base64.getEncoder().encodeToString(hexStringToByteArray(privateKeyHex));
        
        System.out.println("Public Key: " + publicKeyStringRe);
        System.out.println("Private Key: " + privateKeyStringRe);
	}
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
	
	private static byte[] hexStringToByteArray(String hexString) {
        BigInteger bigInt = new BigInteger(hexString, 16);
        return bigInt.toByteArray();
    }
}