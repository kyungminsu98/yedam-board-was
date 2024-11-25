package com.yedam.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.yedam.board_back.dto.response.ResponseDto;
import com.yedam.board_back.dto.response.search.GetPopularListResponseDto;
import com.yedam.board_back.dto.response.search.GetRelationListResponseDto;
import com.yedam.board_back.repository.SearchLogRepository;
import com.yedam.board_back.repository.resultSet.GetPopularListResultSet;
import com.yedam.board_back.repository.resultSet.GetRelationListResultSet;
import com.yedam.board_back.service.SearchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    
    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        
        List<GetPopularListResultSet> resultSets = new ArrayList<>();

        try {
            
            resultSets = searchLogRepository.getPopularList();


        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {
        
        List<GetRelationListResultSet> resultSets = new ArrayList<>();

        try {
                
            resultSets = searchLogRepository.getRelationList(searchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRelationListResponseDto.success(resultSets);
    }
}
