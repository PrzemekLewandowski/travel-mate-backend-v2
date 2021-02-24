package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class LoginForm {
    @NotBlank
    @Size(min = 3, max = 60)
    private final String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private final String password;
}
