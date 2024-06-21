package sales.api.repository.api;

import java.util.List;

public interface TbChainRepositoryCustom {
    List<Object[]> findMyCardSalesInfo(String compCd);
    void writeScrapLog(String scrapGb, String vanCd,String chainNo);
}
