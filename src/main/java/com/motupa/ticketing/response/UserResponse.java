package com.motupa.ticketing.response;

import com.motupa.ticketing.entity.Role;
import com.motupa.ticketing.entity.User;

public record UserResponse(Long id, String name, String email, Role role) {
    public static UserResponse from(User u) {
        return new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getRole());
    }

}
