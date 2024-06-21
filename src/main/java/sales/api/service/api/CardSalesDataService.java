package sales.api.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sales.api.common.ConfigUtil;
import sales.api.common.StringPath;
import sales.api.dto.CardSalesDataDTO;
import sales.api.dto.ChainDTO;
import sales.api.entity.TscCardSalesDataEntity;
import sales.api.repository.api.TbCardSalesDataRepository;
import sales.api.repository.api.TbChainRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardSalesDataService {
    @Autowired
    TbChainRepository tbChainRepository;
    @Autowired
    TbCardSalesDataRepository tbCardSalesDataRepository;

    public List<ChainDTO> findMyCardSalesInfoList(String compCd){
        String strDomain = ConfigUtil.getProperty("domain");
        String finalStrDomain = strDomain; // 사실상 final 변수로 사용

        List<Object[]> results = tbChainRepository.findMyCardSalesInfo(compCd);
        return results.stream().map(record -> {
            ChainDTO dto = new ChainDTO();
            dto.setChainNo((String) record[0]);
            dto.setChainNm((String) record[1]);
            dto.setCardSalesId((String) record[2]);
            dto.setCardSalesPwd((String) record[3]);

            dto.setSubDomain(StringPath.getUserMsg(finalStrDomain)); // 사실상 final 변수 사용
            dto.setDes();
            return dto;
        }).collect(Collectors.toList());
    }


    public  String uploadCardSaleData(CardSalesDataDTO cardSalesDataDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String scrapGb  = "CS";
        String vanCd    = "CS";
        String chainNo   = cardSalesDataDTO.getChainNo();
        String strData = cardSalesDataDTO.getUploadData();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
            List<TscCardSalesDataEntity> dataList = objectMapper.readValue(strData, new TypeReference<List<TscCardSalesDataEntity>>() {});
            for (TscCardSalesDataEntity data : dataList) {
                if (data.getEntDttmString() != null) {
                    Date parsedDate = dateFormat.parse(data.getEntDttmString());
                    data.setEntDttm(new Timestamp(parsedDate.getTime()));
                }
                try{
                    tbCardSalesDataRepository.save(data);
                } catch (DataIntegrityViolationException e) {     // 중복 오류 발생 시 무시..
                    System.out.println("중복 오류 발생: 무시하고 진행 >>" + e.getMessage());
                }
            }
            tbChainRepository.writeScrapLog(scrapGb, vanCd, chainNo);
            System.out.println("SAVE Data Complete!.");
            return "1, Hola~!";
        } catch (Exception e) {
            System.out.println("0, Error: " + e.getMessage());
            return "0, Error : " + e.getMessage();
        }
    }
}
