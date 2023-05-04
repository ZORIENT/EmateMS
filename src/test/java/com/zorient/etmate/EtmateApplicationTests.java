package com.zorient.etmate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class EtmateApplicationTests {

    @Test
    void test() {
        LocalDate year=LocalDate.now();


        System.out.println(year);
        System.out.println(year.getYear());



    }
}
