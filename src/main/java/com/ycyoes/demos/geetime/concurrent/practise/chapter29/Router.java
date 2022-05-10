package com.ycyoes.demos.geetime.concurrent.practise.chapter29;

import java.util.Objects;

public final class Router {
    private final String ip;
    private final Integer port;
    private final String iface;

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Router) {
            Router r = (Router)o;
            return iface.equals(r.iface) && ip.equals(r.ip) && port.equals(r.port);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, iface);
    }

    public String getIface() {
        return iface;
    }
}
