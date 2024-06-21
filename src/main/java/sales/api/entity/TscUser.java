package sales.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TSC_USER")
@Getter
@Setter

public class TscUser {

    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "COMP_CD")
    private String compCd;

    @Column(name = "USER_NM")
    private String userNm;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_AUTH")
    private String userAuth;

    @Column(name = "USE_YN")
    private String useYn;

    @Column(name = "LAST_LOGIN_DTTM")
    private String lastLoginDttm;

    @Column(name = "ENT_DTTM")
    private String entDttm;

    @Column(name = "ENT_USER_ID")
    private String entUserId;

    @Column(name = "UPT_DTTM")
    private String uptDttm;

    @Column(name = "UPT_USER_ID")
    private String uptUserId;
}


