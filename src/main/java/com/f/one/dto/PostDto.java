package com.f.one.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostDto {
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> commentDtoSet;
}
