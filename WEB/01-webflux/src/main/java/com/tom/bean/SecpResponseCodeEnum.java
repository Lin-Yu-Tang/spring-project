package com.tom.bean;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SecpResponseCodeEnum {

    // SS Security Function 分類/狀態代碼對照表
    // 0 成功
    SUCCESS("SCSCSF0000", "成功"), UNDEFINED_ERR("IM9999", "安控未定義錯誤"),
    // A 參數檢核類
    SF_APPID_ACCESS_DENIED("SCSCSFA001", "前端應用 APP_ID 不存在於 ACL"), SF_IP_ACCESS_DENIED("SCSCSFA002", "前端應用 IP 不存在於 ACL"),
    SF_FUNC_ACCESS_DENIED("SCSCSFA003", "要求之安控功能不存在於 ACL"), SF_KEY_ACCESS_DENIED("SCSCSFA004", "要求之金鑰不存在於 ACL"),
    SF_TOKEN_FAIL("SCSCSFA005", "JWT Token 驗簽失敗"),
    // B資料庫相關
    SF_DATABASE_FAILURE("SCSCSFB001", "Database error code"),
    SF_SOURCE_NOT_EXIST_FAILURE("SCSCSFB002", "The source archive/file not exist"),
    SF_FILE_FAILURE("SCSCSFB003", "The failure may be caused by a format error or something data lose"),
    SF_PARAMETER_FAILURE("SCSCSFB004", "This Parameter can’t find/read from archive/file/property/database sources."),
    // c HSM

    // D外部界接
    SF_CONNECT_TIMEOUT_FAILURE("SCSCSFD001", "The sending component is unable to reciving data in a define wait time"),
    SF_CONNECT_FAILURE("SCSCSFD002",
            "The sending component is unable to establish connection to the receiving component."),
    SF_FTP_DOWNLOAD_FAILED("SCSCSFD003", "FTP/SFTP download failed"),
    SF_FTP_UPLOAD_FAILED("SCSCSFD004", "FTP/SFTP upload failed"),
    SF_TRANSIENT_SYSTEM_FAILURE("SCSCSFD998", "Transient System Failure"),
    SF_PERMANENT_SYSTEM_FAILURE("SCSCSFD999", "Permanent System Failure"),

    // E 訊息相關
    SF_MSG_NOT_RECOGNIZED("SCSCSFE001",
            "The sending component is unable to establish connection to the receiving component."),
    // F欄位相關
    SF_REQ_TOKEN_DATA_MISSING("SCSCSFF001", "The required token data element missing"),
    SF_REQ_PAYLOAD_DATA_MISSING("SCSCSFF002", "必要欄位被省略，如果有多個欄位則用逗號分隔"), SF_REQ_DUPLICATE_DATA("SCSCSFF003", "重複的資料欄位"),
    // G 電文相關
    SF_TRANS_TIME_OUT("SCSCSFG001", "交易逾時"), SF_TRANS_DATA_NOT_VALID("SCSCSFG002", "交易資料驗證失敗"),
    SF_TRANS_PROCESS_EXCEPTION("SCSCSFG003", "交易處理異常"), SF_RETURN_DATA_MISSING("SCSCSFG004", "無回傳資料"),
    SF_MAC_FAILED("SCSCSFG005", "MAC 驗證失敗"), SF_SIGN_FAILED("SCSCSFG006", "簽名驗證失敗"),
    SF_PINBLOCK_FAILED("SCSCSFG007", "PINBLOCK 驗證失敗"), SF_EN_DE_CODE_FAILED("SCSCSFG008", "編碼/解碼失敗"),

    SF_BANCS_TIMEOUT("SCSCSFG009", "主機時間已逾時"), SF_BANCS_VERIFY_FAILED("SCSCSFG010", "主機 Token 驗證失敗"),

    // Z other
    SF_TRANS_SYSTEM_FAILED("SCSCSFZ998", "Transient System Failure"),
    SF_PERMANENT_SYSTEM_FAILED("SCSCSFZ999", "Permanent System Failure"),

    // SS Security Service 分類/狀態代碼對照表 20230503 安控文件ver1.6
    // A params
    SS_PARAMS_REQUIRED_ERR("SCSCSSA001", "必要欄位無值"), SS_PARAMS_CHECK_ERR("SCSCSSA002", "check 值檢核錯誤"),
    SS_PARAMS_KCV_ERR("SCSCSSA003", "kcv 值檢核錯誤"),
    // B db
    SS_DATABASE_1_ERR("SCSCSSB001", "安控資料庫相關問題一"), SS_DATABASE_2_ERR("SCSCSSB002", "安控資料庫相關問題二"),
    // C hsm
    SS_HSM_SLOTID_ERR("SCSCSSC003", "無效的 slot id"), SS_HSM_PARAMS_ERR("SCSCSSC032", "無效的輸入資料"),
    SS_HSM_PARAMS_LEN_ERR("SCSCSSC033", "輸入資料長度有誤"), SS_HSM_HARDWARE_ERR("SCSCSSC048", "HSM 硬體錯誤"),
    SS_HSM_MEMORY_ERR("SCSCSSC049", "HSM 記憶體錯誤"), SS_HSM_MEMORY_REMOVE_ERR("SCSCSSC050", "HSM 硬體被移除"),
    SS_HSM_HASH_ERR("SCSCSSC064", "加密後的資料無效"), SS_HSM_HASH_LEN_ERR("SCSCSSC065", "加密後的資料長度有誤"),
    SS_HSM_KEY_ERR("SCSCSSC096", "無效的金鑰 handle"), SS_HSM_KEY_LEN_ERR("SCSCSSC098", "金鑰長度不符"),
    SS_HSM_KEY_TYPE_ERR("SCSCSSC099", "金鑰類型有誤"), SS_HSM_KEY_NOTFOUND_ERR("SCSCSSC100", "找不到指定的金鑰"),
    SS_HSM_KEY_EXPORT_ERR("SCSCSSC105", "指定的金鑰無法匯出"), SS_HSM_KEY_ALG_ERR("SCSCSSC112", "無效的金鑰演算法"),
    SS_HSM_KEY_ALG_PARAMS_ERR("SCSCSSC113", "金鑰演算法參數有誤"), SS_HSM_PSWD_ERR("SCSCSSC160", "HSM 密碼錯誤"),
    SS_HSM_PSWD_INVALID_ERR("SCSCSSC161", "HSM 密碼無效"), SS_HSM_PSWD_LEN_ERR("SCSCSSC162", "HSM 密碼長度有誤"),
    SS_HSM_PSWD_EXPIRED_ERR("SCSCSSC163", "HSM 密碼已過期"), SS_HSM_PSWD_OVERLIMIT_ERR("SCSCSSC164", "HSM 因登入密碼錯誤過多已被鎖住"),
    SS_HSM_SESSION_ERR("SCSCSSC177", "使用 Session 數過多。"), SS_ALG_PARAMS_ERR("SCSCSSC208", "演算法參數不完整"),
    SS_ALG_PARAMS_DUPLICATE_ERR("SCSCSSC209", "演算法參數相衝突"), SS_TOKEN_NOTFOUND_ERR("SCSCSSC224", "找不到指定的 token"),
    SS_TOKEN_ERR("SCSCSSC225", "偵測到無法識別的 token"), SS_HSM_NOT_LOGIN_ERR("SCSCSSC257", "尚未登入 HSM"),
    SS_WRAPPED_KEY_ERR("SCSCSSC272", "無效的 wrapped key"), SS_ELLIPTIC_CURVE_PARAMS_ERR("SCSCSSC320", "不支援的橢圓曲線參數"),
    SS_DATA_OVER_RANGE_ERR("SCSCSSC336", "接收資料的空間過小"), SS_HSM_DRIVER_NOT_INIT_ERR("SCSCSSC400", "尚未初始化 HSM 驅動程式"),
    SS_HSM_ALREADY_INIT_ERR("SCSCSSC401", "HSM 驅動程式已經被初始化過"), SS_HSM_ERR("SCSCSSC999", "HSM 未知錯誤"),
    // D

    // E E2EE
    SS_COMMAND_LEN_ERR("SCSCSSE001", "命令中所含資料過長"), SS_HSM_DATA_ERR("SCSCSSE002", "從 HSM 中接收的資料不符合預期"),
    SS_SESSION_OVER_LIMIT_ERR("SCSCSSE003", "session 數量超過限制"), SS_BUFFER_ERR("SCSCSSE004", "接收資料的 buffer 設定過小"),
    SS_HSM_INVALID_ERR("SCSCSSE005", "連接不到有效的 HSM"), SS_SESSION_INVALID_ERR("SCSCSSE006", "指定的 session 是無效的"),
    SS_E2EE_LOAD_ERR("SCSCSSE007", "載入 E2EE 程式庫錯誤"), SS_HSM_INIT_CONN_ERR("SCSCSSE008", "初始化 HSM 連線錯誤"),
    SS_PARAMS_ERR("SCSCSSE009", "輸入的參數是無效的"), SS_PARAMS_LEN_ERR("SCSCSSE010", "輸入參數的長度是無效的"),
    SS_HASH_PSWD_LEN_ERR("SCSCSSE011", "加密過的密碼長度是無效的"),
    SS_HASH_PSWD_SESSION_KEY_ERR("SCSCSSE012", "加密密碼的 session key 長度是無效的"),
    SS_OUT_OF_MEMORY_ERR("SCSCSSE013", "無法配置記憶體"), SS_AES_DES_KEY_NOTFOUND_ERR("SCSCSSE014", "找不到指定的 AES/DES 金鑰"),
    SS_ECC_KEY_NOTFOUND_ERR("SCSCSSE015", "找不到指定的 ECC 金鑰"), SS_KEY_LEN_ERR("SCSCSSE016", "無效的金鑰長度"),
    SS_SESSION_KEY_EXPIRED_ERR("SCSCSSE017", "session key 已過期"), SS_DATA_LEN_ERR("SCSCSSE018", "資料長度超過限制"),
    SS_COMMAND_AUTH_ERR("SCSCSSE019", "沒有執行命令的權限"), SS_AP_NAME_ERR("SCSCSSE020", "AP 名稱超過長度"),
    SS_COMMAND_OVERLIMIT_ERR("SCSCSSE021", "超過可以執行命令的數量"),

    // 密碼驗證錯誤類別
    PWD_VERIFY_ERR("SCSCSSE022", "密碼驗證失敗"),
    // 密碼格式錯誤類別 E023~E038
    PWD_SAME_WITH_OLD_PWD_ERR("SCSCSSE023", "新密碼與舊密碼相同"), PWD_LEN_OVER_32_ERR("SCSCSSE024", "密碼長度過長(超過 32 字元)"),
    PWD_VERIFY_SETTING_ERR("SCSCSSE025", "密碼驗證方式設定錯誤"), PWD_LEN_ERR("SCSCSSE026", "密碼長度不符合設定值"),
    PWD_NOT_ALLOW_CHAR_ERR("SCSCSSE027", "密碼中含有非指定格式的字元"), PWD_CHAR_ERR("SCSCSSE028", "密碼與不可相同的字串相同"),
    PWD_NOT_ILLEGAL_ERR("SCSCSSE029", "密碼中含有非法字串"), PWD_UPPERCASE_ERR("SCSCSSE030", "密碼中大寫字元數量不足"),
    PWD_LOWERCASE_ERR("SCSCSSE031", "密碼中小寫字元數量不足"), PWD_ENG_ERR("SCSCSSE032", "密碼中英文字元數量不足"),
    PWD_CON_CHAR_ERR("SCSCSSE033", "密碼中連續重複字元超過限制"), PWD_CON_NUM_CHAR_ERR("SCSCSSE034", "密碼中連續數字超過限制"),
    PWD_CON_ENG_CHAR_ERR("SCSCSSE035", "密碼中連續英文超過限制"), PWD_SEP_CHAR_ERR("SCSCSSE036", "密碼中特殊字元數量不足"),
    PWD_NUM_ORDER_ERR("SCSCSSE037", "密碼中昇冪或降冪長度超過限制"), PWD_NUM_ERR("SCSCSSE038", "密碼中數字字元數量不足"),

    // ver 1.7 update
    SS_FM_PKSC11_ERR("SCSCSSE039", "FM 中啟動 PKCS#11 失敗"), SS_FM_COMMAND_ERR("SCSCSSE040", "FM 不支援的命令."),
    SS_MEMORY_SETTING_ERR("SCSCSSE041", "配置記憶體錯誤"), SS_SESSION_ERR("SCSCSSE042", "無效的 Session."),
    SS_FM_FINDOBJ_INIT_ERR("SCSCSSE043", "FM 中呼叫 C_FindObjectsInit()失敗."),
    SS_FM_FINDOBJ_ERR("SCSCSSE044", "FM 中呼叫 C_FindObjects()失敗."),
    SS_FM_ENCRYP_INIT_ERR("SCSCSSE045", "FM 中呼叫 C_EncryptInit()失敗"),
    SS_FM_ENCRYP_ERR("SCSCSSE046", "FM 中呼叫 C_Encrypt()失敗."),
    SS_FM_DECRYP_INIT_ERR("SCSCSSE047", "FM 中呼叫 C_DecryptInit()失敗."),
    SS_FM_DECRYP_ERR("SCSCSSE048", "FM 中呼叫 C_Decrypt()失敗"),
    SS_FM_DIGST_INIT_ERR("SCSCSSE049", "FM 中呼叫 C_DigestInit()失敗."),
    SS_FM_DIGST_ERR("SCSCSSE050", "FM 中呼叫 C_Digest()失敗"), SS_SESSION_OBJECT_CREATE_ERR("SCSCSSE051", "建立 session 物件失敗"),
    SS_ENCRYP_MEMORY_TOO_LOW_ERR("SCSCSSE052", "加密用的記憶體過小"), SS_DECRYP_MEMORY_TOO_LOW_ERR("SCSCSSE053", "解密用的記憶體過小"),
    SS_HSM_INIT_ERR("SCSCSSE054", "HSM 初始化失敗"),

    // Z 其他
    SS_UNKNOWN_ERR("SCSCSSZ999", "Unknown Error");

    private String code;
    private String desc;

    public static SecpResponseCodeEnum getEnumByRespCode(String respCode) {
        return Stream.of(SecpResponseCodeEnum.values()).filter(r -> StringUtils.equals(r.getCode(), respCode))
                .findFirst().orElseGet(() -> UNDEFINED_ERR);
    }

    public static boolean isFail(SecpResponseCodeEnum target) {
        return SUCCESS != target;
    }

    public static boolean isSuccess(SecpResponseCodeEnum target) {
        return SUCCESS == target;
    }

    /**
     * 密碼檢驗錯誤類型
     *
     * @param target
     * @return
     */
    public static boolean isPwdErr(SecpResponseCodeEnum target) {
        return target != null && PWD_VERIFY_ERR == target;
    }

    /**
     * 密碼格式驗證錯誤代碼
     *
     * @param target
     * @return
     */
    public static boolean isPwdRegexErr(SecpResponseCodeEnum target) {
        Predicate<String> inRange = s -> (22 < Integer.valueOf(s)) && (39 > Integer.valueOf(s));
        Optional<SecpResponseCodeEnum> result = Stream.of(SecpResponseCodeEnum.values())
                .filter(t -> t.getCode().startsWith("SCSCSSE"))
                .filter(t -> (inRange.test(t.getCode().replaceAll("SCSCSSE0", "")))).filter(t -> t == target)
                .findFirst();
        return result.isPresent();
    }

}
