package sales.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="TSC_CARD_INFO_SYNC")
public class TscCardInfoSyncEntity {
    @Id
    @Column(name = "SEQ")
    private String Seq;

    @Column(name = "CHAIN_NO")
    private String chainNo;
    @Column(name = "CARD_ACQ_NM")
    private String cardAcqNm;
    @Column(name = "CARD_REG_NO")
    private String cardRegNo;

    @Column(name = "BANK_NM")
    private String bankNm;
    @Column(name = "BANK_ACCOUNT_NO")
    private String bankAccountNo;

    @Column(name = "CARD_RATE")
    private String  cardRate;

    @Column(name = "CHECK_RATE")
    private String  checkRate;

    @Column(name = "ABROAD_RATE")
    private String abroadRate;

    @Column(name = "CARD_IN_CYCLE")
    private String  cardInCycle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ENT_DTTM")
    private Timestamp entDttm;

    @Transient
    private String entDttmString;

}
