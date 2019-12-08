package User;

import User.email;

import java.util.Random;

public class lili {


    public static class Test {

        public static void main(String[] args) {

            email sendEmail=new email();

            //设置要发送的邮箱

            sendEmail.setReceiveMailAccount("781410052@qq.com");//这里是邮件发送的邮箱地址，我们可以改成一个变量传入

            //创建9位发验证码

            Random random=new Random();

            String str="";

            for(int i=0;i<9;i++) {

                int n=random.nextInt(10);

                str+=n;

            }

            sendEmail.setInfo(str);

            try {

                sendEmail.Send();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }



    }
}
