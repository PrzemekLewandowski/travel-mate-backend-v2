package com.travelmate.mapper;

import com.travelmate.model.User;
import com.travelmate.viewmodel.SignUpForm;
import com.travelmate.viewmodel.UserViewModel;
import org.mapstruct.Mapper;

@Mapper(uses = {CountryMapper.class}, componentModel = "spring")
public interface UserMapper {

    UserViewModel toUserViewModel(User user);

    User toUser(SignUpForm signUpForm);

    User toUser(UserViewModel userViewModel);
}
