package sales.api.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter

@SqlResultSetMapping(
        name = "UserDTOResult",
        entities = {
                @EntityResult(
                        entityClass = UserDTO.class,
                        fields = {
                                @FieldResult(name = "userId", column = "user_id"),
                                @FieldResult(name = "userNm", column = "user_nm"),
                                @FieldResult(name = "compCd", column = "comp_cd"),
                                @FieldResult(name = "compNm", column = "comp_nm"),
                                @FieldResult(name = "userPwd", column = "user_pwd"),
                                @FieldResult(name = "useYn", column = "use_yn"),
                                @FieldResult(name = "userAuth", column = "user_auth")
                        }
                )
        }
)

public class UserDTO {
    private String userId;
    private String userNm;
    private String compCd;
    private String userPwd;
    private String userAuth;
    private String useYn;

    public UserDTO(String userId, String userNm, String compCd, String userPwd, String userAuth, String useYn) {
        this.userId = userId;
        this.userNm = userNm;
        this.compCd = compCd;
        this.userPwd = userPwd;
        this.userAuth = userAuth;
        this.useYn = useYn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getCompCd() {
        return compCd;
    }

    public void setCompCd(String compCd) {
        this.compCd = compCd;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }
}
