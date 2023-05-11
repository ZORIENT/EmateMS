package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Comment;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Rate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CommentService {
    PageBean selectByCondition(Integer userId, String username, Short type, Integer itemId, String itemName, LocalDate begin, LocalDate end,Integer page, Integer pageSize);

    void insertComment(Comment comment);

    void deleteByIds(List<Integer> ids);

    void updateComment(Comment comment);

    Rate getRates(Integer itemId,Short type);
}
