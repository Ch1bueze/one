package com.f.one.service.impl;

import com.f.one.dto.AppResponse;
import com.f.one.dto.PostDto;
import com.f.one.entity.Post;
import com.f.one.exception.ResourceNotFoundException;
import com.f.one.repository.PostRepository;
import com.f.one.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper mapper;


    @Override
    public AppResponse createPost(PostDto postDto) {
        Post newPost = Post.builder()
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();
        postRepository.save(newPost);
        return AppResponse.builder()
                .responseMessage("Post created successfully")
                .build();
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","ID",id));
        return mapToDto(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","ID",id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post){
        return mapper.map(post, PostDto.class);
    }
}
