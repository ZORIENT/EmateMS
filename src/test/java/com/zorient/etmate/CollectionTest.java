package com.zorient.etmate;

import com.zorient.etmate.pojo.Collection;
import com.zorient.etmate.mapper.CollectionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
public class CollectionTest {
    @Autowired
    private CollectionMapper collectionMapper;

    @Test
    public void testSelectFilmCollection(){
        List<Collection> collectionList= collectionMapper.selectFilmCollection(1,(short)1);
        log.info(collectionList.toString());
    }

    @Test
    public void testSelectGameCollection(){
        List<Collection> collectionList= collectionMapper.selectGameCollection(1,(short)2);
        log.info(collectionList.toString());
    }

    @Test
    public void testSelectBookCollection(){
        List<Collection> collectionList= collectionMapper.selectBookCollection(1,(short)3);
        log.info(collectionList.toString());
    }

    @Test
    public void testInsertCollection(){
        Collection collection=new Collection(null,1,17,
                null,null,null,(short)1, LocalDateTime.now(),LocalDateTime.now());

        collectionMapper.insertCollection(collection);
    }

    @Test
    public void testDeleteById(){
        collectionMapper.deleteById(14);
    }

}
