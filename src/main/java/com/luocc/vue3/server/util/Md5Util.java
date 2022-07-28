package com.luocc.vue3.server.util;

import java.io.*;
import java.net.*;
import java.security.MessageDigest;

public class Md5Util {

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        System.out.println(MD5("18:26:1816779884994f4b07b495846d1097fa78"));
    }


    public static String getFileMD5(byte[] b) {
        if (b == null) {
            return null;
        }
        MessageDigest digest = null;
        StringBuffer Sbuf = new StringBuffer("");
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(b, 0, b.length);
            byte[] buff = digest.digest();
            int i;
            for (int offset = 0; offset < buff.length; offset++) {
                i = buff[offset];
                if (i < 0) i += 256;
                if (i < 16) Sbuf.append("0");
                Sbuf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return Sbuf.toString();
    }


    public static String post(String server_url, String message) {
        BufferedWriter bw = null;
        BufferedReader br = null;
        HttpURLConnection conn = null;

        String temp = null;
        StringBuffer resp = new StringBuffer();
        try {
            URL url = new URL(server_url);
            conn = (HttpURLConnection) url.openConnection();
            setHttpCommonParams(conn);
            conn.setRequestMethod("POST");
            conn.connect();
            bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bw.write(message);
            bw.flush();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((temp = br.readLine()) != null) {
                    resp.append(temp);
                }
            } else {
                conn.disconnect();
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        } finally {
            bw = null;
            br = null;
            conn.disconnect();
        }

        return resp.toString();
    }


    private static void setHttpCommonParams(HttpURLConnection conn) {
        conn.setReadTimeout(15000);
        conn.setConnectTimeout(30000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


}
