package com.cat.mid.demo.cart.shareddomain.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private String returnCode;
    private String returnMsg;
}
