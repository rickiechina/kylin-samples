import org.apache.kylin.jdbc.shaded.org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException;

public class Base64Demo {
    public static void main(String[] args) {
        String sourceStr = "ADMIN:KYLIN";
        String decodeStr = "QURNSU46S1lMSU4";
        try {
            // 编码
            byte[] encodeBase64 = Base64.encodeBase64(sourceStr.getBytes("UTF-8"));
            System.out.println("Encode Result: " + new String(encodeBase64));
            // 解码
            byte[] decodeBase64 = Base64.decodeBase64(decodeStr.getBytes("UTF-8"));
            System.out.println("Decode Result: " + new String(decodeBase64));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
