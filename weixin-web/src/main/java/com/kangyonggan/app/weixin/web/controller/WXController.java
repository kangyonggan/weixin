package com.kangyonggan.app.weixin.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * @author kangyonggan
 * @since 2017/3/30
 */
@RestController
@RequestMapping("wx")
@Log4j2
public class WXController {

    /**
     * 验证开发者服务器
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handle(HttpServletRequest request) {
        log.info("========= 微信订阅号后台收到一个请求 =========");
        log.info("request:" + request);
//        String signature = request.getParameter("signature");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
//        String echostr = request.getParameter("echostr");
//
//        log.info("signature:" + signature);
//        log.info("timestamp:" + timestamp);
//        log.info("nonce:" + nonce);
//        log.info("echostr:" + echostr);

        ServletInputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            inputStream = request.getInputStream();

            byte b[] = new byte[1024];
            int len;

            while ((len = inputStream.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }

            log.info("接收到的消息：{}", sb);

        } catch (Exception e) {
            log.error("接收消息异常", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    log.error("关闭流异常", e);
                }
            }
        }

        return "success";
    }

}
