package com.zorient.etmate.service;

import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface OperateLogService {

    PageBean selectByCondition(String username, String email, String className, String methodName, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    void deleteByIds(List<Integer> ids);
}
