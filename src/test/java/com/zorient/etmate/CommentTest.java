package com.zorient.etmate;

import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.pojo.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testSelectFilmComment() {
        List<Comment> commentList = commentMapper.selectFilmComment(
                null, "暴走", (short) 1, null, null, null, null, 0);
        log.info(commentList.toString());
    }

    @Test
    public void testSelectGameComment() {
        List<Comment> commentList = commentMapper.selectGameComment(
                null, "暴走", (short) 2, null, null, null, null, 0);
        log.info(commentList.toString());
    }

    @Test
    public void testSelectBookComment() {
        List<Comment> commentList = commentMapper.selectBookComment(
                null, "暴走", (short) 3, null, null, null, null, 0);
        log.info(commentList.toString());
    }

    @Test
    public void testInsertComment() {
        Comment comment = new Comment(null, 8, null, 1, null, null, null, null,
                (short) 3, 0, "插入评论测试", (short) 4, LocalDateTime.now(), LocalDateTime.now());

        commentMapper.insertComment(comment);
    }

    @Test
    public void testUpdateComment() {
        Comment comment = new Comment(5, null, null, null, null, null, null,
                null, null, null, "修改评论测试", (short) 1, LocalDateTime.now(), LocalDateTime.now());

        commentMapper.updateComment(comment);
    }

    @Test
    public void testGetRateAvg() {
        Float rateAvg = commentMapper.getRateAvg(1, (short) 3);
        Integer rateNum = commentMapper.getRateNum(1, (short) 3);
        Integer rate1=commentMapper.getRate(1,(short)3,(short)1);


        log.info("rateAvg:{},rateNum:{},rate1:{}", rateAvg,rateNum,rate1);

    }

}
