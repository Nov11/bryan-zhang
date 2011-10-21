package org.poseidon.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;

public abstract class HttpClientUtil {

    private static final int BUFFER_SIZE = 4096;
    
    static{
    	//注册 HTTPS 协议
    	ProtocolSocketFactory factory = new Protocol4Https();
        Protocol.registerProtocol("https", new Protocol("https", factory, 443));
    }
    /**
     * 转换成queryString
     * @param params
     *            存档参数名与参数值的集合
     * @return queryString
     */
    public static String getQueryString(Map<String, String> params) {
        String queryString = null;
        if (params != null) {
            NameValuePair[] nvp = new NameValuePair[params.size()];
            int index = 0;
            for (String key : params.keySet()) {
                nvp[index++] = new NameValuePair(key, params.get(key));
            }
            queryString = EncodingUtil.formUrlEncode(nvp, "UTF-8");
        }
        return queryString == null ? "" : queryString;
    }

    /**
     * 向目标发出一个GET请求并得到响应数据
     * @param url
     *            目标
     * @param params
     *            参数集合
     * @param timeout
     *            超时时间
     * @return 响应数据
     * @throws IOException 
     */
    public static String sendGet(String url, Map<String, String> params, int timeout) throws IOException  {
    	
        GetMethod method = new GetMethod(url);
        if (params != null) {
            method.setQueryString(getQueryString(params));
        }

        byte[] content;
        String text = null, charset = null;
        try {
            content = executeMethod(method, timeout);
            charset = method.getResponseCharSet();
            text = new String(content, charset);
        } finally {
            method.releaseConnection();
        }
        
        return text;
    }
    
    /**
     * 向目标发出一个Post请求并得到响应数据
     * @param url
     *            目标
     * @param params
     *            参数集合
     * @param timeout
     *            超时时间
     * @return 响应数据
     * @throws IOException
     */
    public static String sendPost(String url, NameValuePair[] data, int timeout) throws IOException {
    	
        PostMethod method = new PostMethod(url);
        method.setRequestBody(data);
        
        byte[] content;
        String text = null, charset = null;
        try {
            content = executeMethod(method, timeout);
            charset = method.getResponseCharSet();
            text = new String(content, charset);
            if(true){
                //throw new NullPointerException();
            }
        } finally {
            method.releaseConnection();
        }
        
        return text;
    }

    private static byte[] executeMethod(HttpMethodBase method, int timeout) throws IOException {
    	InputStream in = null;
    	try {
	    	method.addRequestHeader("Connection", "close");
	        HttpClient client = new HttpClient();
	        HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
	        params.setConnectionTimeout(timeout);
	        params.setSoTimeout(timeout);
	        params.setStaleCheckingEnabled(false);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
            client.executeMethod(method);         
            in = method.getResponseBodyAsStream();
            byte[] buffer = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buffer)) > 0) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        } 
    	finally {
            if (in != null) {
                in.close();
            }
        }
    }
}


/**
 * 内部类,支持 Https 请求发送格式
 * @author xing_zhao
 *
 */
class Protocol4Https implements SecureProtocolSocketFactory {
    
    private SSLContext sslcontext = null;
    
    private SSLContext createSSLContext() {
        SSLContext sslcontext=null;
        try {
            sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslcontext;
    }
    
    private SSLContext getSSLContext() {
        if (this.sslcontext == null) {
            this.sslcontext = createSSLContext();
        }
        return this.sslcontext;
    }
    
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
            throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(
                socket,
                host,
                port,
                autoClose
            );
    }

    public Socket createSocket(String host, int port) throws IOException,
            UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(
                host,
                port
            );
    }
    
    public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
            throws IOException, UnknownHostException {
        return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
    }

    public Socket createSocket(String host, int port, InetAddress localAddress,
            int localPort, HttpConnectionParams params) throws IOException,
            UnknownHostException, ConnectTimeoutException {
        if (params == null) {
            throw new IllegalArgumentException("Parameters may not be null");
        }
        int timeout = params.getConnectionTimeout();
        SocketFactory socketfactory = getSSLContext().getSocketFactory();
        if (timeout == 0) {
            return socketfactory.createSocket(host, port, localAddress, localPort);
        } else {
            Socket socket = socketfactory.createSocket();
            SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
            SocketAddress remoteaddr = new InetSocketAddress(host, port);
            socket.bind(localaddr);
            socket.connect(remoteaddr, timeout);
            return socket;
        }
    }
    
    private static class TrustAnyTrustManager implements X509TrustManager {
       
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
   
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
   
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }

        public boolean isClientTrusted(X509Certificate[] arg0) {
            return false;
        }

        public boolean isServerTrusted(X509Certificate[] arg0) {
            return false;
        }
    }
    
}
