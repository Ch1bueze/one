package com.f.one.controller;

import com.f.one.dto.AppResponse;
import com.f.one.dto.CommentDto;
import com.f.one.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/one")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<AppResponse> addComment(@PathVariable(name = "postId") Long postId, @RequestBody CommentDto commentDto){
        return  new ResponseEntity<>(commentService.AddComment(postId, commentDto), HttpStatus.CREATED);
    }
    @GetMapping("/post/{postId}/comment")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId") Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("/post/{postId}/comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id){
        return new ResponseEntity<>(commentService.getCommentById(postId, id), HttpStatus.OK);
    }
    @DeleteMapping("/post/{postId}/comment/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "postId") Long postId, @PathVariable(name = "id") Long id){
        commentService.deleteComment(postId, id);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
