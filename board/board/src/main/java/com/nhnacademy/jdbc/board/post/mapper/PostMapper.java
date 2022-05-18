package com.nhnacademy.jdbc.board.post.mapper;

import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Optional;

public interface PostMapper {

    Optional<Post> selectPost(int id);

    List<Post> selectPosts();

    void postRegister(Post post);

    void postUpdate(int id, Post post);

    void postDelete(int id);
}