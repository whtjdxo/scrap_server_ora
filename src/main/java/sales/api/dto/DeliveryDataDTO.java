package sales.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryDataDTO {
    private String chainNo;      // 회원사 코드
    private String vanCd;       // 배달사업자 = 요기요, 배민, 쿠팡잇츠....
    private String uploadData;
}
