package org.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装登录后的结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logininfo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
