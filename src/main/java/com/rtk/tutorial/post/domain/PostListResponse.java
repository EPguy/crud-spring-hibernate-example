package com.rtk.tutorial.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PostListResponse {
    private int page;
    private int rowsPerPage;
    private int count;
    private List<PostEntity> posts;
}
