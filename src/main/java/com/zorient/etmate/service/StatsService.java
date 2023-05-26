package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Category;
import com.zorient.etmate.pojo.Stats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatsService {
    Stats getStats();

    List<Category> getFilmStats();

    List<Category> getGameStats();

    List<Category> getBookStats();
}
