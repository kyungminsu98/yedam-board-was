package com.yedam.board_back.dto.response.board;

import com.yedam.board_back.common.ResponseCode;
import com.yedam.board_back.common.ResponseMessage;
import com.yedam.board_back.dto.object.BoardListItem;
import com.yedam.board_back.dto.response.ResponseDto;
import com.yedam.board_back.entity.BoardListViewEntity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class GetTop3BoardListResponseDto extends ResponseDto {
    
    private List<BoardListItem> top3List;
    
    private GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.top3List = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
        
}
