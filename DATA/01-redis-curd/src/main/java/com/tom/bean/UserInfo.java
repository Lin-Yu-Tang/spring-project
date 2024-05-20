package com.tom.bean;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@RedisHash("userInfo")
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
	private String cifId;                               // 人員ID
    private String userIp;                              // 使用者IP
    private String userId;                              // 使用者代碼
    private String userName;                            // 使用者姓名
    private String agencyName;                          // 機關名稱
    private String branchName;                          // 分支名稱
    private Long agencyId;                              // 機關ID
    private Long branchId;                              // 分支ID
    private String agencyCodingId;                      // 繳納機關代碼
    private String branchCodingId;                      // 總分支代碼
    private String applicationId;                       // application ID
    private List employeeRoles;      

}
