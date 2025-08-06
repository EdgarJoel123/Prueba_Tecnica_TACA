package com.test.technical.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class Encrypt {

    private static final String ENCRYPTATION_ALGORITHM = "MD5";
    private static final String FORMAT_ENCODE = "UTF-8";
    private static final String ALGORITHM = "DESede";
    private static final String SECRET_KEY = "SiXHKeyGenerat0r";


    public static String encrypt(String text) throws Exception
    {
        String base64EncryptedString = "";

        MessageDigest md = MessageDigest.getInstance( ENCRYPTATION_ALGORITHM );
        byte[] digestOfPassword = md.digest( SECRET_KEY.getBytes( FORMAT_ENCODE ));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plainTextBytes = text.getBytes( FORMAT_ENCODE );
        byte[] buf = cipher.doFinal(plainTextBytes);
        byte[] base64Bytes = Base64.getEncoder().encode(buf);
        base64EncryptedString = new String(base64Bytes);

        return base64EncryptedString;
    }

    public static String decrypt(String encryptedText) throws Exception
    {
        String base64EncryptedString = "";
        String encrypting="";
        encrypting=encryptedText.replaceAll("%2B", "+");
        encrypting=encrypting.replaceAll("%40", "@");
        encrypting=encrypting.replaceAll("%3A", ":");
        encrypting=encrypting.replaceAll("%24", "$");
        encrypting=encrypting.replaceAll("%2C", ",");
        encrypting=encrypting.replaceAll("%3B", ";");
        encrypting=encrypting.replaceAll("%3D", "=");
        encrypting=encrypting.replaceAll("%3F", "?");
        encrypting=encrypting.replaceAll("%2F", "/");
        byte[] message = Base64.getDecoder().decode(encrypting.getBytes( FORMAT_ENCODE ));
        MessageDigest md = MessageDigest.getInstance( ENCRYPTATION_ALGORITHM );
        byte[] digestOfPassword = md.digest(SECRET_KEY.getBytes( FORMAT_ENCODE ));
        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);

        Cipher decipher = Cipher.getInstance(ALGORITHM);
        decipher.init(Cipher.DECRYPT_MODE, key);

        byte[] plainText = decipher.doFinal(message);

        base64EncryptedString = new String( plainText, FORMAT_ENCODE );

        return base64EncryptedString;
    }
}
