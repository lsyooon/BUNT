package com.baseball.bunt.model.dao;

import com.baseball.bunt.model.dto.community.SearchCondition;
import com.baseball.bunt.model.dto.shop.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao {

    // 상품 목록
    List<Item> getList(SearchCondition searchCondition);

    // 상품 상세 보기
    Item getItem(int itemId);

}
