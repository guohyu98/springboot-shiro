package com.yu.demo.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 */
@Getter
@Setter
public class Admin implements Serializable {

    private static final long serialVersionUID = 833668672337347037L;

    private String username;
    private String password;
    private String perm;

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", perm='" + perm + '\'' +
                '}';
    }
}
