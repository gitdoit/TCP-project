package tcp.util;

import java.nio.ByteBuffer;

public class NumberTool {
    private static ByteBuffer buffer=ByteBuffer.allocate(8);

    public static byte intToByte(int x){
            return (byte)x;
    }
    
    public static int byteToInt(byte b){
            return b & 0xFF;
    }

    public static int byteArrayToInt(byte[] b) {
            return  b[3]   & 0xFF    |
                    (b[2] & 0xFF) << 8 |
                    (b[1] & 0xFF) << 16 | 
                    (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a){
            return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
            };
       }

    public static byte[] longToBytes(long x){
        buffer.putLong(0,x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes){
        buffer.put(bytes,0,bytes.length);
        buffer.flip();//need flip   
        return buffer.getLong();
    }
}
