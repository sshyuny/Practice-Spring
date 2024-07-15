package com.sshyuny.sftp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sshyuny.sftp.domain.LocalFileInfo;
import com.sshyuny.sftp.domain.RemoteServerInfo;

@RestController
public class SftpController {
    
    @PostMapping("/sftp")
    public void upload(@RequestParam("file") MultipartFile file) throws Exception {

        LocalFileInfo localFileInfo = LocalFileInfo.builder()
                .fileName("test")
                .remoteDir("/Users/sshyuny/Documents")
                .multipartFile(file)
                .build();

        RemoteServerInfo remoteServerInfo = RemoteServerInfo.builder()
                .username("sshyuny")
                .password("")
                .host("localhost")
                .build();

        SftpSender sftpSender = SftpSender.create(remoteServerInfo);
        sftpSender.uploadFile(localFileInfo);
    }
}
