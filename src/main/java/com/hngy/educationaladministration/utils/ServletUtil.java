package com.hngy.educationaladministration.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;

/**
 * 封装了常用的Servlet API操作
 *
 * Created by shibiao on 2017/9/28.
 */
public class ServletUtil {

    private static String[] mobileAgents = {"iphone", "android", "phone", "mobile",
            "wap", "netfront", "java", "opera mobi", "opera mini", "ucweb",
            "windows ce", "symbian", "series", "webos", "sony",
            "blackberry", "dopod", "nokia", "samsung", "palmsource", "xda",
            "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
            "docomo", "up.browser", "up.link", "blazer", "helio", "hosin",
            "huawei", "novarra", "coolpad", "webos", "techfaith",
            "palmsource", "alcatel", "amoi", "ktouch", "nexian",
            "ericsson", "philips", "sagem", "wellcom", "bunjalloo", "maui",
            "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
            "pantech", "gionee", "portalmmm", "jig browser", "hiptop",
            "benq", "haier", "^lct", "320x320", "240x320", "176x220",
            "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq",
            "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
            "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi",
            "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo",
            "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
            "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play",
            "port", "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-",
            "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar",
            "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tsm-",
            "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
            "wapr", "webc", "winw", "winw", "xda", "xda-",
            "Googlebot-Mobile"};

    /**
     * 根据请求头的User-Agent值判断对方是否用手机访问页面
     */
    public static boolean isMobileUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (!StringUtils.isEmpty(userAgent)) {
            userAgent = userAgent.toLowerCase();
            for (String mobileAgent : mobileAgents) {
                if (userAgent.contains(mobileAgent)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据Servlet请求获取绝对路径的URL，格式大致为http://hostname:port/path，如果port为80，则默认省略port
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        int port = request.getServerPort();
        if (port == 80) {
            return request.getScheme() + "://" + request.getServerName() + path + "/";
        } else {
            return request.getScheme() + "://" + request.getServerName() + ":" + port + path + "/";
        }
    }

    /**
     * 获取对方的IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 调用此方法即可实现断点下载
     *
     */
    public static void downloadFile(File file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!file.exists()) {
            response.sendError(404);
            return;
        }
        String range = request.getHeader("Range");
        long start = 0;
        long end = file.length() - 1;
        if (!StringUtils.isEmpty(range)) {
            String[] arr = range.substring("bytes=".length()).split("-");
            if (arr.length > 0) {
                start = Long.valueOf(arr[0]);
                if (arr.length > 1) {
                    end = Long.valueOf(arr[1]);
                }
            }
            response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + file.length());
            response.setStatus(206);
        }

        long contentLength = end - start + 1;
        String fileName = new String(file.getName().getBytes("UTF-8"), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.addHeader("Content-Length", String.valueOf(contentLength));
        response.setContentType(Files.probeContentType(Paths.get(file.getAbsolutePath())));
        response.setHeader("Last-Modified", String.valueOf(file.lastModified()));
        response.setHeader("Accept-Ranges", "bytes");
        response.setBufferSize(2048);

        FileInputStream in = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        try {
            StreamUtils.copyRange(in, out, start, end);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public static void clearSession(HttpSession session) {
        Enumeration enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            session.removeAttribute(enumeration.nextElement().toString());
        }
    }

}
