package com.common.util;

/**
 * @ProjectName: com.common.util
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/5
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/5
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.*;


public class FTPSTest {


    public static void ftpsUtils(FtpsParam ftpsParam) throws NoSuchAlgorithmException {
        FTPSClient ftps;

        boolean error = false;

        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");

        ftps = new FTPSClient(ftpsParam.protocol);

        ftps.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));


        try {
            int reply;
            ftps.connect(ftpsParam.server, 22);
            System.out.println("Connected to " + ftpsParam.server + ".");
            reply = ftps.getReplyCode();

            // Set protection buffer size
            ftps.execPBSZ(0);
            // Set data channel protection to private
            ftps.execPROT("P");
            // Enter local passive mode
            ftps.enterLocalPassiveMode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftps.disconnect();
                System.err.println("FTP server refused connection");
                System.exit(1);
            }
        } catch (IOException e) {
            if (ftps.isConnected()) {
                try {
                    ftps.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            System.err.println("Could not connect to Server.");
            e.printStackTrace();
            System.exit(1);
        }
 _main:
        try {
            ftps.setBufferSize(1000);

            if(!ftps.login(ftpsParam.username, ftpsParam.password)) {
                ftps.logout();
                error = true;
                break _main;
            }

            System.out.println("Remote system is " + ftps.getSystemName());

            if(ftpsParam.binaryTransfer) {
                ftps.setFileType(FTP.BINARY_FILE_TYPE);
            }

            ftps.enterLocalPassiveMode();

            if(ftpsParam.storeFile) {
                InputStream input;

                input = new FileInputStream(ftpsParam.local);

                ftps.storeFile(ftpsParam.remote, input);

                input.close();
            } else {
                OutputStream output;

                output = new FileOutputStream(ftpsParam.local);

                ftps.retrieveFile(ftpsParam.remote, output);

                output.close();
            }

            ftps.logout();

        } catch (FTPConnectionClosedException e) {
            error = true;
            System.err.println("Server closed connection.");
            e.printStackTrace();
        } catch (IOException ioe) {
            error = true;
            ioe.printStackTrace();

        } finally {
            if (ftps.isConnected()) {
                try {
                    ftps.disconnect();
                } catch (IOException e) {

                }
            }
            System.exit(error ? 1 : 0);
        }



    }


    public static void putFile(String host,
                        int port,
                        String username,
                        String password,
                        String localFilename,
                        String remoteFilename) {
        try {
            FTPSClient ftpClient = new FTPSClient(false);
            // Connect to host
            ftpClient.connect(host, port);
            int reply = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(reply)) {

                // Login
                if (ftpClient.login(username, password)) {

                    // Set protection buffer size
                    ftpClient.execPBSZ(0);
                    // Set data channel protection to private
                    ftpClient.execPROT("P");
                    // Enter local passive mode
                    ftpClient.enterLocalPassiveMode();

                    // Store file on host
                    InputStream is = new FileInputStream(localFilename);
                    FTPFile[] files = ftpClient.listFiles();
                    for(FTPFile file : files) {
                        System.out.println(file.getName());
                    }
                    if (ftpClient.storeFile(remoteFilename, is)) {
                        is.close();
                    } else {
                        System.out.println("Could not store file");
                    }
                    // Logout
                    ftpClient.logout();

                } else {
                    System.out.println("FTP login failed");
                }

                // Disconnect
                ftpClient.disconnect();

            } else {
                System.out.println("FTP connect to host failed");
            }
        } catch (IOException ioe) {
            System.out.println("FTP client received network error");
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        FtpsParam ftpsParam = new FtpsParam();
        ftpsParam.setBinaryTransfer(true);
        ftpsParam.setPassword("Pernod201903");
        ftpsParam.setProtocol("SSL");
        ftpsParam.setServer("139.219.128.210");
        ftpsParam.setUsername("gcdb");
        ftpsParam.setStoreFile(false);
        ftpsParam.setLocal("C:\\jar\\test.txt");
        ftpsParam.setRemote("ftp/test.txt.txt");
        ftpsUtils(ftpsParam);



//        FtpsParam ftpsParam = new FtpsParam();
//        ftpsParam.setBinaryTransfer(true);
//        ftpsParam.setPassword("tianjian");
//        ftpsParam.setProtocol("TLS");
//        ftpsParam.setServer("127.0.0.1");
//        ftpsParam.setUsername("tianjian");
//        ftpsParam.setStoreFile(false);
//        ftpsParam.setLocal("C:\\jar\\tst.json");
//        ftpsParam.setRemote("sftp\\tst.json");
//        ftpsUtils(ftpsParam);
        //putFile("139.219.128.210", 22, "gcdb", "Pernod201903", "C:\\jar\\read.txt", "F:\\GCDB\\ftp\\read.txt");
    }

    public static class FtpsParam {
        private boolean storeFile = false;
        private boolean binaryTransfer = false;
        private boolean error = false;
        private String server = null;
        private String username = null;
        private String password = null;
        private String remote = null;
        private String local = null;
        private String protocol = "SSL";

        public boolean isStoreFile() {
            return storeFile;
        }

        public void setStoreFile(boolean storeFile) {
            this.storeFile = storeFile;
        }

        public boolean isBinaryTransfer() {
            return binaryTransfer;
        }

        public void setBinaryTransfer(boolean binaryTransfer) {
            this.binaryTransfer = binaryTransfer;
        }

        public boolean isError() {
            return error;
        }

        public void setError(boolean error) {
            this.error = error;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemote() {
            return remote;
        }

        public void setRemote(String remote) {
            this.remote = remote;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }
    }
}