package com.rtk.tutorial.post.service;

import com.rtk.tutorial.post.domain.PostEntity;
import com.rtk.tutorial.post.repository.PostRepository;
import com.rtk.tutorial.common.exception.CustomException;
import com.rtk.tutorial.common.exception.ErrorCode;
import com.rtk.tutorial.common.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SHA256 sha256;

    public List<PostEntity> getPostList(int page, int rowsPerPage) {
        return postRepository.findAllBy(PageRequest.of(page, rowsPerPage));
    }

    public int getPostListCount() {
        return postRepository.getPostListCount();
    }

    public PostEntity getPost(Long seq) {
        Optional<PostEntity> post = postRepository.findById(seq);
        if(post.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_EXIST);
        } else {
            return post.get();
        }
    }

    public PostEntity insertPost(PostEntity postEntity) throws NoSuchAlgorithmException {
        postEntity.encryptPassword();
        return postRepository.save(postEntity);
    }

    public PostEntity deletePost(PostEntity postEntity) throws NoSuchAlgorithmException {
        Optional<PostEntity> post = postRepository.findById(postEntity.getSeq());

        if(post.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_EXIST);
        } else if(!sha256.encrypt(postEntity.getPassword()).equals(post.get().getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        } else {
            postRepository.updateDeleteFlag(post.get().getSeq());
            return post.get();
        }
    }

    public PostEntity updatePost(PostEntity postEntity) throws NoSuchAlgorithmException {
        Optional<PostEntity> post = postRepository.findById(postEntity.getSeq());

        if(post.isEmpty()) {
            throw new CustomException(ErrorCode.POST_NOT_EXIST);
        }else if(!sha256.encrypt(postEntity.getPassword()).equals(post.get().getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_INCORRECT);
        } else {
            postEntity.encryptPassword();
            postRepository.save(postEntity);
            return postEntity;
        }
    }
}
