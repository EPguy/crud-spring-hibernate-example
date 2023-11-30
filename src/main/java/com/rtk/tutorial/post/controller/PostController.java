package com.rtk.tutorial.post.controller;

import com.rtk.tutorial.post.domain.PostEntity;
import com.rtk.tutorial.post.domain.PostListResponse;
import com.rtk.tutorial.post.service.PostService;
import com.rtk.tutorial.common.domain.BaseResponse;
import com.rtk.tutorial.common.exception.CustomException;
import com.rtk.tutorial.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rtk")
public class PostController {
    private final PostService postService;

    @GetMapping("post")
    public BaseResponse<PostListResponse> findAllPost(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "rowsPerPage") int rowsPerPage
    ) {
        int boardListCount = postService.getPostListCount();
        List<PostEntity> posts = postService.getPostList(page, rowsPerPage);
        PostListResponse postListResponse = PostListResponse.builder()
                .rowsPerPage(rowsPerPage)
                .page(page)
                .count(boardListCount)
                .posts(posts)
                .build();
        return BaseResponse.Success(postListResponse);
    }

    @GetMapping("post/{seq}")
    public BaseResponse<PostEntity> findPost(@PathVariable Long seq) {
        return BaseResponse.Success(postService.getPost(seq));
    }

    @PostMapping("post")
    public BaseResponse<PostEntity> savePost(@RequestBody PostEntity postEntity) {
        try {
            return BaseResponse.Success(postService.insertPost(postEntity));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.POST_INSERT_FAILED);
        }
    }

    @DeleteMapping("post")
    public BaseResponse<PostEntity> deletePost(@RequestBody PostEntity postEntity) {
        try {
            return BaseResponse.Success(postService.deletePost(postEntity));
        } catch (Exception e) {
            throw new CustomException(ErrorCode.POST_DELETE_FAILED);
        }
    }

    @PutMapping("post")
    public BaseResponse<PostEntity> updatePost(@RequestBody PostEntity postEntity) {
        try {
            return BaseResponse.Success(postService.updatePost(postEntity));
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ErrorCode.POST_EDIT_FAILED);
        }
    }
}
