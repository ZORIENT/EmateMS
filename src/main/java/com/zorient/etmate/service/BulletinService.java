package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Bulletin;
import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BulletinService {

    PageBean selectAllBulletin(Integer page, Integer pageSize);

    Bulletin selectBulletinById(Integer id);

    void deleteBulletinByIds(List<Integer> ids);

    void insertBulletin(Bulletin bulletin);

    void updateBulletin(Bulletin bulletin);
}
