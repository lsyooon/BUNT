package com.baseball.bunt.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baseball.bunt.model.dto.shop.Item;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Item DAO")
@Repository
public interface ItemDao {

	int insertItem(Item item);

	int updateItem(Item item);

	Item selectItemByItemId(int itemId);

	Item selectItemByName(String name);

	List<Item> selectItemList();

	int deleteItem(int itemId);

	int updateImage(int itemId);
}
