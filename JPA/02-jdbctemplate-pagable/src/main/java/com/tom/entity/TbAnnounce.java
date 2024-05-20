package com.tom.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.io.Serializable;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_ANNOUNCE")
public class TbAnnounce extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false) // 公告通知ID
    private Long id;

    @Column(name = "OP_YEAR_MONTH", nullable = false, length = 5) // 作業月份
    private String opYearMonth;

    @Column(name = "TARGET_SEND", nullable = false, length = 1) // 公告通知對象
    private String targetSend;

    @Column(name = "GOV_AGENCY_ID") // 機關ID
    private Long govAgencyId;

    @Column(name = "GOV_BRANCH_ID") // 分支ID
    private Long govBranchId;

    @Column(name = "PAGE_SHOW_UP", nullable = false, length = 1) // 公告區域
    private String pageShowUp;

    @Column(name = "EFFECTIVE_DATE", nullable = false, length = 7) // 公告日期
    private String effectiveDate;

    @Column(name = "END_DATE", nullable = false, length = 7) // 下架日期
    private String endDate;

    @Column(name = "CHANGE_DATE", nullable = false, length = 7) // 異動日期
    private String changeDate;

    @Column(name = "CHANGE_CATEGORY", nullable = false, length = 1) // 異動別
    private String changeCategory;

    @Column(name = "CHANGE_STAFF", nullable = false, length = 18) // 異動人員
    private String changeStaff;

    @Column(name = "ANNOUNCE_TITLE", nullable = false, length = 50) // 公告主旨
    private String announceTitle;

    @Column(name = "ANNOUNCE_CONTENT", nullable = false, length = 200) // 本文
    private String announceContent;

    @Column(name = "FILE_URL", length = 50) // 附件連結
    private String fileURL;

    @Column(name = "DELETE_FLAG", length = 1) // 刪除註記
    private String deleteFlag;

}
