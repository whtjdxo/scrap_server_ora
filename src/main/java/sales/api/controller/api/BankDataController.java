package sales.api.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.api.dto.BankDataDTO;
import sales.api.dto.ChainBankAccountDTO;
import sales.api.service.api.BankDataService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BankDataController {
    @Autowired
    BankDataService bankDataService;

    @PostMapping("/api/bank_data_upload")
    public String bankDataUpload(BankDataDTO bankDataDTO){
        String rslt =  bankDataService.bankDataUpload(bankDataDTO);
        System.out.println("data upload Result = " + rslt);
        return rslt;

    }

    @PostMapping("/api/my_bank_list")
    public List<ChainBankAccountDTO> getMyBankAccountList(ChainBankAccountDTO chainBankAccountDTO){
        List<ChainBankAccountDTO> rsltDto = new ArrayList<>();

        String bankCd = chainBankAccountDTO.getBankCd();
        String chainNo = chainBankAccountDTO.getChainNo();
//        String accountNo = chainBankAccountDTO.getAccountNo();
        return bankDataService.findMyBankAccountList(bankCd, chainNo);

    }
}
