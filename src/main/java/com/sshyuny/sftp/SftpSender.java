package com.sshyuny.sftp;

import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sshyuny.sftp.domain.LocalFileInfo;
import com.sshyuny.sftp.domain.RemoteServerInfo;

public class SftpSender {

    private RemoteServerInfo remoteServerInfo;
    private Session session;
    private ChannelSftp channelSftp;

    public static SftpSender create(RemoteServerInfo remoteServerInfo) {
        SftpSender sftpUseCase = new SftpSender();
        sftpUseCase.setRemoteServerInfo(remoteServerInfo);
        return sftpUseCase;
    }
    private void setRemoteServerInfo(RemoteServerInfo remoteServerInfo) {
        this.remoteServerInfo = remoteServerInfo;
    }

    public void uploadFile(LocalFileInfo localFileInfo) throws Exception {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(remoteServerInfo.getUsername(), remoteServerInfo.getHost(), remoteServerInfo.getPORT());
            session.setPassword(remoteServerInfo.getPassword());

            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();

            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // 파일 업로드
            try (InputStream inputStream = localFileInfo.getMultipartFile().getInputStream()) {
                channelSftp.put(inputStream, localFileInfo.getRemoteDir() + "/" + localFileInfo.getFileName());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            if (channelSftp != null) {
                channelSftp.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

    
}
