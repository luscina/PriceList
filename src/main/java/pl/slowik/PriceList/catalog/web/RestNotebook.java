package pl.slowik.PriceList.catalog.web;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class RestNotebook {
    Long id;
    String pn;
    String eanCode;
    String productFamily;
    String productSeries;
    String status;
    BigDecimal bpPrice;
    BigDecimal bpPricePln;
    BigDecimal srpPrice;
    String base;
    String color;
    String panel;
    String cup;
    String memory;
    String ssd;
    String hdd;
    String graphics;
    String odd;
    String wlan;
    String wwan;
    String backlit;
    String frp;
    String camera;
    String keyboard;
    String cardReader;
    String os;
    String warranty;
    String battery;
    String adapter;
}
