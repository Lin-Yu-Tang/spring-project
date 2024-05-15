package com.tom.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SecpFuncResponse {

    private SecpCommonResponseHeader header = new SecpCommonResponseHeader();

    @JsonIgnore
    private SecpSystemCodeEnum systemCode;

    @JsonIgnore
    private SecpResponseCodeEnum secpCode;

    @JsonIgnore
    private String errorDetail;

    @JsonIgnore
    private String errorDescription;

    public boolean notSuccess() {
        return SecpResponseCodeEnum.SUCCESS != this.secpCode || this.systemCode != null;
    }

    public boolean isPwdErr() {
        return SecpResponseCodeEnum.isPwdErr(this.secpCode);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SecpFuncResponse [header=");
        builder.append(header);
        builder.append(", systemCode=");
        builder.append(systemCode);
        builder.append(", secpCode=");
        builder.append(secpCode);
        builder.append(", errorDetail=");
        builder.append(errorDetail);
        builder.append(", errorDescription=");
        builder.append(errorDescription);
        builder.append("]");
        return builder.toString();
    }
}