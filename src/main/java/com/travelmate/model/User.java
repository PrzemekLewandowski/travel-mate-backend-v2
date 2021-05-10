package com.travelmate.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private String name;

    @Column(updatable = false)
    private String username;

    private int bornYear;

    private String city;

    @Builder.Default
    private String infoAboutUser = "Napisz coś o sobie.";

    private String email;

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

    @Builder.Default
    private String avatarFileName = "default-avatar.png";

    private int budgetValueFrom;

    @Builder.Default
    private int budgetValueTo = 10000;

    private boolean isAccountClosed;

    public void closeAccount() {
        this.isAccountClosed = true;
    }
}
