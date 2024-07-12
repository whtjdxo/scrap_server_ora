package sales.api.repository.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TbChainRepositoryCustomImpl implements TbChainRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findMyCardSalesInfo(String chainNo) {
        String sql = "";
        sql += "SELECT TC.CHAIN_NO AS CHAIN_NO      " ;
        sql +=  "      , TC.CHAIN_NAME  AS CHAIN_NM " ;
        sql += "       , TC.CARDSALES_ID            " ;
        sql += "       , TC.CARDSALES_PWD           " ;
        sql += "  FROM TCHAIN TC                    " ;
        sql += " WHERE TC.SVC_STAT IN ('O', 'R')    " ;
        sql += "   AND TC.CARDSALES_ID IS NOT NULL  " ;
        // compCd 값이 존재하는 경우에만 조건 추가
        if (chainNo != null && !chainNo.isEmpty()) {
            sql += " AND TC.CHAIN_NO = '" + chainNo + "'      ";
        }

        sql +=  "   AND  NOT EXISTS(SELECT 1 FROM TSC_SCRAP_LOG SL          ";
        sql +=  "                    WHERE SL.CHAIN_NO  = TC.CHAIN_NO       ";
        sql +=  "                      AND SL.SCRAP_GB  = 'CS'              ";
        sql +=  "                      AND SL.VAN_CD    = 'CS'              ";
        sql +=  "                      AND SL.LAST_SCRAP_DTTM >= (SYSDATE - INTERVAL '90' MINUTE )";
        sql +=  "                   )                                       ";
        sql +=  " ORDER BY TC.CHAIN_NO                       ";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void writeScrapLog(String scrapGb, String vanCd, String chainNo){
        String sql ="";
        sql += "INSERT INTO TSC_SCRAP_LOG (                     ";
        sql += "    CHAIN_NO, SCRAP_GB, VAN_CD, LAST_SCRAP_DTTM ";
        sql += ") VALUES (                                      ";
        sql += "     :chainNo, :scrapGb, :vanCd, SYSDATE        ";
        sql += ")                                               ";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("chainNo", chainNo);
        query.setParameter("scrapGb", scrapGb);
        query.setParameter("vanCd", vanCd);
        query.executeUpdate();
    }
}
