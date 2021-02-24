package com.travelmate.viewmodel;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
public class UserViewModel {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    private int bornYear;

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<CountryViewModel> preferredCountries;

    @Size(max = 200)
    private String infoAboutUser;

    private int budgetValueFrom;

    private int budgetValueTo;

    private String avatarFileName;

    private Boolean isAccountClosed;
}
