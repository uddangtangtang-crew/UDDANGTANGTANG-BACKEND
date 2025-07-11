package com.uddangtangtang.global.apiPayload.code.status;

import com.uddangtangtang.global.apiPayload.code.BaseErrorCode;
import com.uddangtangtang.global.apiPayload.code.ErrorReasonDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorStatus implements BaseErrorCode {


    /* ===== 공통 ===== */
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST           (HttpStatus.BAD_REQUEST,         "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED          (HttpStatus.UNAUTHORIZED,        "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN             (HttpStatus.FORBIDDEN,           "COMMON403", "금지된 요청입니다."),

    /* ===== AI 응답 ===== */
    AI_PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "AI_001", "AI에게 잘못된 응답을 받았습니다."),


    /* ===== 유형 테스트 ===== */
    TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "TRAVEL_TYPE_001", "해당 여행 유형을 찾을 수 없습니다."),

    /* ===== 공유 유형 생성 ===== */
    RESULT_NOT_FOUND(HttpStatus.NOT_FOUND, "TRAVEL_TYPE_001", "해당 테스트 결과가 존재하지 않습니다."),

    NULL_IS_NOT_ALLOWED(HttpStatus.NOT_FOUND,"TRAVEL_TYPE_001","여행 유형은 빈 값이어서는 안 됩니다"),

    /* ===== 검증/리소스 ===== */
    VALIDATION_ERROR ("COMMON400A", "유효성 검증 실패"),
    NOT_FOUND        (HttpStatus.NOT_FOUND, "COMMON404", "리소스를 찾을 수 없습니다.")
    ;

    /* ---------- 필드 ---------- */
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    /* ---------- 생성자 ---------- */
    // 3-파라미터 : HttpStatus 직접 지정
    ErrorStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code       = code;
        this.message    = message;
    }

    // 2-파라미터 : BAD_REQUEST 기본
    ErrorStatus(String code, String message) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.code       = code;
        this.message    = message;
    }

    /* ---------- 인터페이스 구현 ---------- */
    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }

}
