package sales.api.repository.api;

import java.util.List;

public interface TbBankDataRepositoryCustom {
    List<Object[]> findMyBankAccountList(String bankCd, String chainNo);
    void writeScrapLog(String scrapGb, String bankCd, String chainNo);
}
