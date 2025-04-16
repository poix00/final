package carrotmoa.carrotmoa.model.request;

import carrotmoa.carrotmoa.config.security.CustomUserDetails;
import carrotmoa.carrotmoa.entity.User;
import carrotmoa.carrotmoa.entity.UserProfile;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private long userId;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{2,12}$",
            message = "닉네임은 2~12자의 한글, 영문, 숫자만 사용 가능합니다.")
    private String nickname;

    private String name;
    private String phoneNumber;
    private String bio;
    private LocalDate birthday;

    public UserUpdateRequest(CustomUserDetails user) {
        this.userId = user.getUserProfile().getUserId();
        this.nickname = user.getUserProfile().getNickname();
        this.name = user.getUserProfile().getName();
        this.phoneNumber = user.getUserProfile().getPhoneNumber();
        this.bio = user.getUserProfile().getBio();
        this.birthday = user.getUserProfile().getBirthday();
    }

    public void updateUser(User user, UserProfile userProfile) {
        user.setName(this.name);
        userProfile.setNickname(this.nickname);
        userProfile.setBio(this.bio);
        userProfile.setBirthday(this.birthday);
        userProfile.setPhoneNumber(this.phoneNumber);
    }

}

