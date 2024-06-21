package sales.api.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sales.api.dto.DeliveryDataDTO;
import sales.api.service.api.DeliveryDataService;

@RestController
public class DeliveryDataController {
    @Autowired
    DeliveryDataService deliveryDataService;

    @PostMapping("/api/delivery_data_upload")
    public String deliveryDataUpload(DeliveryDataDTO deliveryDataDTO){
        return deliveryDataService.deliveryDataUpload(deliveryDataDTO);
    }

}
