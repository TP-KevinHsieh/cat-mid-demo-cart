package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.cat.mid.demo.cart.cartms.application.internal.commandservices.UpdateGoodsCommandService;
import com.cat.mid.demo.cart.cartms.application.internal.queryservices.QueryGoodsService;
import com.cat.mid.demo.cart.cartms.domain.queries.QueryGoods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GoodsServiceTest {
    @Autowired
    private QueryGoodsService queryGoodsService;

    @Autowired
    private UpdateGoodsCommandService updateGoodsCommandService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void queryGoodsByGoodsId() throws JsonProcessingException {
        QueryGoods queryGoods = queryGoodsService.queryGoodsByGoodsId("8762289");
        System.out.println(mapper.writeValueAsString(queryGoods));
    }

    @Test
    void updateGoodsInventory() throws JsonProcessingException {
        System.out.println(updateGoodsCommandService.updateGoodsInventory("8762289", 1));
    }
}
