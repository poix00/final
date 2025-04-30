package carrotmoa.carrotmoa.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String code; // 오류 코드
    private String message; // 오류 메시지
    private LocalDateTime timestamp;


}
