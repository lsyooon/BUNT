package com.baseball.bunt.model.service;

import java.util.List;

import com.baseball.bunt.model.dto.shop.Item;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "상품 서비스")
public interface ItemService {

	int createItem(Item item);

	int modifyItem(Item item);

	Item readItemByItemId(int itemId);

	Item readItemByName(String name);

	List<Item> readItemList();

	int removeItem(int itemId);

	int uploadImage(int itemId);
}
