package com.example.v1.semojo.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
    public static String getIPAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
