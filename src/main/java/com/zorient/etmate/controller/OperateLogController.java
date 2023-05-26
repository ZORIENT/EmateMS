package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class OperateLogController {
    @Autowired
    private OperateLogService operateLogService;

    /*
     * 条件分页查询日志数据
     * */
    @GetMapping("/log")
    public Result selectByCondition(@RequestParam(required = false) String username,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String className,
                                    @RequestParam(required = false) String methodName,
                                    @RequestParam(required = false) LocalDate begin,
                                    @RequestParam(required = false) LocalDate end,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "20") Integer pageSize) {
        PageBean pageBean = operateLogService.selectByCondition(username, email, className,
                methodName, begin, end, page, pageSize);

        return Result.success(pageBean);
    }

    /*
     * 批量删除日志信息
     * */
    @Log
    @DeleteMapping("/log/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        operateLogService.deleteByIds(ids);

        return Result.success();
    }

}
