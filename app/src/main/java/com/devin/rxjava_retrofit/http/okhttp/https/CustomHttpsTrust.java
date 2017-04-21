package com.devin.rxjava_retrofit.http.okhttp.https;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class CustomHttpsTrust {

    public SSLSocketFactory sSLSocketFactory;
    public X509TrustManager x509TrustManager;

    /**
     * 信任全部https
     */
    public CustomHttpsTrust() {
        this(null);
    }

    /**
     * 信任指定证书的https
     *
     * @param certificates
     */
    public CustomHttpsTrust(InputStream[] certificates) {
        this(certificates, null, null);
    }

    /**
     * 双向证书的验证,极少数的应用需要双向证书验证，比如银行、金融类
     *
     * @param certificates
     * @param bksFile
     * @param password
     */
    public CustomHttpsTrust(InputStream[] certificates, InputStream bksFile, String password) {

        try {
            TrustManager[] trustManagers = prepareTrustManager(certificates);
            KeyManager[] keyManagers = prepareKeyManager(bksFile, password);
            if (trustManagers == null) {
                x509TrustManager = new UnSafeTrustManager();
            } else {
                for (TrustManager trustManager :
                        trustManagers) {
                    if (trustManager instanceof X509TrustManager) {
                        x509TrustManager = (X509TrustManager) trustManager;
                        break;
                    }
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagers, new TrustManager[]{x509TrustManager}, null);
            sSLSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static TrustManager[] prepareTrustManager(InputStream[] inputStreams) {

        if (inputStreams == null || inputStreams.length == 0) {
            return null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);

            int index = 0;
            for (InputStream stream : inputStreams) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(stream));
                if (stream != null)
                    stream.close();

            }

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            return trustManagerFactory.getTrustManagers();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private static KeyManager[] prepareKeyManager(InputStream bksFile, String password) {

        if (bksFile == null || password == null) {
            return null;
        }

        KeyStore clientKeyStore;
        try {
            clientKeyStore = KeyStore.getInstance("BKS");
            clientKeyStore.load(bksFile, password.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, password.toCharArray());
            return keyManagerFactory.getKeyManagers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class UnSafeTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
