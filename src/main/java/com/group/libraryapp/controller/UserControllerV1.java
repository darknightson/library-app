package com.group.libraryapp.controller;

import com.group.libraryapp.dto.user.UserCreateRequest;
import com.group.libraryapp.dto.user.UserResponse;
import com.group.libraryapp.dto.user.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
//@RestController
@RequiredArgsConstructor
public class UserControllerV1 {

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, userCreateRequest.getName(), userCreateRequest.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "SELECT id, name, age FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                return UserResponse.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .age(rs.getInt("age"))
                        .build();
            }
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {

        String readSql = "SELECT * FROM user WHERE id = ?";
        boolean isUserNotExists = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, userUpdateRequest.getId()).isEmpty();

        if (isUserNotExists) {
          throw new IllegalArgumentException();
        }

        String sql = "UPDATE user SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, userUpdateRequest.getName(), userUpdateRequest.getAge(), userUpdateRequest.getId());
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
