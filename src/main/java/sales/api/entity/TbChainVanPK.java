package sales.api.entity;

import java.io.Serializable;
import java.util.Objects;

public class TbChainVanPK implements Serializable {
    private String chainNo;
    private String vanCd;

    // 기본 생성자
    public TbChainVanPK() {}

    // 매개변수 있는 생성자
    public TbChainVanPK(String chainNo, String vanCd) {
        this.chainNo = chainNo;
        this.vanCd = vanCd;
    }

    // equals 및 hashCode 메서드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TbChainVanPK that = (TbChainVanPK) o;
        return Objects.equals(chainNo, that.chainNo) && Objects.equals(vanCd, that.vanCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chainNo, vanCd);
    }

    // getters and setters
    public String getChainNo() {
        return chainNo;
    }

    public void setChainNo(String chainNo) {
        this.chainNo = chainNo;
    }

    public String getVanCd() {
        return vanCd;
    }

    public void setVanCd(String vanCd) {
        this.vanCd = vanCd;
    }
}
