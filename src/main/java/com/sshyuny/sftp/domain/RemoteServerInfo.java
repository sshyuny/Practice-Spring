package com.sshyuny.sftp.domain;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class RemoteServerInfo {
    
    private String username;
    private String password;
    private String host;
    private final int PORT = 22;

}
