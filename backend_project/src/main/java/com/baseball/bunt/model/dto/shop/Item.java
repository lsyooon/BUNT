package com.baseball.bunt.model.dto.shop;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Tag(name = "쇼핑몰 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    private int itemId;
    private int teamId;
    private String name;
    private int price;
    private int stockQuantity;
    private String imgPath;
    private String category;
    private String itemDescription;
    private Date regDate;
    private Date modDate;

}
