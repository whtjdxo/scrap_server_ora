package sales.api.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sales.api.dto.DeliveryDataDTO;
import sales.api.entity.TscDeliveryDataEntity;
import sales.api.repository.api.TbDeliveryDataRepository;
import sales.api.repository.api.TbChainVanRepoistory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DeliveryDataService {
    @Autowired
    TbDeliveryDataRepository tbDeliveryDataRepository;
    @Autowired
    private TbChainVanRepoistory tbChainVanRepoistory;

    public  String deliveryDataUpload(DeliveryDataDTO deliveryDataDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String scrapGb  = "VAN";
        String chainNo  = deliveryDataDTO.getChainNo();
        String vanCd    = deliveryDataDTO.getVanCd();
        String strData  = deliveryDataDTO.getUploadData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            List<TscDeliveryDataEntity> dataList = objectMapper.readValue(strData, new TypeReference<List<TscDeliveryDataEntity>>() {});
            try {
                for (TscDeliveryDataEntity data : dataList) {
                    if (data.getEntDttmString() != null) {
                        Date parsedDate = dateFormat.parse(data.getEntDttmString());
                        data.setEntDttm(new Timestamp(parsedDate.getTime()));
                    }
                    try {
                        tbDeliveryDataRepository.save(data);
                    } catch (DataIntegrityViolationException  e) {     // 중복 오류 발생 시 무시..
                        System.out.println("Duplicaton Errore >>" + e.getMessage());
                    }
                }
                // 작업이 완료되면. 작업 시간 UPDATE
                tbChainVanRepoistory.writeScrapLog(scrapGb, vanCd, chainNo);
                System.out.println("Data Save Complete!.");
                return "1, Data Save Success ";
            } catch (Exception e) {
                System.out.println("0, Error: " + e.getMessage());
                return "0, Error : " + e.getMessage();
            }
        } catch (Exception e) {
            System.out.println("0, Error: " + e.getMessage());
            return "0, Error : " + e.getMessage();
        }
    }
}
