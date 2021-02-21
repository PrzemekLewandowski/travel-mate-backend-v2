package com.travelmate.viewmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travelmate.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
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
    private String city;

    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    private Set<Role> roles;

    @NotNull
    private int bornYear;

    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 6, max = 40)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpForm that = (SignUpForm) o;
        return username.equals(that.username) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
