package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Collection;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /*
    * 条件分页查询收藏信息
    * */
    @GetMapping("/collection")
    public Result selectByCondition(@RequestParam(required = true) Integer userId,
                                    @RequestParam(required = false) Integer collectionId,
                                    @RequestParam(defaultValue = "1") Short type,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "35") Integer pageSize){
        PageBean pageBean=collectionService.selectByCondition(userId,collectionId,type,page,pageSize);

        return Result.success(pageBean);
    }

    /*
    * 用户添加收藏
    * */
    @PostMapping("/collection")
    public Result insertCollection(@RequestBody Collection collection){
        log.info("添加的收藏信息为：{}",collection);
        collectionService.insertCollection(collection);

        return Result.success();
    }

    /*
    * 用户取消收藏(一次只能取消一个)
    * */
    @DeleteMapping("/collection/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("取消收藏的id：{}",id);
        collectionService.deleteById(id);

        return Result.success();
    }
}
