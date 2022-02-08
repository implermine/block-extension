package team.flow.blockextension.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

    public String encrypt(String string) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(string.getBytes(StandardCharsets.UTF_8));

        return this.bytesToHex(messageDigest.digest());
    }

    private String bytesToHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            builder.append(String.format("%02x",aByte));
        }
        return builder.toString();
    }
}
