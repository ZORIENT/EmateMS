package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    PageBean selectByCondition(String ibsn, String bookName, Integer releaseYear, String author, String publisher, String tags, Integer sortId, Integer page, Integer pageSize);

    Book selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertBook(Book book);

    void updateBook(Book book);
}
