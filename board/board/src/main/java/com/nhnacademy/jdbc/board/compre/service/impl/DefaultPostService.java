package com.nhnacademy.jdbc.board.compre.service.impl;

import com.nhnacademy.jdbc.board.compre.dao.PostDAO;
import com.nhnacademy.jdbc.board.compre.domain.Pagination;
import com.nhnacademy.jdbc.board.compre.domain.Post;
import com.nhnacademy.jdbc.board.compre.mapper.PostMapper;
import com.nhnacademy.jdbc.board.compre.service.PostService;
import com.nhnacademy.jdbc.board.compre.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;
    private final UserService userService;

    public DefaultPostService(PostMapper postMapper, DefaultUserService userService) {
        this.postMapper = postMapper;
        this.userService = userService;
    }

    @Override
    public Optional<Post> getPost(int id) {
        if (Objects.isNull(postMapper.selectPost(id))) {
            return Optional.empty();
        }
        return postMapper.selectPost(id);
    }

    @Override
    public List<Post> getPosts() {
        List<PostDAO> postDao = postMapper.selectPosts();
        List<Post> posts = new ArrayList<>();
        for (PostDAO postDAO : postDao) {
            posts.add(new Post(postDAO.getPostNo(),
                postDAO.getPostTitle(), (userService.getUserId(postDAO.getUserNo())),
                postDAO.getPostContent(), postDAO.getPostWriteDatetime(), postDAO.getPostHits()
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

    @Override
    public int getCount() {
        return this.postMapper.postCount();
    }

    @Override
    public List<PostDAO> getListPage(Pagination pagination) {
        return this.postMapper.getListPage(pagination);
    }
}
