package web.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OpenCrypt {

    public static byte[] generateKey(String algorithm, int keySize)
            throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    public static String aesDecrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = hexToByteArray(msg);
        byte[] original = cipher.doFinal(encrypted);
        return new String(original);
    }

    public static String aesEncrypt(String msg, byte[] key) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = "AAAAAAAAAAAAAAAA"; // 전략 필요
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
                new IvParameterSpec(iv.getBytes())); // 암복호화 모드, 알고리즘, 복호화
        byte[] encrypted = cipher.doFinal(msg.getBytes()); // 암호화
        return byteArrayToHex(encrypted);

    }


    public static String getSHA256(String source, String salt)
            throws Exception { // source : 해싱할 값, salt : 안보여주기 위한 랜덤
        // 값
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(source.getBytes());
        md.update(salt.getBytes());
        byte[] byteData = md.digest(); // 섞어주세요!!
        String hashMsg = byteArrayToHex(byteData); // byte 배열을 16진수로 변환 시킨다
        return hashMsg;

    }

    public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() == 0) {
            return null;
        }

        byte[] ba = new byte[hex.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return ba;
    }

    // byte[] to hex
    public static String byteArrayToHex(byte[] ba) {
        if (ba == null || ba.length == 0) {
            return null;
        }

        StringBuffer sb = new StringBuffer(ba.length * 2);
        String hexNumber;
        for (int x = 0; x < ba.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & ba[x]);

            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        return sb.toString();
    }

}
