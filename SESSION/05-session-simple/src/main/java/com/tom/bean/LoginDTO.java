package com.tom.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private String userId;          // 使用者代碼
    private String credential;      // 密碼
    private String username;		// 使用者名稱
    private String organization;	// 單位
    private String locale;          // 語系

}
