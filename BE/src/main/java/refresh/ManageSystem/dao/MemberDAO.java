package refresh.ManageSystem.dao;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

@Builder @ToString
public class MemberDAO {
    @NonNull private String memberId;
    @NonNull private String memberPassword;
}
