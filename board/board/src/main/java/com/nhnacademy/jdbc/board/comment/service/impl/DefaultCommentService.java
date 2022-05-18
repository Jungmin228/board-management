package com.nhnacademy.jdbc.board.comment.service.impl;

import com.nhnacademy.jdbc.board.comment.domain.Comment;
import com.nhnacademy.jdbc.board.comment.mapper.CommentMapper;
import com.nhnacademy.jdbc.board.comment.service.CommentService;
import com.nhnacademy.jdbc.board.post.domain.Post;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultCommentService implements CommentService {

    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public Optional<Comment> getComment(int id) {
        if (Objects.isNull(commentMapper.selectComment(id))) {
            return Optional.empty();
        }
        return commentMapper.selectComment(id);
    }

    @Override
    public List<Comment> getComments() {
        return commentMapper.selectComments();
    }

    @Override
    public void register(Comment comment) {
        commentMapper.commentRegister(comment);
    }

    @Override
    public void update(int id, Comment comment) {
        commentMapper.commentUpdate(id, comment);
    }

    @Override
    public void delete(int id) {
        commentMapper.commentDelete(id);
    }
}
