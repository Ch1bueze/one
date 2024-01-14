package com.f.one.service;

import com.f.one.dto.AppResponse;
import com.f.one.dto.PostDto;

public interface PostService {
    AppResponse createPost(PostDto postDto);
    PostDto getPostById(Long id);
    void deletePost(Long id);
}
