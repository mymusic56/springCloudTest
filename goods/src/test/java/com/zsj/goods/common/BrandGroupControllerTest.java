package com.zsj.goods.common;

import com.zsj.goods.controller.BrandGroupController;
import com.zsj.goods.mapper.BrandGroupMapper;
import com.zsj.goods.mapper.BrandMapper;
import com.zsj.goods.service.impl.BrandGroupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangshangji
 * @since 2021/2/1 11:39
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BrandGroupController.class)
public class BrandGroupControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BrandGroupServiceImpl brandGroupService;

    @MockBean
    BrandGroupMapper brandGroupMapper;

    @MockBean
    BrandMapper branMapper;

    @MockBean
    RedisConnectionFactory factory;

    @Test
    public void getListTest() throws Exception {
        mvc.perform(get("/goods/brand-group/list").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
    };
}
