package springstudy.ioc.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取某个包下面所有类信息
 */
public class ClassUtil {

    public static List<Class> getAllClassByInterface(Class c){

        List<Class> returnClassList = null;
        if(c.isInterface()){
            String packageName = c.getPackage().getName();
            System.out.println("packageName-->"+packageName);
            //List<Class<?>> allClass = getClasses(packageName);

        }

        return returnClassList;

    }

    /**
     * 从包package中获取所有的Class
     * @param packageName
     * @return
     */

    public static List<Class<?>> getClasses(String packageName){
        //第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        //是否循环迭代
        boolean recursive = true;
        //获取包的名字，并进行替换
        String packageDirName = packageName.replace(".","/");
        //定义一个枚举的集合，并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            //循环迭代下去
            while(dirs.hasMoreElements()){
                //获取下一个元素
                URL url = dirs.nextElement();
                //得到协议的名称
                String protocol = url.getProtocol();
                //如果是以文件的形式保存在服务器上
                if("file".equals(protocol)){
                    //获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
                    //以文件的方式扫描整个包下的文件，并添加到集合里
                    findAndAddClassesInPackageByFile(packageName,filePath,recursive,classes);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 以文件的形式来获取包下所有的class
     * @param packageName
     * @param filePath
     * @param recursive
     * @param classes
     */
    private static void findAndAddClassesInPackageByFile(String packageName, String filePath, boolean recursive, List<Class<?>> classes) {

        //获取此包的目录，建立一个File
        File dir = new File(filePath);
        //如果不存在或者也不在目录就直接返回
        if(!dir.exists()||!dir.isDirectory()){
            return;
        }
        //如果存在，就获取包下的所有文件，包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            //自定义过滤规则，如果可以循环（包含子目录）或则是以，class结尾的文件（编译好的java类文件）
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        for(File file : dirfiles){
            //如果是目录，则继续扫描
            if(file.isDirectory()){

                findAndAddClassesInPackageByFile(packageName + "."+file.getName(),file.getAbsolutePath(),recursive,classes);

            } else {
                String className = file.getName().substring(0,file.getName().length()-6);
                try {
                    //添加到集合中去
                    classes.add(Class.forName(packageName+"."+className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        getClasses("springstudy.ioc.service.impl");
    }
}
