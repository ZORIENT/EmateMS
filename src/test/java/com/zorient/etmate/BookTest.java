package com.zorient.etmate;

import com.zorient.etmate.mapper.BookMapper;
import com.zorient.etmate.pojo.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class BookTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void testSelectByCondition(){
        List<Book> bookList= bookMapper.selectByCondition(null,null,2016,
                null,null,null,"douban_score");

        log.info(bookList.toString());
    }

    @Test
    public void testSelectById(){
        Book book=bookMapper.selectById(2);

        log.info(book.toString());
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> ids=new ArrayList<>();
        ids.add(2);
        ids.add(3);

        bookMapper.deleteByIds(ids);
    }

    @Test
    public void testInsertBook(){
        Book book=new Book(null,"ibsn131313131","测试：走向建筑","梁xx","暴走d姜撞奶","成都", LocalDate.now(),
                (float)188,(float)8.2,2,"炸鸡真tmd难吃","吃了炸鸡生无可恋的人","炸鸡/建筑/难吃","testCover");

        bookMapper.insertBook(book);
    }

    @Test
    public void testUpdateBook(){
        Book book=new Book(2403,"ibsn131313131","测试：走向建筑","梁xx","暴走d姜撞奶","成都", LocalDate.now(),
                (float)188,(float)8.2,2,"炸鸡真tmd难吃","吃了炸鸡生无可恋的人","炸鸡/建筑/难吃","testCover");

        bookMapper.updateBook(book);
    }


}
