package com.inovision.commander.dto;

import com.inovision.commander.model.User;
import com.inovision.commander.model.UserRole;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String name;
    private String email;
    private String[] roles;

    public static UserDTO create(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        if(user.getRoles() != null && !user.getRoles().isEmpty()) {
            List<String> roles = user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
            dto.setRoles(roles.toArray(new String[] {}));
        }
        return dto;
    }
}
