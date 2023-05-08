package refresh.ManageSystem.repository;

import refresh.ManageSystem.dao.AnnualSumCountDAO;

public interface AnnualSumCountRepository {

    int setAnnualSumCount(AnnualSumCountDAO dao);
    int decreaseAnnualSumCount(AnnualSumCountDAO dao);
    // 테스트용 db 테이블 제거 쿼리
    void dropTable();
}
