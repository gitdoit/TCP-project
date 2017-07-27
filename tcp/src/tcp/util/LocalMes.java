/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tcp.util;

import java.io.IOException;
import java.net.InetAddress;

public class LocalMes {
    private LocalMes(){};
    
    public static String getLocalIPAddress(){
        String localIP = null;
        try
        {
            localIP = InetAddress.getLocalHost().getHostAddress();
        }
        catch(IOException e)
        {
             localIP = "网络链接异常";
        }
        return localIP;
    }
}
