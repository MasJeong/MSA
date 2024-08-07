package com.example.userservice.user.controller;

import com.example.userservice.user.domain.UserEntity;
import com.example.userservice.user.dto.UserDto;
import com.example.userservice.user.service.UserService;
import com.example.userservice.user.vo.RequestUser;
import com.example.userservice.user.vo.ResponseUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    /**
     * 사용자 전체 목록 조회
     * @return 사용자 목록
     */
    @GetMapping
    public ResponseEntity<List<ResponseUser>> getUsers() {

        List<UserEntity> userList = userService.getAllUsers();
        List<ResponseUser> responseUsers = new ArrayList<>();

        userList.forEach(user -> responseUsers.add(modelMapper.map(user, ResponseUser.class)));

        return ResponseEntity.status(HttpStatus.OK).body(responseUsers);
    }

    /**
     * 사용자 상세정보 조회
     * @param userId 사용자 아이디
     * @return 사용자 상세정보
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

    /**
     * 사용자 정보 저장
     * @param requestUser 저장할 요청 정보
     * @return responseUser
     */
    @PostMapping
    public ResponseEntity<ResponseUser> createUser(@RequestBody @Valid RequestUser requestUser) {

        UserDto userDto = modelMapper.map(requestUser, UserDto.class);

        userService.createUser(userDto);

        ResponseUser responseUser = modelMapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
