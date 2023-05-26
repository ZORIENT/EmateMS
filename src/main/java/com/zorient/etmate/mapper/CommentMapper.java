package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectFilmComment(Integer userId, String username, Short type, Integer itemId, String itemName, LocalDate begin, LocalDate end, Integer parentId);

    List<Comment> selectGameComment(Integer userId, String username, Short type, Integer itemId, String itemName, LocalDate begin, LocalDate end, Integer parentId);

    List<Comment> selectBookComment(Integer userId, String username, Short type, Integer itemId, String itemName, LocalDate begin, LocalDate end, Integer parentId);

    void insertComment(Comment comment);

    void deleteParentComment(List<Integer> ids);

    void deleteChildrenComment(List<Integer> ids);

    void updateComment(Comment comment);

    @Select("SELECT AVG( score ) FROM tb_comment WHERE item_id = #{itemId} AND type = #{type} AND parent_id = 0")
    Float getRateAvg(Integer itemId,Short type);

    @Select("SELECT count(*) FROM tb_comment WHERE item_id = #{itemId} AND type = #{type} AND parent_id = 0")
    Integer getRateNum(Integer itemId, Short type);

    @Select("SELECT count(*) FROM tb_comment WHERE item_id = #{itemId} AND type = #{type} AND score = #{score} AND parent_id = 0")
    Integer getRate(Integer itemId, Short type, short score);

    /*
    * 根据电影、游戏、书籍的id删除与其相关的评论
    * */
    @Delete("delete from tb_comment where item_id=#{id} and type=#{type}")
    void deleteCommentsByItemId(Integer id,Short type);

}
