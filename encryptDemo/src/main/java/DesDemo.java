import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DesDemo {
    public static void main(String[] args) throws Exception {
        //原文
        String input = "原文";

        //定義key
        //字數太少InvalidKeyException
        //DES is a block cipher that operates on data blocks of 64 bits in size.
        //64/8 = 8
        String key = "12345678";

        //定義transformation
        String transformation = "DES";

        //創建加密對象
        Cipher cipher = Cipher.getInstance(transformation);

        //加密初始化
        //參數1 -> mode(加密,解密...)
        //參數2 -> 加密規則 -> new SecretKeySpec(key的byte[], algorithm(=加密類型))
        String algorithm = "DES";
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        //加密方法 doFinal(原文.getBytes())
        //bytes 即為密文
        byte[] bytes = cipher.doFinal(input.getBytes());
        System.out.println(new String(bytes)); //%ת����_
        // 上面亂碼原因，編碼沒有對應字符
        // 解釋亂碼
//        for (byte b : bytes) {
//            System.out.println(b); //ASCII 沒有負數
//        }

        //創建base64
        String encryptDES = Base64.encodeBase64String(bytes);
        System.out.println("加密後： " + encryptDES);

        String ds = decrptDES(encryptDES, key, transformation, algorithm);
        System.out.println("解密後： " + ds);
    }

    /**
     * @param encryptDES     密文
     * @param key            密鑰
     * @param transformation 解密算法
     * @param algorithm      解密類型
     * @return
     */
    private static String decrptDES(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] bytes = cipher.doFinal(Base64.decodeBase64(encryptDES));
        return new String(bytes);
    }
}
