package com.travelmate.mapper;

import com.travelmate.model.Post;
import com.travelmate.viewmodel.PostViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostViewModel toPostViewModel(Post post);
}
