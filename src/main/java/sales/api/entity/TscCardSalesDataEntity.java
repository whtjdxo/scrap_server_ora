package sales.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Getter
@Setter
@Table(name = "TSC_CARDSALES_DATA")
public class TscCardSalesDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @Column(name = "BCID")
    private String bcid;

    @Column(name = "MID")
    private String mid;

    @Column(name = "APP_NO")
    private String appNo;

    @Column(name = "CARD_COMPANY")
    private String cardCompany;

    @Column(name = "CARD_TYPE")
    private String cardType;

    @Column(name = "CARD_NO")
    private String cardNo;

    @Column(name = "ISTM_MONTH")
    private String istmMonth;

    @Column(name = "APP_AMOUNT")
    private Long appAmount;

    @Column(name = "FEE_AMOUNT")
    private Long feeAmount;

    @Column(name = "PAY_AMOUNT")
    private Long payAmount;

    @Column(name = "DDATE")
    private String ddate;

    @Column(name = "DTIME")
    private String dtime;

    @Column(name = "DCHECK")
    private String dcheck;

    @Column(name = "PDATE")
    private String pdate;

    @Column(name = "PAYDAY")
    private String payday;

    @Column(name = "SCRAP_DTM")
    private String scrapDtm;

    @Column(name = "SITE_ID")
    private String siteId;

    @Column(name = "PROC_FG")
    private String procFg;

    @Column(name = "PROC_RESULT")
    private String procResult;

    @Column(name = "USE_YN")
    private String useYn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ENT_DTTM")
    private Timestamp entDttm;

    // 새로 추가된 문자열 날짜 필드
    @Transient
    private String confDttmString;

    @Transient
    private String entDttmString;
}
