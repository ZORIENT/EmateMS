package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Comment;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Rate;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    /*
     * 分页条件查询评论信息
     * */
    @GetMapping("/comment")
    public Result selectByCondition(@RequestParam(required = false) Integer userId,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) Integer itemId,
                                    @RequestParam(required = false) String itemName,
                                    @RequestParam(required = false) LocalDate begin,
                                    @RequestParam(required = false) LocalDate end,
                                    @RequestParam(defaultValue = "1") Short type,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "35") Integer pageSize) {
        log.info("userId:{},username:{},type:{},itemId:{},itemName:{},begin:{},end:{}",
                userId,username,type,itemId,itemName,begin,end);

        PageBean pageBean=commentService.selectByCondition(userId,username,type,itemId,itemName,begin,end,page,pageSize);

        return Result.success(pageBean);
    }

    /*
    * 添加评论
    * */
    @Log
    @PostMapping("/comment")
    public Result insertComment(@RequestBody Comment comment){
        log.info("添加的评论信息为：{}",comment);
        commentService.insertComment(comment);

        return Result.success();
    }

    /*
    * 批量删除评论
    * */
    @Log
    @DeleteMapping("/comment/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        commentService.deleteByIds(ids);

        return Result.success();
    }

    /*
    * 评论修改
    * */
    @Log
    @PutMapping("/comment")
    public Result updateComment(@RequestBody Comment comment){
        log.info("更新的评论信息为：{}",comment);
        commentService.updateComment(comment);

        return Result.success();
    }

    /*
    * 获取对应电影、游戏、书籍的详细评分信息
    * */
    @GetMapping("/rate")
    public Result getRates(@RequestParam(required = true) Integer itemId,
                           @RequestParam(required = true) Short type){
        log.info("itemId:{},type:{}",itemId,type);
        Rate rate=commentService.getRates(itemId,type);

        return Result.success(rate);
    }



}
