package com.kangyonggan.app.weixin.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(method = RequestMethod.GET)
    public String handle(HttpServletRequest request) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        log.info("signature:" + signature);
        log.info("timestamp:" + timestamp);
        log.info("nonce:" + nonce);
        log.info("echostr:" + echostr);

        return echostr;
    }

}
