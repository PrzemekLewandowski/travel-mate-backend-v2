package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class LoginForm {
    @NotBlank
    @Size(min = 3, max = 60)
    private final String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private final String password;
}
