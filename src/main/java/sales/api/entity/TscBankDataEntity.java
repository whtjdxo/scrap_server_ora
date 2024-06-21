package sales.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "TSC_BANK_DATA")

public class TscBankDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private String seq;

    @Column(name="CHAIN_NO")
    private String chainNo;

    @Column(name="BANK_CD")
    private String bankCd;

    @Column(name="ACCOUNT_NO")
    private String accountNo;

    @Column(name="TRADE_DTTM")
    private String tradeDttm;

    @Column(name="TRADE_GB")
    private String tradeGb;

    @Column(name="REMARK")
    private String remark;

    @Column(name="OUT_AMT")
    private Long outAmt;

    @Column(name="IN_AMT")
    private Long inAmt;

    @Column(name="REMAIN_AMT")
    private Long remainAmt;

    @Column(name="CENTER_NM")
    private String centerNm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ENT_DTTM")
    private Timestamp entDttm;

    // 새로 추가된 문자열 날짜 필드
    @Transient
    private String confDttmString;

    @Transient
    private String entDttmString;

}
