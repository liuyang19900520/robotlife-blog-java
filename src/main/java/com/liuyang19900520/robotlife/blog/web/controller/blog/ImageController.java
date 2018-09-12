package com.liuyang19900520.robotlife.blog.web.controller.blog;


import com.liuyang19900520.robotlife.blog.common.pojo.CommonRes;
import com.liuyang19900520.robotlife.blog.common.util.LS3Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

@Controller
@RequestMapping("/image")
public class ImageController {

    private String IMAGE_PATH = "/home/robotlife/tempimage/";

    private String URL_PREFIX = "https://s3-ap-northeast-1.amazonaws.com/robotlife/markdown/";
    //private String IMAGE_PATH = SysConstant.IMAGE_PATH;

    @RequestMapping("/uploadimage")
    @ResponseBody
    public Object oneFileUpload(HttpServletRequest request, HttpServletResponse response) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 1. build an iterator
        Iterator<String> itr = multipartRequest.getFileNames();
        CommonsMultipartFile file = null;

        // 2. get each file
        while (itr.hasNext()) {

            // 2.1 get next MultipartFile
            file = (CommonsMultipartFile) multipartRequest.getFile(itr.next());

            String fileName = file.getOriginalFilename();

            // new file name
            String newFileName = fileName;
            // 获得项目的路径
            File f = new File(IMAGE_PATH);
            String result = IMAGE_PATH + newFileName;
            if (!f.exists()) {
                f.mkdirs();
            }
            if (!file.isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(result);
                    InputStream in = file.getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();

                    File resultFile = new File(result);
                    if (resultFile.exists()) {
                        System.out.println(file.getOriginalFilename() + " uploaded! ");
                        boolean isUpload2S3 = LS3Utils.putObj(resultFile);
                        if (isUpload2S3) {
                            System.out.println(resultFile.getName()
                                    + "========================================================================OK");
                            System.out.println("the file is upload to s3 successfully");
                            String url = URL_PREFIX + resultFile.getName();
                            resultFile.delete();

                            return CommonRes.buildOk(url);

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return CommonRes.buildError("1", "1");
    }

}