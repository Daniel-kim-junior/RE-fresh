package refresh.ManageSystem.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberLoginDTO {
    @NonNull private String id;
    @NonNull private String password;

}
