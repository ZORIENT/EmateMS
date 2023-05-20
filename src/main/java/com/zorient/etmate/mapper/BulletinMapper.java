package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Bulletin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BulletinMapper {
    List<Bulletin> selectAllBulletin();

    Bulletin selectBulletinById(Integer id);

    void deleteBulletinByIds(List<Integer> ids);

    void insertBulletin(Bulletin bulletin);

    void updateBulletin(Bulletin bulletin);
}
