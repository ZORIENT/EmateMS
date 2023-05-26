package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.OperateLogMapper;
import com.zorient.etmate.pojo.OperateLog;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /*
    * 条件分页查询日志信息
    * */
    @Override
    public PageBean selectByCondition(String username, String email, String className, String methodName, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page,pageSize);
        List<OperateLog> operateLogList=operateLogMapper.selectByCondition(username,email,className,methodName,begin,end);
        Page<OperateLog> p= (Page<OperateLog>) operateLogList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询书籍：{}",pageBean);

        return pageBean;
    }

    /*
    * 批量删除日志信息
    * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        operateLogMapper.deleteByIds(ids);
    }
}
