package com.cat.mid.demo.cart.cartms.application.internal.queryservices;

import com.cat.mid.demo.cart.cartms.domain.queries.QueryGoods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class QueryGoodsService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${goods.service.api.url}")
    private String goodsServiceApiUrl;

    private final ObjectMapper mapper = new ObjectMapper();

    public QueryGoods queryGoodsByGoodsId(String goodsId) {
        ResponseEntity<String> response = restTemplate.getForEntity(goodsServiceApiUrl + "/" + goodsId, String.class);

        if (response != null &&
                response.getBody() != null &&
                HttpStatus.OK.value() == response.getStatusCode().value()) {
            try {
                return mapper.readValue(response.getBody(), QueryGoods.class);
            } catch (JsonProcessingException e) {
                log.error("Query goods json processing error", e);
            }
        }

        return null;
    }
}
