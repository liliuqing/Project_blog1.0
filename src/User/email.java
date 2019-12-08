package User;
import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import java.util.Date;

import java.util.Properties;



public class email {

    //发件人的邮箱和授权码

    private final String myEmailAccount = "1406796381@qq.com";

    private final String myEmailPassword = "nybiefrvotoujghb";

    private String receiveMailAccount = null ;

    private String info=null;

    // 信息内容

    public void setReceiveMailAccount(String receiveMailAccount) {

        this.receiveMailAccount = receiveMailAccount;

    }

    public void setInfo(String info) {

        this.info = info;

    }



    private  String myEmailSMTPServer = "smtp.qq.com";



    public  void Send() throws Exception {



        Properties props = new Properties();                    // 参数配置

        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）

        props.setProperty("mail.smtp.host", myEmailSMTPServer);   // 发件人的邮箱的 SMTP 服务器地址

        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证


        final String smtpPort = "465";

        props.setProperty("mail.smtp.port", smtpPort);

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        props.setProperty("mail.smtp.socketFactory.fallback", "false");

        props.setProperty("mail.smtp.socketFactory.port", smtpPort);





        // 2. 根据配置创建会话对象, 用于和邮件服务器交互

        Session session = Session.getDefaultInstance(props);

        session.setDebug(true);



        // 3. 创建一封邮件

        MimeMessage message = createMessage(session, myEmailAccount, receiveMailAccount,info);



        // 4. 根据 Session 获取邮件传输对象

        Transport transport = session.getTransport();





        transport.connect(myEmailAccount, myEmailPassword);



        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人

        transport.sendMessage(message, message.getAllRecipients());



        // 7. 关闭连接

        transport.close();

    }





    public  MimeMessage createMessage(Session session, String sendMail, String receiveMail,String info) throws Exception {

        // 1. 创建一封邮件

        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人

        message.setFrom(new InternetAddress(sendMail, "邮箱验证码测试", "UTF-8"));

        // 3. To: 收件人

        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "xx用户", "UTF-8"));

        // 4. Subject: 邮件主题

        message.setSubject("验证码", "UTF-8");

        // 5. Content: 邮件正文

        message.setContent("【注册验证】:\n"+info+"   请在一分钟内输入验证码完成注册", "text/html;charset=UTF-8");

        // 6. 设置发件时间

        message.setSentDate(new Date());

        // 7. 保存设置

        message.saveChanges();



        return message;

    }

}