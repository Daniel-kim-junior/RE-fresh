package refresh.ManageSystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AnnualHistoryDTO {
    @NonNull
    private String annualUid;
    private String acceptor;
    @NonNull
    private String annualType;
    @NonNull
    private String annualStatus;
    @NonNull
    private String startDate;
    @NonNull
    private String endDate;
    @NonNull
    private String createTm;
}
