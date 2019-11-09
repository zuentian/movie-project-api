package com.zuer.zuerlvdoubancommon.demo;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "DEMO")
public class Demo  implements Serializable {


    private String id;
    private String name;
    private String age;

}
