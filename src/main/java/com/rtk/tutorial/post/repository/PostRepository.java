package com.rtk.tutorial.post.repository;

import com.rtk.tutorial.post.domain.PostEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query("select p from post p where p.deleteFlag = false order by p.seq desc")
    List<PostEntity> findAllBy(Pageable page);

    @Query("select count(p) from post p where p.deleteFlag = false")
    int getPostListCount();

    @Modifying
    @Query("update post p set p.deleteFlag = true where p.seq = :seq")
    void updateDeleteFlag(@Param("seq") Long seq);
}
