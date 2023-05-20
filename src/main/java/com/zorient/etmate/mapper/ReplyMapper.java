package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Comment;
import com.zorient.etmate.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    List<Comment> selectFilmReply(Integer id);

    List<Comment> selectGameReply(Integer id);

    List<Comment> selectBookReply(Integer id);

    List<Reply> filmReplyToMine(Integer id);

    List<Reply> gameReplyToMine(Integer id);

    List<Reply> bookReplyToMine(Integer id);
}
