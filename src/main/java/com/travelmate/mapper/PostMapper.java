package com.travelmate.mapper;

import com.travelmate.model.Post;
import com.travelmate.viewmodel.PostViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class, CommentMapper.class, CountryMapper.class})
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostViewModel toPostViewModel(Post post);
}
