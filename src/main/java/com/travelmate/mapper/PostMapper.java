package com.travelmate.mapper;

import com.travelmate.model.Post;
import com.travelmate.viewmodel.PostViewModel;
import org.mapstruct.Mapper;

@Mapper(uses = {UserMapper.class, CommentMapper.class, CountryMapper.class}, componentModel = "spring")
public interface PostMapper {

    PostViewModel toPostViewModel(Post post);
}
