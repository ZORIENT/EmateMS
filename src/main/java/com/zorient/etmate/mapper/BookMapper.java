package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> selectByCondition(String ibsn, String bookName, Integer releaseYear, String author, String publisher, String tags, String sort);

    @Select("select id, ibsn, book_name, author, translator, publisher, " +
            "publication_time, price, douban_score, pages, introduction, " +
            "author_intro, tags, cover from tb_book where id=#{id}")
    Book selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertBook(Book book);

    void updateBook(Book book);

    List<Book> getSimilarBooks(Integer id, String tag, String author, String publisher);

    @Select("select * from tb_book")
    List<Book> getAllBooks();

    @Select("select * from tb_book ORDER BY  publication_time DESC,douban_score DESC LIMIT 0,#{size}")
    List<Book> getHotBooks(Integer size);
}







