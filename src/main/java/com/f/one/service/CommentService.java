package com.f.one.service;

import com.f.one.dto.AppResponse;
import com.f.one.dto.CommentDto;

import java.util.List;

public interface CommentService {
    AppResponse AddComment(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentById(Long postId, Long commentId);
    void deleteComment(Long postId, Long commentId);
}
