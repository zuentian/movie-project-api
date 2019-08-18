package com.zuer.mjuser.service.impl;

import com.zuer.mjuser.entity.Department;
import com.zuer.mjuser.repository.DepartmentRepository;
import com.zuer.mjuser.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service

public class DepartmentServiceImpl  implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(String id) {
        return departmentRepository.findOne(id);
    }

}
