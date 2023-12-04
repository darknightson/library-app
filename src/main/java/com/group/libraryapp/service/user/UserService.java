package com.group.libraryapp.service.user;


import com.group.libraryapp.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    void saveUser(String name, int age);
    List<UserResponse> getUsers();
    void updateUser(Long id, String name, int age);
    void deleteUser(String name);
}
