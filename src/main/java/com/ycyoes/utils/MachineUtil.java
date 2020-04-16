package com.ycyoes.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MachineUtil {
    public static boolean isWindowsOS() {
        String osName = System.getProperty("os.name");
        if (StringUtils.isNotBlank(osName)) {
            return osName.toUpperCase().indexOf("WINDOWS") != -1;
        }
        return false;
    }

    public static String getIpAddr() {
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = netInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address && !address.isLoopbackAddress() && address.isSiteLocalAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (null != name) {
            String[] nameArr = name.split("@");
            if (null != nameArr && nameArr.length > 0) {
                return nameArr[0];
            }
        }
        return "";
    }
}
