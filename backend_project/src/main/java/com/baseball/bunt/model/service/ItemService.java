package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dto.community.SearchCondition;
import com.baseball.bunt.model.dto.shop.Item;

import java.util.List;

public interface ItemService {

    // 상품 목록
    List<Item> getItemList(SearchCondition searchCondition);

    // 상품 상세 보기
    Item getItemDetail(int itemId);
}
