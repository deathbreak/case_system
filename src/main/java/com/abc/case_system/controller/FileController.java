package com.abc.case_system.controller;

import com.abc.case_system.bean.ForFileUpDown;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.UUID;

@Controller
@RequestMapping("/file/")
public class FileController {

    @Value("${filepath}")
    private String filepath;

    @Value("${downloadpath}")
    private String downloadpath;

    @Value("${ip_address}")
    private String ipinfo;

    @RequestMapping("upload")
    @ResponseBody
    public ForFileUpDown upload (@RequestParam("file")MultipartFile file){
        //获取文件原始名字
        String fileName = file.getOriginalFilename();
        //获取后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //文件保存路径
        String _filename = UUID.randomUUID() + fileName;
        //文件重命名,防止重复
        fileName = filepath + _filename;
        //文件对象
        File dest = new File(fileName);
        //判断路径是否存在,如果不存在则创建
        if (!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            //保存到服务器中
            file.transferTo(dest);
            return new ForFileUpDown(0,"上传成功", ipinfo +_filename);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ForFileUpDown(1,"上传失败", "");
    }


    @RequestMapping("download")
    public void download(String file_, HttpServletResponse response) throws Exception {
        // 文件地址，真实环境是存放在数据库中的
        File file = new File(downloadpath + file_);
        // 穿件输入对象
        FileInputStream fis = new FileInputStream(file);
        // 设置相关格式
        response.setContentType("application/force-download");
        // 设置下载后的文件名以及header
        response.addHeader("Content-disposition", "attachment;fileName=" + file_);
        // 创建输出对象
        OutputStream os = response.getOutputStream();
        // 常规操作
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        fis.close();
    }

}
