package com.group.libraryapp.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateRequest {

    private Long id;
    private String name;
    private int age;
}
