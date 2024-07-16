package sales.api.repository.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TbBankDataRepositoryCustomImpl implements TbBankDataRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findMyBankAccountList(String bankCd, String chainNo) {
        String sql = "";
        sql += "SELECT  COMP_CD, COMP_NM, BANK_CD, ACCOUNT_NO, ACCOUNT_PWD, CORP_TYPE           ";
        sql += "        , BIZ_NO, BOSS_REGNO, ACCOUNT_NM                                        ";
        sql += "  FROM  (                                                                       ";
        sql += "        SELECT TC.CHAIN_NO          AS COMP_CD                                  ";
        sql += "                , TC.CHAIN_NAME     AS COMP_NM                                  ";
        sql += "                , TC.ABANK          AS BANK_CD                                  ";
        sql += "                , TC.AACCOUNT       AS ACCOUNT_NO                               ";
        sql += "                , TC.AACCOUNT_PWD   AS ACCOUNT_PWD                              ";
        sql += "                , DECODE(NVL(TC.CORP_NO, 'X'), 'X', 'PERS', 'COMP') AS CORP_TYPE";
        sql += "                , TC.CHAIN_BIZ_NO       AS BIZ_NO                               ";
        sql += "                , P.PERSON_REGNO        AS BOSS_REGNO                           ";
        sql += "                , TC.ADEPOSITOR         AS ACCOUNT_NM                           ";
        sql += "          FROM  TCHAIN TC                                                       ";
        sql += "                JOIN TPERSON P ON P.PERSON_NO = TC.PERSON_NO                    ";
        sql += "         WHERE  TC.SVC_STAT IN ('O', 'R')                                       ";
        if (chainNo != null && !chainNo.isEmpty()) {
            sql += "          AND   TC.CHAIN_NO = '" + chainNo + "'                             ";
        }
        sql += "          AND  NOT EXISTS(SELECT 1 FROM TSC_SCRAP_LOG SL                        ";
        sql += "                WHERE SL.CHAIN_NO  = TC.CHAIN_NO                                ";
        sql += "                AND SL.SCRAP_GB  = 'BANK'                                       ";
        sql += "                AND SL.VAN_CD    = '" + bankCd + "'                            ";
        sql += "                AND SL.LAST_SCRAP_DTTM >= (SYSDATE - INTERVAL '90' MINUTE )     ";
        sql += "                   )                                                            ";
        sql += "         UNION  ALL                                                             ";
        sql += "        SELECT  CREDIT_CD           AS CHAIN_CD                                 ";
        sql += "                , CREDIT_NAME                                                   ";
        sql += "                , CRD_BANK          AS BANK_CD                                  ";
        sql += "                , CRD_ACCOUNT       AS ACCOUNT_NO                               ";
        sql += "                , CRD_ACCOUNT_PWD   AS ACCOUNT_PWD                              ";
        sql += "                , 'COMP'            AS CORP_TYPE                                ";
        sql += "                , CRD_BIZ_NO        AS BIZ_NO                                   ";
        sql += "                , ''                AS BOSS_REG_NO                              ";
        sql += "                , OPERATOR_NAME     AS ACCOUNT_NM                               ";
        sql += "          FROM  TCREDIT CR                                                      ";
        sql += "         WHERE  DEL = 'O'                                                       ";
        sql += "           AND  NOT EXISTS(                                                     ";
        sql += "                    SELECT 1 FROM TSC_SCRAP_LOG SL                              ";
        sql += "                     WHERE SL.CHAIN_NO  = CR.CREDIT_CD                          ";
        sql += "                       AND SL.SCRAP_GB  = 'BANK'                                ";
        sql += "                       AND SL.VAN_CD    = '" + bankCd + "'                      ";
        sql += "                       AND SL.LAST_SCRAP_DTTM >= (SYSDATE - INTERVAL '90' MINUTE )";
        sql += "                          )                                                     ";
        sql += "        ) VT                                                                    ";
        sql += " ORDER  BY VT.COMP_CD                                                           ";

        System.out.println(sql);
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
        sql += "     :chainNo, :scrapGb, :vanCd, SYSDATE         ";
        sql += ")                                               ";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("chainNo", chainNo);
        query.setParameter("scrapGb", scrapGb);
        query.setParameter("vanCd", vanCd);
        query.executeUpdate();
    }
}
