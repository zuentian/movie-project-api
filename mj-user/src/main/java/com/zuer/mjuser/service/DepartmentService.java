package com.zuer.mjuser.service;

import com.zuer.mjuser.entity.Department;

public interface DepartmentService {
    Department saveDepartment(Department department);

    Department getDepartmentById(String id);
}
