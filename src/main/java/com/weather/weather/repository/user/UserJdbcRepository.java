package com.weather.weather.repository.user;

import com.weather.weather.dto.user.reponse.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class UserJdbcRepository {

    private final JdbcTemplate jdbcTemplate;//변수로 만들고

    public UserJdbcRepository(JdbcTemplate jdbcTemplate) {//생성자를 통해 인스턴스화해서 jdbctemplate을 넘긴다
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist( long id){
    String readsql="SELECT * FROM user WHERE id=?";
    return jdbcTemplate.query(readsql,(rs, rowNum) -> 0,id).isEmpty();
}

public void updateUserName(String name, long id){
    String sql="UPDATE user SET name =? WHERE id= ?";
    jdbcTemplate.update(sql,name,id);
}
public boolean isUserExist(String name){
    String readsql="SELECT * FROM user WHERE name=?";
    return jdbcTemplate.query(readsql,(rs, rowNum) -> 0,name).isEmpty();
}

public void deleteUser(String name){
        String sql="DELETE FROM user WHERE name =?";
        jdbcTemplate.update(sql,name);
}
public void saveUser(String name, Integer age){
    String sql="INSERT INTO user (name, age) VALUES (?,?)";
    jdbcTemplate.update(sql, name,age);//데이터의 변경인 update지 crud의 update가 아님
}
public List<UserResponse> getUsers(){
    String sql="SELECT * FROM user";
    return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
        @Override//오버라이드와 익명클래스?--->자바의 람다로 표현이 가능하다
        public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id=rs.getLong("id");
            String name=rs.getString("name");
            int age=rs.getInt("age");
            return new UserResponse(id,name,age);
        }
    });

}
}
