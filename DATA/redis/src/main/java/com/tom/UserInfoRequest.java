package com.tom;

import java.util.List;

import lombok.Data;

@Data
public class UserInfoRequest {

    private String userId;                              // 使用者代碼
    private String agencyName;                          // 機關名稱
    private String branchName;                          // 分支名稱
    private String agencyCodingId;                      // 繳納機關代碼
    private String branchCodingId;                      // 總分支代碼
    
}
