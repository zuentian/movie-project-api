package com.zuer.zuerlvdoubanauth.demo;

import com.zuer.zuerlvdoubancommon.demo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@EnableAutoConfiguration
@RequestMapping(value = "/DemoController")
@RestController
@Controller
public class DemoController {

    @Autowired
    DemoFeignService demoFeignService;
    public List<Demo> query(){
        System.out.println("--------------进入controller层query()-------------");
        return demoFeignService.query();
    }

    public void insertDemo(Demo demo) {
        System.out.println("--------------进入controller层insertDemo()-------------");
        demoFeignService.insertDemo(demo);
    }

    @RequestMapping(value = "/ssoLogin",method = RequestMethod.GET)
    //@ResponseBody
    public String ssoLogin(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        String requserUrl=request.getParameter("requserUrl");
        System.out.println("requserUrl-----------------"+requserUrl);
        request.getRequestDispatcher("updatePdf").forward(request,response);
        return requserUrl;
    }

    @RequestMapping(value = "/updatePdf",method = RequestMethod.GET)
    //@ResponseBody
    public String updatePdf(HttpServletResponse response){
        System.out.println("------------------------updatePdf--------------------------");
        response.setContentType("application/x-download;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename="+ UUID.randomUUID().toString()+".pdf");

        String path="C:\\Users\\Zuer\\Desktop\\12233.pdf";
        File file=new File(path);

        FileInputStream in =null;
        OutputStream out=null;
        try {
            out=response.getOutputStream();
            in=new FileInputStream(file);
            byte[] b=new byte[1024];
            while((in.read(b)!=-1)){
                out.write(b);
            }
            out.flush();
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return path;
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
