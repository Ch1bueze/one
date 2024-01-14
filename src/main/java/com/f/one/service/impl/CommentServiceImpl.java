package com.f.one.service.impl;

import com.f.one.dto.AppResponse;
import com.f.one.dto.CommentDto;
import com.f.one.entity.Comment;
import com.f.one.entity.Post;
import com.f.one.exception.ResourceNotFoundException;
import com.f.one.repository.CommentRepository;
import com.f.one.repository.PostRepository;
import com.f.one.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper mapper;
    @Override
    public AppResponse AddComment(Long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId));
        comment.setPost(post);
        commentRepository.save(comment);
        return AppResponse.builder()
                .responseMessage("Comment Added successfully")
                .build();
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "ID", commentId));

        if (!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new RuntimeException("Comment does not belong to post");
        }
        return mapToDto(comment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "ID", commentId));

        if (!comment.getPost().getId().equals(post.getId())){
            throw new RuntimeException("Comment does not belong to post");
        }
        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
       return mapper.map(comment, CommentDto.class);
    }
    private Comment mapToEntity(CommentDto commentDto){
        return mapper.map(commentDto, Comment.class);
    }

}
