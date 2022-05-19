package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.dao.PostDAO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.mapper.PostMapper;
import com.nhnacademy.jdbc.board.compre.service.CommentService;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;
    private final UserService userService;
    private final CommentService commentService;

    public DefaultPostService(PostMapper postMapper, DefaultUserService userService, DefaultCommentService commentService) {
        this.postMapper = postMapper;
        this.userService = userService;
        this.commentService = commentService;
    }

    @Override
    public Optional<Post> getPost(int id) {
        if (Objects.isNull(postMapper.selectPost(id))) {
            return Optional.empty();
        }
        PostDAO pod = postMapper.selectPost(id).get();
        return Optional.of(new Post(pod.getPostNo(),
            pod.getPostTitle(), (userService.getUserId(pod.getUserNo())),
            pod.getPostContent(), pod.getPostWriteDatetime(), pod.getPostHits()));
    }

    @Override
    public List<Post> getPosts() {
        List<PostDAO> postDao = postMapper.selectPosts();
        List<Post> posts = new ArrayList<>();
        for (PostDAO postDAO : postDao) {
            List<Comment> comment = commentService.getComments(postDAO.getPostNo());
            posts.add(new Post(postDAO.getPostNo(),
                postDAO.getPostTitle(), (userService.getUserId(postDAO.getUserNo())),
                postDAO.getPostContent(), postDAO.getPostWriteDatetime(), comment.size()
                ));
        }
        return posts;
    }

    @Override
    public void register(Post post, int num) {
        postMapper.postRegister(post, num);
    }

    @Override
    public void update(int id, Post post) {
        postMapper.postUpdate(id, post);
    }

    @Override
    public void delete(int id) {
        postMapper.postDelete(id);
    }

}
