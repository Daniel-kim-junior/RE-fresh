package refresh.ManageSystem.repository;

import refresh.ManageSystem.dao.AnnualSumCountDAO;

public interface AnnualSumCountRepository {

    int setAnnualSumCount(AnnualSumCountDAO dao);
    int decreaseAnnualSumCount(AnnualSumCountDAO dao);
}
