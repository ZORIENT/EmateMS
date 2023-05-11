package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.BookMapper;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /*
    * 条件分页查询书籍信息
    * */
    @Override
    public PageBean selectByCondition(String ibsn, String bookName, Integer releaseYear, String author,
                                      String publisher, String tags, Integer sortId, Integer page, Integer pageSize) {

        String  sort=new String("douban_score");
        switch(sortId){
            case 1:
                sort="douban_score";
                break;
            case 2:
                sort="publication_time";
                break;
            default:
                sort="price";
                break;
        }

        log.info("sort的值：{}",sort);

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Book> bookList=bookMapper.selectByCondition(ibsn,bookName,releaseYear,author,publisher ,tags,sort);
        Page<Book> p= (Page<Book>) bookList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询书籍：{}",pageBean);

        return pageBean;
    }

    /*
    * 根据id查询书籍信息
    * */
    @Override
    public Book selectById(Integer id) {
        Book book=bookMapper.selectById(id);

        return book;
    }

    /*
    * 批量删除书籍信息
    * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        bookMapper.deleteByIds(ids);
    }

    /*
    * 添加书籍信息
    * */
    @Override
    public void insertBook(Book book) {
        bookMapper.insertBook(book);
    }

    /*
    * 修改书籍信息
    * */
    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    /*
    * 根据书籍id查询相关书籍
    * */
    @Override
    public PageBean getSimilarBooks(Integer id, int page, int pageSize) {
        Book book=bookMapper.selectById(id);

        String[] tag=book.getTags().split("/");

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Book> bookList=bookMapper.getSimilarBooks(id,tag[0],book.getAuthor(),book.getPublisher());
        Page<Book> p= (Page<Book>) bookList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询书籍：{}",pageBean);

        return pageBean;
    }
}
