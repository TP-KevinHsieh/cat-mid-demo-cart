package com.cat.mid.demo.cart.cartms.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    C5000("C5000", "查無客戶資料"),

    C6000("C6000", "商品數量不足"),
    C6001("C6001", "查無商品資料"),
    C6002("C6002", "更新商品庫存失敗"),

    C7000("C7000", "查無購物車資料"),
    C7001("C7001", "購物車已存在"),

    C8000("C8000", "訂單建立失敗"),

    C9999("C9999", "Api execute error")
    ;

    private final String code;
    private final String msg;
}
