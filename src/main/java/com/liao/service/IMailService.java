package com.liao.service;

/**
 *
 * TODO: 邮件发送接口
 * @author LiAo
 * @date 2020/5/21 9:07
 */
public interface IMailService {

    /**
     * 简单文本邮件
     * @param toUser
     * @throws Exception
     */
    void simpleMail(String toUser)throws Exception;

    /**
     * html邮件
     * @param toUser
     * @throws Exception
     */
    void htmlMail(String toUser)throws Exception;

    /**
     * 带附件邮件
     * @param toUser
     * @throws Exception
     */
    void attachmentMail(String toUser)throws Exception;

    /**
     * 带图邮件
     * @param toUser
     * @throws Exception
     */
    void imgMail(String toUser)throws Exception;


    /**
     * 模板邮件
     * @param toUser
     * @throws Exception
     */
    void TemplateMail(String toUser)throws Exception;
}
