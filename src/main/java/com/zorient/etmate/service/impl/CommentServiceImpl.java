package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.pojo.Comment;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Rate;
import com.zorient.etmate.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /*
     * 条件分页查询评论信息
     * */
    @Override
    public PageBean selectByCondition(Integer userId, String username, Short type, Integer itemId, String itemName,
                                      LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //使用pagehelper设置分页参数
        PageHelper.startPage(page, pageSize);
        List<Comment> commentList = null;

        switch (type) {
            case 1:
                commentList = commentMapper.selectFilmComment(userId, username, type, itemId, itemName, begin, end, 0);

                commentList.forEach(comment -> {
                    comment.setChildren(commentMapper.selectFilmComment(null, null,
                            comment.getType(), comment.getItemId(), null, null, null, comment.getId()));
                });
                break;
            case 2:
                commentList = commentMapper.selectGameComment(userId, username, type, itemId, itemName, begin, end, 0);

                commentList.forEach(comment -> {
                    comment.setChildren(commentMapper.selectGameComment(null, null,
                            comment.getType(), comment.getItemId(), null, null, null, comment.getId()));
                });
                break;
            case 3:
                commentList = commentMapper.selectBookComment(userId, username, type, itemId, itemName, begin, end, 0);

                commentList.forEach(comment -> {
                    comment.setChildren(commentMapper.selectBookComment(null, null,
                            comment.getType(), comment.getItemId(), null, null, null, comment.getId()));
                });

                break;
            default:
                throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        }

        Page<Comment> p = (Page<Comment>) commentList;

        //结果封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        log.info("条件查询评论：{}", pageBean);

        return pageBean;
    }

    /*
     * 添加评论
     * */
    @Override
    public void insertComment(Comment comment) {
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());

        commentMapper.insertComment(comment);
    }

    /*
     * 批量删除评论信息
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        //1.批量删除父级评论
        commentMapper.deleteParentComment(ids);
        //2.删除所有子级评论
        commentMapper.deleteChildrenComment(ids);
    }

    /*
     * 更新评论信息
     * */
    @Override
    public void updateComment(Comment comment) {
        comment.setUpdateTime(LocalDateTime.now());

        commentMapper.updateComment(comment);
    }

    /*
     * 获取对应电影、游戏、书籍的评分信息
     * */
    @Override
    public Rate getRates(Integer itemId, Short type) {
        Float rateAvg = commentMapper.getRateAvg(itemId, type);
        Integer rateNum = commentMapper.getRateNum(itemId, type);
        Integer rate1 = commentMapper.getRate(itemId, type, (short) 1);
        Integer rate2 = commentMapper.getRate(itemId, type, (short) 2);
        Integer rate3 = commentMapper.getRate(itemId, type, (short) 3);
        Integer rate4 = commentMapper.getRate(itemId, type, (short) 4);
        Integer rate5 = commentMapper.getRate(itemId, type, (short) 5);

        Rate rate = new Rate(rateAvg, rateNum, rate1, rate2, rate3, rate4, rate5);

        return rate;
    }
}
