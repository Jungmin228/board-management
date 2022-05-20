package com.nhnacademy.jdbc.board.compre.service;

import com.nhnacademy.jdbc.board.compre.dto.CommentDTO;
import com.nhnacademy.jdbc.board.compre.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<CommentDTO> getComment(int id);

    List<Comment> getComments(int id);

    void register(int postNo, int userNo, String comment);

    void update(int id, String content);

    void delete(int id);
}
