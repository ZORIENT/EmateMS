package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Bulletin;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.BulletinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BulletinController {
    @Autowired
    private BulletinService bulletinService;

    /*
    * 分页查询所有公告
    * */
    @GetMapping("/bulletin")
    public Result selectAllBulletin(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize){
        PageBean pageBean=bulletinService.selectAllBulletin(page,pageSize);

        return Result.success(pageBean);
    }

    /*
    * 根据id查询公告信息
    * */
    @GetMapping("/bulletin/{id}")
    public Result selectBulletinById(@PathVariable Integer id){
        Bulletin bulletin=bulletinService.selectBulletinById(id);

        return Result.success(bulletin);
    }

    /*
     * 批量删除公告信息
     * */
    @Log
    @DeleteMapping("/bulletin/{ids}")
    public Result deleteBulletinByIds(@PathVariable List<Integer> ids){
        bulletinService.deleteBulletinByIds(ids);

        return Result.success();
    }

    /*
    * 添加公告
    * */
    @Log
    @PostMapping("/bulletin")
    public Result insertBulletin(@RequestBody Bulletin bulletin){
        log.info("添加的广告信息为：{}",bulletin);
        bulletinService.insertBulletin(bulletin);

        return Result.success();
    }

    /*
     * 修改公告信息
     * */
    @Log
    @PutMapping("/bulletin")
    public Result updateBulletin(@RequestBody Bulletin bulletin){
        log.info("修改的公告信息为：{}",bulletin);
        bulletinService.updateBulletin(bulletin);

        return Result.success();
    }
}
