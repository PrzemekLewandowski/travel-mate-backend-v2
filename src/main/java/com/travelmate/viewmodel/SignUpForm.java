package com.travelmate.viewmodel;

import com.travelmate.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Getter
@Setter
@Builder
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 3, max = 50)
    private String address;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<Role> roles;

    @NotNull
    private int bornYear;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
