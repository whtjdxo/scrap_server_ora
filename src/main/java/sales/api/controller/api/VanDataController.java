package sales.api.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.api.dto.*;

import sales.api.service.api.VanDataService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VanDataController {
    @Autowired
    private VanDataService vanDataService;

    @PostMapping ("/api/upload_json")
    public String uploadVan(VanDataDTO vanDataDTO){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            // DB에서 사용자 추가 정보 가져오기
//            System.out.println(vanDataDTO.getVanData());
            String rslt =  vanDataService.uploadVanData(username, vanDataDTO);
            System.out.println("data upload Result = " + rslt);
            return rslt;
        }
        return "0,Please Check You're Login Info ~!!";
    }

    @GetMapping("/api/comp_list")
    public List<ChainVanDTO> getCompList(){
//        return vanDataService.findCompanyScrapInfoList();
        List<ChainVanDTO> compList = new ArrayList<>();
        compList = vanDataService.findCompanyScrapInfoList();

        return compList;
    }

    @PostMapping("/api/my_comp_list")
    public List<ChainVanDTO> getMyCompList(ChainVanDTO chainVanDTO){
        String vanCd = chainVanDTO.getVanCd();
        String chainNo = chainVanDTO.getChainNo();
        return vanDataService.findMyCompanyScrapInfoList(vanCd, chainNo);
    }

}