package com.panda.sharebike.kb260;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author KB260
 *         Created on  2018/1/22
 */

public class Aes {

    public static byte []sSrc = new  byte[]{6,1,1,1,36,36,36,36,36,36,36,36,36,36,36,36};
    public static byte []sKey = new  byte[]{65,122,104,111,110,103,49,53,49,50,51,56,48,48,48,48};
    public static byte []sKey16 = new  byte[]{06,01,01,01,12,15,69,00,23,32,36,16,44,04,54,51};

    /*参考以下 AES-128
    数据加密的 JAVA
    实现：*/

    public static byte[] Encrypt(byte[] sSrc, byte[] sKey) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] encrypted = cipher.doFinal(sSrc);
            return encrypted;
        } catch (Exception ex) {
            return null;
        }
    }

    /*参考以下 AES-128
    数据解密的 JAVA
    实现：*/

    public static byte[] Decrypt(byte[] sSrc, byte[] sKey) {
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] dncrypted = cipher.doFinal(sSrc);
            return dncrypted;
        } catch (Exception ex) {
            return null;
        }
    }

    //byte[]转十六进制String
    public static String byteArrayToHexStr(byte[] byteArray) {
        if (byteArray == null){
            return null;
        }
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[byteArray.length * 2];
        for (int j = 0; j < byteArray.length; j++) {
            int v = byteArray[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    //十六进制String转byte[]
    public static byte[] hexStrToByteArray(String str)
    {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return new byte[0];
        }
        byte[] byteArray = new byte[str.length() / 2];
        for (int i = 0; i < byteArray.length; i++){
            String subStr = str.substring(2 * i, 2 * i + 2);
            byteArray[i] = ((byte) Integer.parseInt(subStr, 16));
        }
        return byteArray;
    }
}
