package pl.slowik.PriceList.catalog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Notebook {
    @Id
    @GeneratedValue
    private Long id;
    private String pn;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    private String eanCode;
    private String productFamily;
    private String productSeries;
    private String status;
    private BigDecimal bpPrice;
    private BigDecimal bpPricePln;
    private BigDecimal srpPrice;
    private String base;
    private String color;
    private String panel;
    private String cup;
    private String memory;
    private String ssd;
    private String hdd;
    private String graphics;
    private String odd;
    private String wlan;
    private String wwan;
    private String backlit;
    private String frp;
    private String camera;
    private String keyboard;
    private String cardReader;
    private String os;
    private String warranty;
    private String battery;
    private String adapter;
}
