package com.zuer.zuerlvdoubancommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

@Component//这个注释是把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>，不加无法读取配置文件里的数据
@PropertySource(value = {"classpath:uploadFile.properties" })
public class UploadFile {

    /**
     *
     * @param file 文件
     * @param name 文件最终保存的名字
     * @param uploadImagesPath 文件保存的主目录
     * @return 文件的全路径
     * @throws Exception
     */
    public static String uploadMultipartFile(MultipartFile file, String name,String uploadImagesPath)throws Exception {

        Calendar cal = Calendar.getInstance();
        String year=cal.get(Calendar.YEAR)+"";
        String month=cal.get(Calendar.MONTH)+1<10?"0"+(cal.get(Calendar.MONTH)+1):(cal.get(Calendar.MONTH)+1)+"";
        String day=cal.get(Calendar.DAY_OF_MONTH)+"";
        String datePath=year+ File.separator+month+ File.separator+day;
        if (file!=null) {
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            // 判断文件类型
            type = fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空

                File path=new File(ResourceUtils.getURL("classpath:").getPath());
                if(!path.exists()){
                    path=new File("");
                }
                File upload=new File(path.getAbsolutePath(),"static"+ File.separator+uploadImagesPath);
                if(!upload.exists()) {
                    upload.mkdirs();
                }
                File finalFileDir=new File(upload.getAbsolutePath()+ File.separator+datePath);
                if(!finalFileDir.exists()) {
                    finalFileDir.mkdirs();
                }
                try {
                    file.transferTo(new File(finalFileDir.getAbsolutePath()+ File.separator+name+"."+type));
                    return uploadImagesPath+ File.separator+datePath+ File.separator+name+"."+type;
                } catch (Exception e) {
                    throw new Exception("图片文件储存异常！");
                }

            }else {

                throw new Exception("上传文件类型有误！");

            }
        }
        return "";
    }

    //删除指定路径下的文件
    public static boolean deleteFile(String path) throws FileNotFoundException {

        path= ResourceUtils.getURL("classpath:").getPath()+"static"+path;
        File delFile = new File(path);
        if(delFile.isFile() && delFile.exists()) {
            delFile.delete();
            return true;
        }else {
            return false;
        }

    }



}
