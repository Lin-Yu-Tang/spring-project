package com.tom.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@SessionScope
@Component
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	private String creator = "SYSTEM";                  // 建立人員
    private String modifier = "SYSTEM";                 // 更新人員
    private String cifId;                               // 人員ID
    private String userName;                            // 使用者姓名

    public UserInfo() {
        System.out.println(":::::::::::::: DO_constructor :::::::::::::::::");
        System.out.println(this);
    }

}
