package com.demo.hackaton1.user.dto;

import com.demo.hackaton1.user.domain.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {
    @NotNull
    private String fullName;
    @Email
    private String email;
    private String password;        // opcional
    @NotNull
    private Role role;
}