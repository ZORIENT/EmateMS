package com.zorient.etmate;

import com.zorient.etmate.pojo.Stats;
import com.zorient.etmate.service.StatsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class StatsTest {
    @Autowired
    private StatsService statsService;

    @Test
    public void testStats(){
        Stats stats=statsService.getStats();

        log.info(stats.toString());
    }
}
