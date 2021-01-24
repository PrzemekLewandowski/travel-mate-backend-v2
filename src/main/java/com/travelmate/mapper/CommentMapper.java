package com.travelmate.mapper;

import com.travelmate.model.Comment;
import com.travelmate.viewmodel.CommentViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentViewModel toUserViewModel(Comment comment);
}
