package com.zorient.etmate.service;

import com.zorient.etmate.pojo.SearchResult;
import org.springframework.stereotype.Service;

@Service
public interface SearchService {
    SearchResult search(String keyword,Integer page,Integer pageSize);
}
