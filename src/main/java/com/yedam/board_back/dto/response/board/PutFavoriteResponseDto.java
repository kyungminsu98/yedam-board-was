package com.yedam.board_back.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.yedam.board_back.common.ResponseCode;
import com.yedam.board_back.common.ResponseMessage;
import com.yedam.board_back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {
    // 성공 응답 생성자
    private PutFavoriteResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    // 성공 응답을 반환하는 메서드
    public static ResponseEntity<PutFavoriteResponseDto> success() {
        PutFavoriteResponseDto result = new PutFavoriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 게시물이 존재하지 않을 때의 응답을 반환하는 메서드
    public static ResponseEntity<ResponseDto> noExistBoard() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    // 사용자가 존재하지 않을 때의 응답을 반환하는 메서드
    public static ResponseEntity<ResponseDto> noExistUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
