package com.yanzi.common.trace.util;

import java.net.InetAddress;

public class NetUtil {
    private NetUtil() {
    }

    /**
     * get local host
     * 
     * @return
     */
    public static String getLocalName() {
        String result = null;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
        }

        if (null != inetAddress) {
            result = inetAddress.getHostName();
        }

        return result;
    }

    /**
     * 
     * get local IP
     * 
     * @return
     */
    public static String getLocalIP() {
        String result = null;
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
        }

        if (null != inetAddress) {
            result = inetAddress.getHostAddress();
        }

        return result;
    }
}
