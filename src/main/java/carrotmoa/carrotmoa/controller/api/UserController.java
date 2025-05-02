package carrotmoa.carrotmoa.controller.api;

import carrotmoa.carrotmoa.model.request.UserAddressUpdateRequest;
import carrotmoa.carrotmoa.model.request.UserJoinDto;
import carrotmoa.carrotmoa.model.request.UserUpdateRequest;
import carrotmoa.carrotmoa.model.request.UserWithdrawalRequest;
import carrotmoa.carrotmoa.model.response.FindUserResponse;
import carrotmoa.carrotmoa.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/auth-code-send")
    public ResponseEntity<Boolean> authCodeEmailSend(@RequestParam("email") @Valid String email) {
        boolean result;
        try {
            userService.sendCodeToEmail(email);
            result = true;
        } catch (MailSendException e) {
            result = false;
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> userJoinSubmit(@RequestBody @Valid UserJoinDto userJoinDto) {
        return new ResponseEntity<Boolean>(userService.userJoin(userJoinDto), HttpStatus.OK);
    }

    @GetMapping("/email-check")
    public ResponseEntity<Boolean> emailCheck(@RequestParam("email") @Valid String email) {
        return new ResponseEntity<Boolean>(userService.emailCheck(email), HttpStatus.OK);
    }


    @GetMapping("/auth-code-certified")
    public ResponseEntity<Boolean> authCodeCertified(@RequestParam("email") String email, @RequestParam("inputauthcode") String inputAuthCode) {
        return new ResponseEntity<Boolean>(userService.authCodeCertified(email, inputAuthCode), HttpStatus.OK);
    }


    @GetMapping("/nickname-duplication")
    public ResponseEntity<Boolean> nicknameDuplication(@RequestParam("nickname") @Valid String nickname) {
        return new ResponseEntity<Boolean>(userService.nicknameCheck(nickname), HttpStatus.OK);
    }

    @GetMapping("/find-user/{searchType}/{searchKeyword}")
    public ResponseEntity<FindUserResponse> findUser(@PathVariable String searchType, @PathVariable String searchKeyword) {
        return new ResponseEntity<>(userService.findUserNickname(searchType, searchKeyword), HttpStatus.OK);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<Boolean> userProfileUpdate(@RequestBody UserUpdateRequest userUpdateRequestDto) {
        return new ResponseEntity<>(userService.userProfileUpdate(userUpdateRequestDto), HttpStatus.OK);
    }
    @PutMapping("/update-address")
    public ResponseEntity<Boolean> userAddressUpdate(@RequestBody UserAddressUpdateRequest request){
        return new ResponseEntity<>(userService.userAddressUpdate(request),HttpStatus.OK);
    }
    @DeleteMapping("/")
    public ResponseEntity<Boolean> userWithdrawal(@RequestBody UserWithdrawalRequest request, HttpSession session){
        return new ResponseEntity<>(userService.userWithdrawal(request,session), HttpStatus.OK);
    }



}

