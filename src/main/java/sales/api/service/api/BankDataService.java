package sales.api.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sales.api.common.ConfigUtil;
import sales.api.common.StringPath;
import sales.api.dto.BankDataDTO;
import sales.api.dto.ChainBankAccountDTO;
import sales.api.entity.TscBankDataEntity;
import sales.api.repository.api.TbBankDataRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankDataService {
    @Autowired
    TbBankDataRepository tbBankDataRepository;

    public  String bankDataUpload(BankDataDTO bankDataDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String scrapGb = "BANK";
        String bankCd = bankDataDTO.getBankCd();
        String chainNo = bankDataDTO.getChainNo();
        String strData = bankDataDTO.getUploadData();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<TscBankDataEntity> dataList = objectMapper.readValue(strData, new TypeReference<List<TscBankDataEntity>>() {});
            try {
                for (TscBankDataEntity data : dataList) {
                    if (data.getEntDttmString() != null) {
                        Date parsedDate = dateFormat.parse(data.getEntDttmString());
                        data.setEntDttm(new Timestamp(parsedDate.getTime()));
                    }
                    try{
                        tbBankDataRepository.save(data);
                    } catch (DataIntegrityViolationException e) {     // 중복 오류 발생 시 무시..
                        System.out.println("data dup... pass >>" + e.getMessage());
                    }
                }
                tbBankDataRepository.writeScrapLog(scrapGb, bankCd, chainNo);
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

    public List<ChainBankAccountDTO> findMyBankAccountList(String bankCd, String compCd){
        String strDomain = ConfigUtil.getProperty("domain");
        String finalStrDomain = strDomain; // 사실상 final 변수로 사용

        List<Object[]> results = tbBankDataRepository.findMyBankAccountList(bankCd, compCd);
        return results.stream().map(record -> {
            ChainBankAccountDTO dto = new ChainBankAccountDTO();
            dto.setChainNo((String) record[0]);
            dto.setChainNm((String) record[1]);
            dto.setBankCd((String) record[2]);
            dto.setAccountNo((String) record[3]);
            dto.setAccountPwd((String) record[4]);
            dto.setCompTp((String) record[5]);
            dto.setBizNo((String) record[6]);
            dto.setBossRegNo((String) record[7]);
            dto.setAccountNm((String) record[8]);

            dto.setSubDomain(StringPath.getUserMsg(finalStrDomain)); // 사실상 final 변수 사용
            dto.setDes();
            dto.setAuthNo();

            return dto;

        }).collect(Collectors.toList());
    }
}
