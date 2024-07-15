package com.sshyuny.sftp.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class LocalFileInfo {

    private MultipartFile multipartFile;
    private String remoteDir;
    private String fileName;
    
}
