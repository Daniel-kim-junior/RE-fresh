package refresh.ManageSystem.repository;

import refresh.ManageSystem.dao.AnnualSumCountDAO;

public interface AnnualSumCountRepository {

    boolean setAnnualSumCount(AnnualSumCountDAO dao);
    boolean decreaseAnnualSumCount(AnnualSumCountDAO dao);
}
