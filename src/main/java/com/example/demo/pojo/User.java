package com.example.demo.pojo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class User implements Serializable {
    private Integer id;
    private String name;
    private String gender;
}
