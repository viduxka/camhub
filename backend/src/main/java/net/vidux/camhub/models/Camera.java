package net.vidux.camhub.models;

import java.util.Date;

public class Camera {
    private String name;
    private String ip;
    private String firmware;
    private Date lastSeen;

    public Camera(String name, String ip, String firmware, Date lastSeen) {
        this.name = name;
        this.ip = ip;
        this.firmware = firmware;
        this.lastSeen = lastSeen;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getFirmware() {
        return firmware;
    }

    public Date getLastSeen() {
        return lastSeen;
    }
}
