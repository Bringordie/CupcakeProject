package logic;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;


public class SHA256test {
    
    /**
     * @author Bringordie - Frederik Braagaard
     * @return returns HexBinary SHA-256 encryption of an password used for
     * adding password to the DB but also used for login to check match.
    */
    public static String getHash(byte[] inputBytes) {
        String hashValue = "";
        
        try {
            //Static getInstance methos is called with hashing SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(inputBytes);
            byte[] digestedByte = md.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedByte).toLowerCase();
        } catch (Exception e) {
            System.err.println("An error has occured");
        }
        return hashValue;
    }
            
}
