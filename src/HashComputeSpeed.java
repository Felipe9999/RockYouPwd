import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class HashComputeSpeed {
    public static void main(String[] args) {
        String[] randNumbers = new String[50];
        String[] randHashes = new String[50];
        Random random = new Random();
        random.setSeed(69420); //needed  to make sure we always use the same ones
        for (int i = 0; i < randNumbers.length; i++) {
            randNumbers[i] = String.valueOf(random.nextInt(100000000));
            System.out.println("Value "+(i+1)+": "+randNumbers[i]);
        }
        for (int i = 0; i < randNumbers.length; i++) {
            randHashes[i] = hash(randNumbers[i]);
        }
        System.out.println("Starting...");
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 100000000; i++) {
            String hashedpwd = hash(String.valueOf(i));
            for (int j = 0; j < randHashes.length; j++) {
                if(hashedpwd.equals(randHashes[j])){
                    System.out.println("Hash "+hashedpwd+" found, its password is: "+i);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Finished. (took "+((float)(endTime-startTime))/1000+" seconds)");
    }
    public static String hash(String texto){
        // ESTE CÓDIGO NO SE DEBE TOCAR
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("How did we get here"); //try-catch needed so compiler wont complain, we know sha-256 is always gonna exist
        }
        byte[] hashBytes = digest.digest(texto.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
