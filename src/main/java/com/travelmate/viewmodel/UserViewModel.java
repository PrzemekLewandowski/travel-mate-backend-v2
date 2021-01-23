package com.travelmate.viewmodel;

import com.travelmate.model.Country;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class UserViewModel {
    @Id
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    private int bornYear;

    @NotBlank
    @Size(min = 3, max = 50)
    private String city;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<Country> preferredCountries;

    @Size(max = 200)
    private String infoAboutUser;

    private int budgetValueFrom;

    private int budgetValueTo;

    private String avatarFileName;

    private Boolean isAccountClosed;
}
