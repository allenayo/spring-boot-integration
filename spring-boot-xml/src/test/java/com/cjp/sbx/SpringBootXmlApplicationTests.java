package com.cjp.sbx;

import com.cjp.sbx.entity.Message;
import com.cjp.sbx.util.XmlUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootXmlApplicationTests {

    @Test
    public void testToXML() throws Exception {
        Message message = new Message();
        message.setFromUserName("allenAyo");
        message.setToUserName("allenAyo");
        message.setMsgType("text");
        message.setContent("allenAyo");
        message.setCreateTime(System.currentTimeMillis());
        System.out.println(XmlUtil.toXML(message));
    }

    @Test
    public void testToBean() throws Exception {
        String xmlString = "<xml>\n" +
                "  <ToUserName><![CDATA[allenAyo]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[allenAyo]]></FromUserName>\n" +
                "  <CreateTime>1625629544709</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[allenAyo]]></Content>\n" +
                "  <MsgId>0</MsgId>\n" +
                "</xml>";
        System.out.println(XmlUtil.toBean(xmlString, Message.class));
    }

}
