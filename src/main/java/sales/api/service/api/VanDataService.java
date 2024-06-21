package sales.api.service.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import sales.api.common.StringPath;
import sales.api.dto.ChainVanDTO;
import sales.api.dto.VanDataDTO;
import sales.api.entity.TscVanDataEntity;
import sales.api.repository.api.TbChainVanRepoistory;
import sales.api.repository.api.TbVanDataRepository;
import sales.api.common.ConfigUtil;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VanDataService {
    @Autowired
    private TbVanDataRepository tbVanDataRepository;
    @Autowired
    private TbChainVanRepoistory tbChainVanRepoistory;

    public String uploadVanData(String userId, VanDataDTO vanDataDTO){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String scrapGb      = "VAN";
        String chainNo      = vanDataDTO.getChainNo();
        String vanCd        = vanDataDTO.getVanCd();
        String strData      = vanDataDTO.getVanData();

        try {
            List<TscVanDataEntity> dataList = objectMapper.readValue(strData, new TypeReference<List<TscVanDataEntity>>() {});
             try {
                for (TscVanDataEntity data : dataList) {
                    if (data.getEntDttmString() != null) {
                        Date parsedDate = dateFormat.parse(data.getEntDttmString());
                        data.setEntDttm(new Timestamp(parsedDate.getTime()));
                    }
                    try{
                        tbVanDataRepository.save(data);
                    } catch (DataIntegrityViolationException e) {     // 중복 오류 발생 시 무시..
                        System.out.println("중복 오류 발생: 무시하고 진행 >>" + e.getMessage());
                    }
                }
                // 작업이 완료되면. 작업 시간 insert
                tbChainVanRepoistory.writeScrapLog(scrapGb, vanCd, chainNo);
                System.out.println("SAVE Data Complete!.");
                return "1, Hola~!";
            } catch (Exception e) {
                System.out.println("0, Error: " + e.getMessage());
                return "0, Error : " + e.getMessage();
            }
        } catch (Exception e) {
            System.out.println("0, Error: " + e.getMessage());
            return "0, Error : " + e.getMessage();
        }
//        return "1, Sucess";
    }

    public List<ChainVanDTO> findCompanyScrapInfoList(){
        String strDomain = ConfigUtil.getProperty("domain");
        String finalStrDomain = strDomain; // 사실상 final 변수로 사용

        List<Object[]> results = tbChainVanRepoistory.findAllCompVanInfo();
        return results.stream().map(record -> {
            ChainVanDTO dto = new ChainVanDTO();
            dto.setChainNo((String) record[0]);
            dto.setChainNm((String) record[1]);
            dto.setBizNo((String) record[2]);
            dto.setVanCd((String) record[3]);
            dto.setVanNm((String) record[4]);
            dto.setLoginId((String) record[5]);
            dto.setLoginPwd((String) record[6]);

            dto.setSubDomain(StringPath.getUserMsg(finalStrDomain)); // 사실상 final 변수 사용
            dto.setDes();
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ChainVanDTO> findMyCompanyScrapInfoList(String vanCd, String chainNo){
        String strDomain = ConfigUtil.getProperty("domain");
//        System.out.println("domain : >>>>>> " + strDomain);
        String finalStrDomain = strDomain; // 사실상 final 변수로 사용

        List<Object[]> results = tbChainVanRepoistory.findMyCompVanInfo(vanCd, chainNo);

        return results.stream().map(record -> {
            ChainVanDTO dto = new ChainVanDTO();
            dto.setChainNo((String) record[0]);
            dto.setChainNm((String) record[1]);
            dto.setBizNo((String) record[2]);
            dto.setVanCd((String) record[3]);
            dto.setVanNm((String) record[4]);
            dto.setLoginId((String) record[5]);
            dto.setLoginPwd((String) record[6]);

            dto.setSubDomain(StringPath.getUserMsg(finalStrDomain)); // 사실상 final 변수 사용
            dto.setDes();
            return dto;
        }).collect(Collectors.toList());
    }

}
