package com.mcb.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by aacs on 2017. 12. 12..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClubServiceImplTest {

    @Autowired
    private ClubService service;

    @Test
    public void findAll() throws Exception {
        Assert.assertEquals(0, service.findAll().size());
    }

    @Test
    public void save() throws Exception {

    }

}