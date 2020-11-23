package net.vidux.camhub.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Camera {
    private final String name;
    private String ip;
    private String firmware;
    private Date lastSeen;
}
