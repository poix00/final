package carrotmoa.carrotmoa.model.request;

import lombok.Data;

@Data
public class UserWithdrawalRequest {
    private String email;
    private String password;

    public UserWithdrawalRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
