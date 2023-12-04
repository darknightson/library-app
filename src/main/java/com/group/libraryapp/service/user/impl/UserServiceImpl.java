package com.group.libraryapp.service.user.impl;

import com.group.libraryapp.domain.user.UserEntity;
import com.group.libraryapp.dto.user.UserResponse;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(String name, int age) {
        userRepository.save(UserEntity.builder().name(name).age(age).build());
    }

    @Override
    public List<UserResponse> getUsers() {
        List<UserEntity> findAllUser = userRepository.findAll();
        return findAllUser
                .stream()
                .map(userEntity -> UserResponse.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .age(userEntity.getAge())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateUser(Long id, String name, int age) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userEntity.updateUser(name, age);
    }

    @Override
    @Transactional
    public void deleteUser(String name) {
        userRepository.findByName(name)
                .ifPresent(userRepository::delete);
    }
}
