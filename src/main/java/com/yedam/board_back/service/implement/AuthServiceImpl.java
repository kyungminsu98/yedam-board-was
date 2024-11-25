package com.yedam.board_back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yedam.board_back.dto.request.auth.SignInRequestDto;
import com.yedam.board_back.dto.request.auth.SignUpRequestDto;
import com.yedam.board_back.dto.response.ResponseDto;
import com.yedam.board_back.dto.response.auth.SignInResponseDto;
import com.yedam.board_back.dto.response.auth.SignUpResponseDto;
import com.yedam.board_back.entity.UserEntity;
import com.yedam.board_back.provider.JwtProvider;
import com.yedam.board_back.repository.UserRepository;
import com.yedam.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        try {
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail) return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber) return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String token = null;
    
        try {
            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
    
            // 사용자 존재 여부 확인
            if (userEntity == null) return SignInResponseDto.signInFail();
    
            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
    
            // 비밀번호 일치 여부 확인
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if (!isMatched) return SignInResponseDto.signInFail();
    
            // JWT 토큰 생성
            token = jwtProvider.create(email);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        // 성공적으로 로그인한 경우 JWT 토큰과 함께 응답
        return SignInResponseDto.success(token);
    }
}
