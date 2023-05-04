package com.zorient.etmate.controller;

import com.zorient.etmate.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /*
    * 条件分页查询
    * */
    @GetMapping("/book")


}
