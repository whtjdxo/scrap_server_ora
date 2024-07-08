package sales.api.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.api.dto.CardInfoSyncDTO;
import sales.api.dto.CardSalesDataDTO;
import sales.api.dto.ChainDTO;
import sales.api.service.api.CardSalesDataService;

import java.util.List;

@RestController
public class CardSalesDataController {
    @Autowired
    CardSalesDataService cardSalesDataService;

    @PostMapping("/api/my_cardsales_list")
    public List<ChainDTO> getMyCardSalesList(ChainDTO chainDTO){
        String compCd = chainDTO.getChainNo();
        return cardSalesDataService.findMyCardSalesInfoList(compCd);
    }

    @PostMapping ("/api/upload_cardsales_json")
    public String uploadCardSales(CardSalesDataDTO cardSalesDataDTO){
        String rslt =  cardSalesDataService.uploadCardSaleData( cardSalesDataDTO);
        return rslt;
    }

    @PostMapping ("/api/sync_cardinfo")
    public String syncCardFeeInfo(CardInfoSyncDTO cardInfoSyncDTO){
        String rslt =  cardSalesDataService.syncCardFeeInfo(cardInfoSyncDTO);
        return rslt;
    }
}
