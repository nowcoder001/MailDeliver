package com.mawenxia.mail.service.api;

import com.mawenxia.mail.constant.Const;
import com.mawenxia.mail.entity.MailSend;
import com.mawenxia.mail.enumeration.MailStatus;
import com.mawenxia.mail.service.MailSendService;
import com.mawenxia.mail.util.KeyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by sam on 2017/5/31.
 * <p>
 * coding like an artist
 */
@Controller
public class ProducerController {
    @Resource
    MailSendService mailSendService;

    @Transactional
    @RequestMapping(value = "/send")
    public void send(@RequestBody(required = false)MailSend mailSend) throws Exception{
        //1.validate

        //2.insert
        mailSend.setSendId(KeyUtil.generatorUUID());
        mailSend.setSendCount(0L);
        mailSend.setSendStatus(MailStatus.DRAFT.getCode());
        mailSend.setVersion(0L);
        mailSend.setUpdateBy(Const.SYS_RUNTIME); //当前登录用户
        mailSendService.insert(mailSend);

        //3.数据扔到redis

    }
}
