package com.yedam.board_back.service;

import org.springframework.http.ResponseEntity;

import com.yedam.board_back.dto.request.board.PatchBoardRequestDto;
import com.yedam.board_back.dto.request.board.PostBoardRequestDto;
import com.yedam.board_back.dto.request.board.PostCommentRequestDto;
import com.yedam.board_back.dto.response.board.DeleteBoardResponseDto;
import com.yedam.board_back.dto.response.board.GetBoardResponseDto;
import com.yedam.board_back.dto.response.board.GetCommentListResponseDto;
import com.yedam.board_back.dto.response.board.GetFavoriteListResponseDto;
import com.yedam.board_back.dto.response.board.GetLatestBoardListResponseDto;
import com.yedam.board_back.dto.response.board.GetSearchBoardListResponseDto;
import com.yedam.board_back.dto.response.board.GetTop3BoardListResponseDto;
import com.yedam.board_back.dto.response.board.GetUserBoardListResponseDto;
import com.yedam.board_back.dto.response.board.IncreaseViewCountResponseDto;
import com.yedam.board_back.dto.response.board.PatchBoardResponseDto;
import com.yedam.board_back.dto.response.board.PostBoardResponseDto;
import com.yedam.board_back.dto.response.board.PostCommentResponseDto;
import com.yedam.board_back.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber, String email);

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();

    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);

    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);


}
