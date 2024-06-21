package sales.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="TCHAIN")

public class TbChainEntity {
    @Id
    @Column(name = "CHAIN_NO")
    private String chainNo;

    @Column(name = "CHAIN_NAME")
    private String chainNm;

    @Column(name = "CORP_NO")
    private String bizNo;

    @Column(name = "SVC_STAT")
    private String useYn;

    @Column(name = "CARDSALES_ID")
    private String cardSalesId;

    @Column(name = "CARDSALES_PWD")
    private String cardSalesPwd;

}
