package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.BookMapper;
import com.zorient.etmate.mapper.CollectionMapper;
import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    /*
    * 条件分页查询书籍信息
    * */
    @Override
    public PageBean selectByCondition(String ibsn, String bookName, Integer releaseYear, String author,
                                      String publisher, String tags, Integer sortId, Integer page, Integer pageSize) {

        String  sort = switch (sortId) {
            case 1 -> "douban_score";
            case 2 -> "publication_time";
            case 3 -> "price";
            default -> throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        };

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

        if(book!=null){
            // 结果不为空
            return book;
        }else{
            throw new AppException(AppExceptionCodeMsg.RESOURCE_NOT_AVAILABLE);
        }


    }

    /*
    * 批量删除书籍信息
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除相关评论和收藏
        for(Integer id:ids){
            commentMapper.deleteCommentsByItemId(id,(short)3);
            collectionMapper.deleteCollectionsByCollectionId(id,(short)3);
        }

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
