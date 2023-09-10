package com.weather.weather.service.user;

import com.weather.weather.domain.user.User;
import com.weather.weather.domain.user.UserRepository;
import com.weather.weather.dto.user.reponse.UserResponse;
import com.weather.weather.dto.user.request.UserCreateRequest;
import com.weather.weather.dto.user.request.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository){
        this.userRepository= userRepository;
    }
    //아래있는 함수가 시작될때 start transaction을 해준다
    //예외없이 잘 끝났다면 commit
    //문제가 있다면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(),request.getAge()));
    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(user->new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user=userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        //userRepository.save(user);없어도 된다. 변경감지 기능(영속성 컨텍스트 안에서 하기 때문)
    }

    public void deleteUser(String name){
        User user=userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        if(user==null){
            throw new IllegalArgumentException();
        }
        userRepository.delete(user);
    }
}
