package com.zuer.mjuser;

import com.zuer.mjuser.entity.Department;
import com.zuer.mjuser.service.DepartmentService;
import com.zuer.mjuser.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MjUserApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private DepartmentService departmentService;


    @Autowired
    private UserService userService;


    @Test
    public void testDepartmentService(){

        Department department=new Department();
        department.setId("111");
        department.setName("dev");
        Department result =departmentService.saveDepartment(department);

        //LOGGER.info("add result "+result);


        //Long id =1L;
        //result =departmentService.getDepartmentById(id);
        //LOGGER.info("get department "+result);
    }

}
