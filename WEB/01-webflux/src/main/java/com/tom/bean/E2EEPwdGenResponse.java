package com.tom.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class E2EEPwdGenResponse extends SecpFuncResponse {

	private Payload payload = new Payload();

    @NoArgsConstructor
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Payload {
        private String pinBlock; // 主機驗證之密文
        private String encPin; // 包含 B-Part 密碼的加密檔案(就是加密的zip)

        // 錯誤的回應
        private String errorDetail;
        private String errorDescription;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("E2EEPwdGenResponse [payload=");
        builder.append(payload);
        builder.append(", getPayload()=");
        builder.append(getPayload());
        builder.append(", notSuccess()=");
        builder.append(notSuccess());
        builder.append(", isPwdErr()=");
        builder.append(isPwdErr());
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append(", getHeader()=");
        builder.append(getHeader());
        builder.append(", getSystemCode()=");
        builder.append(getSystemCode());
        builder.append(", getSecpCode()=");
        builder.append(getSecpCode());
        builder.append(", getErrorDetail()=");
        builder.append(getErrorDetail());
        builder.append(", getErrorDescription()=");
        builder.append(getErrorDescription());
        builder.append(", getClass()=");
        builder.append(getClass());
        builder.append(", hashCode()=");
        builder.append(hashCode());
        builder.append("]");
        return builder.toString();
    }
}
