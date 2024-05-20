package com.tom.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_STAFF_RESUME_HIS")
public class TbStaffResumeHis  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // 人員經歷異動ID@@N

    @Column(name = "OP_YEAR_MONTH")
    private String opYearMonth; // 作業月份@@N

    @Column(name = "CIF_NO")
    private String cifNo; // CIF編號@@N

    @Column(name = "ID_NO")
    private String idNo; // 身份證號@@Y

    @Column(name = "NAME")
    private String name; // 姓名@@Y

    @Column(name = "BIRTHDAY")
    private String birthday; // 出生日期@@Y

    @Column(name = "GOV_AGENCY_ID")
    private Long govAgencyId; // 機關ID@@N

    @Column(name = "GOV_AGENCY_NAME")
    private String govAgencyName; // 機關名稱@@N

    @Column(name = "GOV_BRANCH_ID")
    private Long govBranchId; // 分支ID@@N

    @Column(name = "GOV_BRANCH_NAME")
    private String govBranchName; // 分支名稱@@N

    @Column(name = "CURRENT_ADD_DATE")
    private String currentAddDate; // 現任加入日期(生效日期)@@N

    @Column(name = "FIRST_ADD_DATE")
    private String firstAddDate; // 初任加入日期(生效日期)@@N

    @Column(name = "JOB_ROLE")
    private String jobRole; // 身份別@@N

    @Column(name = "SALARY_GRADE_ID")
    private Long salaryGradeId; // 俸點對照表ID@@N

    @Column(name = "SALARY_CATEGORY")
    private String salaryCategory; // 俸點類別@@N

    @Column(name = "SALARY_GRADE")
    private String salaryGrade; // 俸(薪)點@@N

    @Column(name = "EMAIL")
    private String email; // 電子郵件@@Y

    @Column(name = "EFFECTIVE_DATE")
    private String effectiveDate; // 生效日期@@N

    @Column(name = "LAST_JOB_ROLE")
    private String lastJobRole; // 原機關身分別@@N

    @Column(name = "LAST_SALARY_GRADE_ID")
    private Long lastSalaryGradeId; // 原機關俸點對照表ID@@N

    @Column(name = "LAST_SALARY_CATEGORY")
    private String lastSalaryCategory; // 原機關俸點類別@@N

    @Column(name = "LAST_SALARY_GRADE")
    private String lastSalaryGrade; // 原機關俸(薪)點@@N

    @Column(name = "CHANGE_CATEGORY")
    private String changeCategory; //異動別@@N

    @Column(name = "CHANGE_DATE")
    private String changeDate; // 異動日期@@N

    @Column(name = "REMARK")
    private String remark; // 備註@@N

    @Column(name = "RESIGN_EFFECTIVE_DATE")
    private String resignEffectiveDate; // 退離生效日@@N

    @Column(name = "TEMP_FLAG")
    private String tempFlag; // 暫存註記@@N

    @Column(name = "DELETE_FLAG")
    private String deleteFlag; // 刪除註記@@N

    @Column(name = "CHANGE_REASON")
    private String changeReason; // 異動原因

    @Column(name = "RESIGN_ID")
    private Long resignId; // 退離ID

}