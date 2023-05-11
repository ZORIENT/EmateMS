package com.zorient.etmate.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import com.zorient.etmate.pojo.Collection;

import java.util.List;

@Mapper
public interface CollectionMapper {
    List<Collection> selectFilmCollection(Integer userId, Integer collectionId, Short type);

    List<Collection> selectGameCollection(Integer userId,Integer collectionId, Short type);

    List<Collection> selectBookCollection(Integer userId,Integer collectionId, Short type);

    void insertCollection(Collection collection);

    @Delete("delete from tb_collection where id = #{id}")
    void deleteById(Integer id);
}
