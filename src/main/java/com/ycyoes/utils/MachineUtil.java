package com.ycyoes.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineUtil {

    /**
     * IP地址的正则表达式,不支持IPV6
     */
    private final static Pattern IP_PATTERN = Pattern.compile("^[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}$");

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

    /**
     * 将ip值转为数值
     *
     * @param ip
     * @return
     */
    public static long converIpToDecimal(final String ip) {
        long iIp = 0;
        Matcher match = IP_PATTERN.matcher(ip);
        if (match.matches()) {
            int index = 0;
            String[] strArray = ip.split("\\.");
            for (int i = 0; i < strArray.length; i++) {
                iIp = iIp | Integer.parseInt(strArray[index++]);
                if (index != 4) {
                    iIp = iIp << 8;
                }
            }
        }
        else {
            iIp = -1;
        }
        return iIp;
    }

    public static void main(String[] args) {
       long dec = converIpToDecimal("192.168.0.1");
        System.out.println("dec: " + dec);
    }
}
