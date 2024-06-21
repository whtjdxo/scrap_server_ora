package sales.api.repository.api;

import java.util.List;


public interface TbChainVanRepositoryCustom {
    List<Object[]> findAllCompVanInfo();
    List<Object[]> findMyCompVanInfo(String vanCd, String chainNo);
    void writeScrapLog(String scrapGb, String VanCd, String chainNo);

}
