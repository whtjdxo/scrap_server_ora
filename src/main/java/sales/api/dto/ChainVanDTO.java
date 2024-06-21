package sales.api.dto;

import lombok.Getter;
import lombok.Setter;
import sales.api.common.EncryptUtilOwra;
//import OwraEncrypt.Encrypt;

@Getter
@Setter
public class ChainVanDTO {
    private String chainNo;
    private String chainNm;
    private String bizNo;
    private String vanCd;
    private String vanNm;
    private String siteUrl;
    private String loginId;
    private String loginPwd;
    private String subDomain;


    // 복호화된 값 설정
    public void setDes() {
        if (bizNo != null && !bizNo.equals("")) {
            bizNo = EncryptUtilOwra.decrypt(bizNo, subDomain);
        }
        if (loginPwd != null && !loginPwd.equals("")) {
            loginPwd = EncryptUtilOwra.decrypt(loginPwd, subDomain);
        }
    }

    // 암호화된 값 설정
    public void setEnc() {
        if (bizNo != null && !bizNo.equals("")) {
            bizNo = EncryptUtilOwra.encrypt(bizNo, subDomain);
        }
        if (loginPwd != null && !loginPwd.equals("")) {
            loginPwd = EncryptUtilOwra.encrypt(loginPwd, subDomain);
        }
    }
//    Encrypt enc = new Encrypt();
//    public void setDes() {
//        if (bizNo != null && !bizNo.equals("")) {
//            bizNo = enc.DesJar(bizNo, subDomain);
//        }
//        if (loginPwd != null && !loginPwd.equals("")) {
//            loginPwd = enc.DesJar(loginPwd, subDomain);
//        }
//    }
//    // 암호화된 값 설정
//    public void setEnc() {
//        if (bizNo != null && !bizNo.equals("")) {
//            bizNo = enc.EncJar(bizNo, subDomain);
//        }
//        if (loginPwd != null && !loginPwd.equals("")) {
//            loginPwd = enc.EncJar(loginPwd, subDomain);
//        }
//    }
}
