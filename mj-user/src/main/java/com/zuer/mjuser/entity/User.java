package com.zuer.mjuser.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "springboot_user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_name")
    private String name;


    @Column(name = "user_password")
    private String password;


    @Column(name = "user_create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


   /* @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;



    @ManyToMany(cascade = {},fetch = FetchType.EAGER)
    @JoinTable(name = "springboot_user_role",joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

   /* public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }*/


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("password", password)
                .append("createDate", createDate)
                //.append("department", department)
                //.append("roleList", roleList)
                .toString();
    }
}
