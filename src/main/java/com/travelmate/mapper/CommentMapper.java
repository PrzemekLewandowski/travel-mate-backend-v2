package com.travelmate.mapper;

import com.travelmate.model.Comment;
import com.travelmate.viewmodel.CommentViewModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentViewModel toUserViewModel(Comment comment);
}
