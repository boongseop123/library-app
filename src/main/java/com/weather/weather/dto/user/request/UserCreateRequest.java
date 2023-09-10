package com.weather.weather.dto.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age;//int는 null표현 불가, integer은 null표현 가능
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
}
