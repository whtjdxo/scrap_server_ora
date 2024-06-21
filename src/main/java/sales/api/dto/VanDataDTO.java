package sales.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VanDataDTO {
//    private List<TbVanDataEntity> vanData;
//
    private String chainNo;      // 회원사 코드
    private String vanCd;       // scrap van, pg 사 코드
    private String vanData;     // 매출 정보 Data - json

}
