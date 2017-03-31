package com.kangyonggan.app.weixin.web.controller;

import com.kangyonggan.app.weixin.biz.util.IOUtil;
import com.kangyonggan.app.weixin.biz.util.XmlUtil;
import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public String handle(HttpServletRequest request, HttpServletResponse response) {
        log.info("========= 微信订阅号后台收到一个请求 =========");

        // 1. 获取请求报文
        String xml = null;
        try {
            xml = IOUtil.read(request.getInputStream());
            log.info("接收到的xml：{}", xml);
        } catch (IOException e) {
            log.error("读取输入流异常", e);
        }

        // 2. 报文解密
        // TODO 调试阶段使用明文，无需解密

        // 3. 解析xml
        Document doc = null;
        try {
            doc = XmlUtil.parseText(xml);
            log.info("xml解析成功");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        Element root = doc.getRootElement();
        String toUserName = root.element("ToUserName").getTextTrim();// 开发者微信号
        String fromUserName = root.element("FromUserName").getTextTrim();// 发送方帐号（一个OpenID）
        String createTime = root.element("CreateTime").getTextTrim();// 消息创建时间 （整型）
        String msgType = root.element("MsgType").getTextTrim();// text
        String content = root.element("Content").getTextTrim();// 文本消息内容
        String msgId = root.element("MsgId").getTextTrim();// 消息id，64位整型

        log.info("toUserName:{}", toUserName);
        log.info("fromUserName:{}", fromUserName);
        log.info("createTime:{}", createTime);
        log.info("msgType:{}", msgType);
        log.info("content:{}", content);
        log.info("msgId:{}", msgId);

        // 4. 报文验签
        // TODO 调试阶段使用明文，无需验签
        log.info("报文验签成功");

        // 5. 业务逻辑
        log.info("落库成功");

        // 6. 拼装响应报文
        String respXml = "<xml>" +
                "<ToUserName><![CDATA[%s]]></ToUserName>" +
                "<FromUserName><![CDATA[%s]]></FromUserName>" +
                "<CreateTime>%d</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[%s]]></Content>" +
                "</xml>";
        respXml = String.format(respXml, toUserName, fromUserName, System.currentTimeMillis(), content);

        log.info("响应报文xml:{}", respXml);

        // 7. 签名加密
        // TODO 调试阶段使用明文，无需加密
        log.info("签名加密后的respXml：{}", respXml);

        // 8. 写响应
//        try {
//            IOUtil.write(response.getOutputStream(), respXml);
//        } catch (IOException e) {
//            log.error("写响应失败", e);
//        }

        return "success";
    }

}
