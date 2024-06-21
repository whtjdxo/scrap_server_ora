package sales.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Getter
@Setter
@Table(name = "TSC_DELIVERY_DATA")
public class TscDeliveryDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private String seq;

    @Column(name="VAN_CD")
    private String vanCd;

    @Column(name="CHAIN_NO")
    private String chainNo;

    @Column(name="ORDER_NO")
    private String orderNo;

    @Column(name="ORDER_DTTM")
    private String orderDttm;

    @Column(name="ORDER_TYPE")
    private String orderType;

    @Column(name="CAMPAIGN_ID")
    private String campaignId;

    @Column(name="ORDER_NM")
    private String orderNm;

    @Column(name="PAY_TYPE")
    private String payType;

    @Column(name="DELI_TYPE")
    private String deliType;

    @Column(name="ORDER_AMT")
    private long   orderAmt;

    @Column(name="ST_INFO_A")
    private String stInfoA;

    @Column(name="ST_INFO_B")
    private String stInfoB;

    @Column(name="ST_INFO_C")
    private String stInfoC;

    @Column(name="ST_INFO_D")
    private String stInfoD;

    @Column(name="ST_INFO_E")
    private String stInfoE;

    @Column(name="ST_INFO_F")
    private String stInfoF;

    @Column(name="ST_DEPOSIT_AMT")
    private long   stDepositAmt;

    @Column(name="ST_DEPOSIT_DT")
    private String stDepositDt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ENT_DTTM")
    private Timestamp entDttm;

    // 새로 추가된 문자열 날짜 필드
    @Transient
    private String confDttmString;

    @Transient
    private String entDttmString;

}
