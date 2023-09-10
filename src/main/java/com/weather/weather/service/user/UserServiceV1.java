package com.weather.weather.service.user;

import com.weather.weather.dto.user.reponse.UserResponse;
import com.weather.weather.dto.user.request.UserCreateRequest;
import com.weather.weather.dto.user.request.UserUpdateRequest;
import com.weather.weather.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceV1 {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository){
        this.userJdbcRepository = userJdbcRepository;
    }

    public void updateUser(UserUpdateRequest request){
        if (userJdbcRepository.isUserNotExist(request.getId())){
            throw new IllegalArgumentException();
        }

       userJdbcRepository.updateUserName(request.getName(),request.getId());
    }

    public void deletesuer(String name){

        if (userJdbcRepository.isUserExist(name)) {
            throw new IllegalArgumentException();
        }
    }
    public void saveUser(UserCreateRequest request){
        userJdbcRepository.saveUser(request.getName(),request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userJdbcRepository.getUsers();
    }
}
