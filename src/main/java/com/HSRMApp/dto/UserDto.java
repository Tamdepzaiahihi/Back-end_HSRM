package com.HSRMApp.dto;

import com.HSRMApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String uuid;
    private String username;
    private String firstName;
    private String lastName;
    private User user;
}
