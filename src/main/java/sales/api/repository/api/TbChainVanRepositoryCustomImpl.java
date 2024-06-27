package sales.api.repository.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TbChainVanRepositoryCustomImpl implements TbChainVanRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findAllCompVanInfo() {
        String sql = "SELECT TC.CHAIN_NO AS COMP_CD         " +
                "        , TC.CHAIN_NAME AS COMP_NM         " +
                "        , TC.CHAIN_BIZ_NO AS BIZ_NO        " +
                "        , TM.VAN                           " +
                "        , CD.CODE_NAME AS VAN_NM           " +
                "        , TM.VAN_ID         AS LOGIN_ID    " +
                "        , TM.VAN_PWD        AS LOGIN_PWD   " +
                "  FROM TCHAIN TC                                       " +
                "        JOIN TTERMINAL TM ON TM.CHAIN_NO = TC.CHAIN_NO " +
                "                AND TM.DEL IS NULL                     " +
                "                AND TERM_STAT = '설치'                   " +
                "        JOIN TCODE_MAST CD ON CD.CODE = TM.VAN         " +
                "            AND CD.PCODE = 'CORP_VAN'" +
                "            AND CD.USE_YN = 'Y'    " +
                " WHERE  TC.SVC_STAT = 'O'          ";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @Override
    public List<Object[]> findMyCompVanInfo(String vanCd, String chainNo) {
        String sql ="SELECT TC.CHAIN_NO AS COMP_CD              " +
                    "       , TC.CHAIN_NAME AS COMP_NM         " +
                    "       , TC.CHAIN_BIZ_NO AS BIZ_NO        " +
                    "       , TM.VAN                           " +
                    "       , CD.CODE_NAME AS VAN_NM           " +
                    "       , TM.VAN_ID         AS LOGIN_ID    " +
                    "       , TM.VAN_PWD        AS LOGIN_PWD   " +
                    "  FROM TCHAIN TC                                       " +
                    "       JOIN TTERMINAL TM ON TM.CHAIN_NO = TC.CHAIN_NO " +
                    "                AND TM.DEL IS NULL             " +
                    "                AND TM.VAN = '" + vanCd +"'    " +
                    "                AND TERM_STAT = '설치'           " +
                    "       JOIN TCODE_MAST CD ON CD.CODE = TM.VAN " +
                    "            AND CD.PCODE = 'CORP_VAN'          " +
                    "            AND CD.USE_YN = 'Y'                ";
        sql +=  " WHERE  TC.SVC_STAT = 'O'          ";
        // compCd 값이 존재하는 경우에만 조건 추가
        if (chainNo != null && !chainNo.isEmpty()) {
            sql += " AND TC.CHAIN_NO = '" + chainNo + "'  ";
        }

        sql +=  "   AND  NOT EXISTS(SELECT 1 FROM TSC_SCRAP_LOG SL          ";
        sql +=  "                    WHERE SL.CHAIN_NO  = TC.CHAIN_NO       ";
        sql +=  "                      AND SL.SCRAP_GB  = 'VAN'             ";
        sql +=  "                      AND SL.VAN_CD    = '" + vanCd +"'    ";
        sql +=  "                      AND SL.LAST_SCRAP_DTTM >= (SYSDATE - INTERVAL '90' MINUTE )";
        sql +=  "                   )                                       ";
        sql +=  " ORDER BY TC.CHAIN_NO              ";
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
        sql += "     :chainNo, :scrapGb, :vanCd, SYSDATE       ";
        sql += ")                                               ";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("chainNo", chainNo);
        query.setParameter("scrapGb", scrapGb);
        query.setParameter("vanCd", vanCd);
        query.executeUpdate();
    }
}