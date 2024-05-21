package com.baseball.bunt.model.service;

import com.baseball.bunt.model.dao.ItemDao;
import com.baseball.bunt.model.dto.community.SearchCondition;
import com.baseball.bunt.model.dto.shop.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    private ItemDao dao;

    @Override
    public List<Item> getItemList(SearchCondition searchCondition) {
        return dao.getList(searchCondition);
    }

    @Override
    public Item getItemDetail(int itemId) {
        return dao.getItem(itemId);
    }
}
