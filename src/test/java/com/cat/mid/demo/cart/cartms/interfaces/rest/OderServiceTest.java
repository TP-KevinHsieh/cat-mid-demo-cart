package com.cat.mid.demo.cart.cartms.interfaces.rest;

import com.cat.mid.demo.cart.cartms.application.internal.commandservices.AddOrderCommandService;
import com.cat.mid.demo.cart.cartms.domain.commands.AddOrderToServerCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class OderServiceTest {
    @Autowired
    private AddOrderCommandService addOrderCommandService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void addOrderToServer() throws JsonProcessingException {
        List<AddOrderToServerCommand.Goods> goodsList = new ArrayList<>();

        AddOrderToServerCommand.Goods goods = new AddOrderToServerCommand.Goods(
                4,
                "8762289",
                "【股癌新作】灰階思考",
                316
        );

        goodsList.add(goods);

        AddOrderToServerCommand addOrderToServerCommand = new AddOrderToServerCommand(
                UUID.randomUUID().toString(),
                "測試",
                goodsList,
                316
        );

        AddOrderToServerCommand result = addOrderCommandService.addOrderToServer(addOrderToServerCommand);

        System.out.println(mapper.writeValueAsString(result));
    }
}
