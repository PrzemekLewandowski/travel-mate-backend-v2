package com.travelmate.mapper;

import com.travelmate.model.User;
import com.travelmate.viewmodel.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CountryMapper.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserViewModel toUserViewModel(User user);
}
