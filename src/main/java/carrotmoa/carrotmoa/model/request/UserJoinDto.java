package carrotmoa.carrotmoa.model.request;

import carrotmoa.carrotmoa.entity.Account;
import carrotmoa.carrotmoa.entity.User;
import carrotmoa.carrotmoa.entity.UserProfile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserJoinDto {
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*\\-])[a-zA-Z0-9#?!@$%^&*\\-]{8,32}$",
            message = "비밀번호는 8~32자이며, 영문 대소문자, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{2,12}$",
            message = "닉네임은 2~12자의 한글, 영문, 숫자만 사용 가능합니다.")
    private String nickname;

    private Long authorityId;
    private Boolean isWithdrawal = false;
    private String bankName;
    private Integer accountNumber;
    private String accountHolder;
    private long userId;
    private int state = 0;

    public User toUserEntity() {
        return User.builder()
            .email(this.getEmail())
            .password(this.getPassword())
            .authorityId(this.getAuthorityId())
            .isWithdrawal(this.getIsWithdrawal())
            .state(this.getState())
            .build();
    }

    public UserProfile toUserProfileEntity(long userId) {
        return UserProfile.builder()
            .userId(userId)
            .nickname(this.getNickname())
            .build();
    }

    public Account toHostAdditionalFormEntity(long userId) {
        return carrotmoa.carrotmoa.entity.Account.builder()
            .userId(userId)
            .bankName(this.getBankName())
            .accountNumber(this.getAccountNumber())
            .accountHolder(this.getAccountHolder())
            .build();
    }
}

