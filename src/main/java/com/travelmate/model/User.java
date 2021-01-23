package com.travelmate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private int bornYear;

    @NotBlank
    @Size(min = 3, max = 20)
    private String city;


    @Size(max = 200)
    private String infoAboutUser = "Napisz coś o sobie.";


    @NotBlank
    @Size(max = 30)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_countries",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private Set<Country> preferredCountries;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "participant_post",
            joinColumns = {@JoinColumn(name = "participant_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id")}
    )
    private Set<Post> enrolledPosts;

    @OneToMany(mappedBy = "postedByUsername")
    private Set<Post> posts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private String avatarFileName = "default-avatar.png";

    private int budgetValueFrom = 0;

    private int budgetValueTo = 10000;

    private Boolean isAccountClosed = false;
}
