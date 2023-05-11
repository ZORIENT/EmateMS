package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    /*
    * 条件分页查询
    * */
    @GetMapping("/book")
    public Result selectByCondition(@RequestParam(required = false) String ibsn,
                                    @RequestParam(required = false) String bookName,
                                    @RequestParam(required = false) Integer releaseYear,
                                    @RequestParam(required = false) String author,
                                    @RequestParam(required = false) String publisher,
                                    @RequestParam(required = false) String tags,
                                    @RequestParam(defaultValue = "1") Integer sortId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "35") Integer pageSize){
        log.info("ibsn:{},bookName:{},releaseYear:{},author:{},publisher:{},tags:{},sortId:{},page:{},pageSize:{}",
                ibsn,bookName,releaseYear,author,publisher,tags,sortId,page,pageSize);

        PageBean pageBean = bookService.selectByCondition(ibsn,bookName,releaseYear,author,publisher,tags,sortId,page,pageSize);

        return Result.success(pageBean);
    }

    /*
    * 根据id查询书籍信息
    * */
    @GetMapping("/book/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询的书籍id：{}",id);
        Book book=bookService.selectById(id);

        return Result.success(book);
    }

    /*
    * 批量删除书籍信息
    * */
    @Log
    @DeleteMapping("/book/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        bookService.deleteByIds(ids);

        return Result.success();
    }

    /*
    * 添加书籍信息
    * */
    @Log
    @PostMapping("/book")
    public Result insertBook(@RequestBody Book book){
        log.info("添加的书籍信息为：{}",book);
        bookService.insertBook(book);

        return Result.success();
    }

    /*
    * 修改书籍信息
    * */
    @Log
    @PutMapping("/book")
    public Result updateBook(@RequestBody Book book){
        log.info("修改的书籍信息为：{}",book);
        bookService.updateBook(book);

        return Result.success();
    }

    /*
     * 根据书籍详情推荐相关书籍
     * */
    @GetMapping("/book/similarBook/{id}")
    public Result getSimilarBooks(@PathVariable Integer id){
        log.info("需要推荐的书籍id：{}",id);
        PageBean pageBean=bookService.getSimilarBooks(id,1,5);

        return Result.success(pageBean);
    }


}











