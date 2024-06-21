package sales.api.dto;

import lombok.Getter;
import lombok.Setter;
import sales.api.common.EncryptUtilOwra;


@Getter
@Setter
public class ChainDTO {
    private String chainNo;
    private String chainNm;
    private String bizNo;
    private String cardSalesId;
    private String cardSalesPwd;
    private String lastCsScrapDttm;
    private String UseYn;
    private String subDomain;

    // 복호화된 값 설정
    public void setDes() {

        if (cardSalesPwd != null && !cardSalesPwd.equals("")) {
            cardSalesPwd = EncryptUtilOwra.decrypt(cardSalesPwd, subDomain);
        }
    }
    // 암호화된 값 설정
    public void setEnc() {
        if (cardSalesPwd != null && !cardSalesPwd.equals("")) {
            cardSalesPwd = EncryptUtilOwra.encrypt(cardSalesPwd, subDomain);
        }
    }
}
