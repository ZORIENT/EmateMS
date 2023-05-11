package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Collection;
import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface CollectionService {

    PageBean selectByCondition(Integer userId,Integer collectionId,Short type, Integer page, Integer pageSize);

    void insertCollection(Collection collection);

    void deleteById(Integer id);
}
