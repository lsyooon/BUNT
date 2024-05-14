package com.baseball.bunt.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baseball.bunt.model.dao.ItemDao;
import com.baseball.bunt.model.dto.shop.Item;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "상품 서비스 구현체")
@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemDao itemDao;

	@Override
	public int createItem(final Item item) {
		return itemDao.insertItem(item);
	}

	@Override
	public int modifyItem(final Item item) {
		return itemDao.updateItem(item);
	}

	@Override
	public Item readItemByItemId(final int itemId) {
		return itemDao.selectItemByItemId(itemId);
	}

	@Override
	public Item readItemByName(final String name) {
		return itemDao.selectItemByName(name);
	}

	@Override
	public List<Item> readItemList() {
		return itemDao.selectItemList();
	}

	@Override
	public int removeItem(final int itemId) {
		return itemDao.deleteItem(itemId);
	}

	@Override
	public int uploadImage(final int itemId) {
		return itemDao.updateImage(itemId);
	}
}
