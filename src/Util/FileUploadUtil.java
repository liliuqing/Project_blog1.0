package Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtil {

    public static final String ATTACHED_UPLOADS = "/attached/uploads";

    /**
     * 上传文件通用方法
     * @param request 请求对象
     * @param fieldName form表单页面文件输入框的name属性值
     * @return 返回前端页面可以访问文件的URL地址
     */
    public static String uploadFile(HttpServletRequest request, String fieldName)
            throws IOException, ServletException {

        //存储路径
        String savePath = request.getServletContext().getRealPath(ATTACHED_UPLOADS);
        //Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
        
        Part part = request.getPart(fieldName);//通过表单file控件(<input type="file" name="file">)的名字直接获取Part对象
        
        //Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
        //获取请求头，请求头的格式：form-data; name="file"; filename="1.jpg"
        String header = part.getHeader("content-disposition");
        //获取文件名1.jpg
        String fileName = header.substring(header.lastIndexOf("=")+2, header.length()-1);
        
        File file = new File(savePath);
        if(!file.exists()){
            //创建存储上传文件的文件夹
            file.mkdirs();
        }

        //重命名上传的文件（UUID生成了一个唯一的以字母和数字组成的字符串）
        String newFileName = UUID.randomUUID().toString().replace("-","") + "_" + fileName;

        //文件保存路径
        String filePath = savePath + File.separator + newFileName;

        //把文件写到指定路径
        part.write(filePath);

        System.out.println("*******file save success: " + filePath);

        return ATTACHED_UPLOADS + File.separator + newFileName;
    }

}
