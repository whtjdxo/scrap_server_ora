package sales.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(TbChainVanPK.class)
@Table(name="TB_COMP_VAN")
@Getter
@Setter

public class TbChainVanEntity {
    @Id
    @Column(name="CHAIN_NO")
    private String chainNo;

    @Id
    @Column(name="van_cd")
    private String vanCd;

    @Column(name="login_id")
    private String loginId;

    @Column(name="login_pwd")
    private String loginPwd;

    @Column(name="use_yn")
    private String useYn;

    @Column(name="last_dttm")
    private String lastDttm;

    @Transient
    private String siteUrl;
}
