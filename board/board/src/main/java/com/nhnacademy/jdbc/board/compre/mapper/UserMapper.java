package com.nhnacademy.jdbc.board.compre.mapper;

import com.nhnacademy.jdbc.board.compre.dto.UserDTO;
import com.nhnacademy.jdbc.board.compre.domain.User;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
@Mapper
public interface UserMapper {
    Integer selectUserNum(@Param("id") String id);

    String selectUserId(@Param("num") int num);

    Optional<User> doLogin(@Param("id") String id, @Param("password") String password);

    Optional<UserDTO> findUser(@Param("id") int id);
}
