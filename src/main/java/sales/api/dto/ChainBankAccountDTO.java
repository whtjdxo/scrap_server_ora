package sales.api.dto;

import lombok.Getter;
import lombok.Setter;
import sales.api.common.EncryptUtilOwra;

@Getter
@Setter
public class ChainBankAccountDTO {
    private String chainNo;
    private String chainNm;
    private String bankCd;
    private String accountNo;
    private String accountPwd;
    private String accountNm;
    private String compTp;
    private String bizNo;
    private String bossRegNo;
    private String authNo;
    private String subDomain;

    public void setAuthNo(){
        if(compTp.equals("PERS")){
            this.authNo = this.bossRegNo.substring(0, 6);
        } else {
            this.authNo = this.bizNo.replaceAll("-", "").substring(5, 10);
        }
    }

    public String getAuthNo(){
        return this.authNo;
    }
    // 복호화된 값 설정
    public void setDes() {

        if (accountNo != null && !accountNo.equals("")) {
            accountNo = EncryptUtilOwra.decrypt(accountNo, subDomain);
        }
        if (accountPwd != null && !accountPwd.equals("")) {
            accountPwd = EncryptUtilOwra.decrypt(accountPwd, subDomain);
        }
        if (bizNo != null && !bizNo.equals("")) {
            bizNo = EncryptUtilOwra.decrypt(bizNo, subDomain);
        }
        if (bossRegNo != null && !bossRegNo.equals("")) {
            bossRegNo = EncryptUtilOwra.decrypt(bossRegNo, subDomain);
        }
    }

    // 암호화된 값 설정
    public void setEnc() {
        if (accountNo != null && !accountNo.equals("")) {
            accountNo = EncryptUtilOwra.encrypt(accountNo, subDomain);
        }
        if (accountPwd != null && !accountPwd.equals("")) {
            accountPwd = EncryptUtilOwra.encrypt(accountPwd, subDomain);
        }

        if (bizNo != null && !bizNo.equals("")) {
            bizNo = EncryptUtilOwra.encrypt(bizNo, subDomain);
        }

        if (bossRegNo != null && !bossRegNo.equals("")) {
            bossRegNo = EncryptUtilOwra.encrypt(bossRegNo, subDomain);
        }
    }
}
