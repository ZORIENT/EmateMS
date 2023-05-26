package com.zorient.etmate.controller;

import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.pojo.SearchResult;
import com.zorient.etmate.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public Result search(@RequestParam String keyword,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("搜索的关键词是：{}",keyword);
        SearchResult searchResult=searchService.search(keyword,page,pageSize);

        return Result.success(searchResult);
    }

}
