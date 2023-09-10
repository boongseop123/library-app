package com.weather.weather.controller.user;

import com.weather.weather.dto.user.reponse.UserResponse;
import com.weather.weather.dto.user.request.UserCreateRequest;
import com.weather.weather.dto.user.request.UserUpdateRequest;
import com.weather.weather.service.user.UserServiceV1;
import com.weather.weather.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;
    //private final List<User> users=new ArrayList<>();-->메모리에 저장하는 방법

    public UserController(UserServiceV2 userService) {//생성자를 만들어서 jdbcTemplate을 파라미터로 넣으면 자동으로 들어온다

        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) {
        //users.add(new User(request.getName(), request.getAge()));
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void UpdateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

    @GetMapping("/user/error-test")
    public void errortTest() {
        throw new IllegalArgumentException();
    }
}
