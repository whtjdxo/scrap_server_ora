package sales.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="TSC_VAN_DATA")
@Getter
@Setter

public class TscVanDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private long seq;

    @Column(name = "CHAIN_NO")
    private String chainNo  ;
    @Column(name = "VAN_CD")
    private String vanCd;

    @Column(name = "TRADE_NO")
    private String tradeNo;

    @Column(name = "CONF_NO")
    private String confNo;
    @Column(name = "CONF_GB")
    private String confGb;
    @Column(name = "CONF_DTTM")
    private String confDttm;
    @Column(name = "CONF_RSLT")
    private String confRslt;
    @Column(name = "CONF_TYPE")
    private String confType;

    @Column(name = "CARD_TYPE")
    private String cardType;
    @Column(name = "CARD_NO")
    private String cardNo;
    @Column(name = "CARD_ACQ")
    private String cardAcq;
    @Column(name = "CARD_ISSUE")
    private String cardIssue;
    @Column(name = "INSTALL_MON")
    private String installMon;

    @Column(name = "CONF_AMT")
    private String confAmt;
    @Column(name = "ORG_CONF_DT")
    private String orgConfDt;

    @Column(name = "STORE_ID")
    private String storeId   ;
    @Column(name = "MID")
    private String mid   ;
    @Column(name = "BIZ_NO")
    private String bizNo   ;
    @Column(name = "BIZ_NM")
    private String bizNm   ;

    @Column(name = "CAT_ID")
    private String catId   ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ENT_DTTM")
    private Timestamp entDttm;

    // 새로 추가된 문자열 날짜 필드
    @Transient
    private String confDttmString;

    @Transient
    private String entDttmString;
}

