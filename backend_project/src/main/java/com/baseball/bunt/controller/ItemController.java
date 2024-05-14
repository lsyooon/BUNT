package com.baseball.bunt.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baseball.bunt.model.dto.shop.Item;
import com.baseball.bunt.model.service.ItemService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@ApiModel(value = "Item Controller")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
	RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ItemController {

	private final ItemService itemService;

	private final ResourceLoader resourceLoader;

	@PostMapping("/admin/item/new")
	public ResponseEntity<?> createItem(@RequestBody Item item) {
		try {
			int result = itemService.createItem(item);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/admin/item/modify")
	public ResponseEntity<?> modifyItem(@RequestBody Item item) {
		try {
			int result = itemService.modifyItem(item);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/admin/items")
	public ResponseEntity<?> readItemList() {
		try {
			List<Item> itemList = itemService.readItemList();
			if (itemList != null) {
				return new ResponseEntity<>(itemList, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/item/{itemId}")
	public ResponseEntity<?> readItemByItemId(@PathVariable("itemId") int itemId) {
		try {
			Item item = itemService.readItemByItemId(itemId);
			if (item != null) {
				return new ResponseEntity<>(item, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/removeItem/{itemId}")
	public ResponseEntity<?> removeItem(@PathVariable("itemId") int itemId) {
		try {
			int result = itemService.removeItem(itemId);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "아이템 이미지 업로드")
	@PutMapping("/upload-image/{itemId}")
	public ResponseEntity<?> uploadImage(@PathVariable int itemId,
		@RequestParam("img") MultipartFile file) {

		try {
			Item item = null;

			if (file != null && file.getSize() > 0) {
				Resource res = resourceLoader.getResource("classpath:static/resources/upload");

				if (!res.getFile().exists()) {
					res.getFile().mkdir();
				}

				List<Item> list = itemService.readItemList();

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getItemId() == itemId) {
						item = list.get(i);
					}
				}
				if (item != null) {
					item.setImagePath(
						System.currentTimeMillis() + "_" + file.getOriginalFilename());
					item.setOriginalImagePath(file.getOriginalFilename());

					file.transferTo(
						new File(res.getFile().getCanonicalPath() + "/" + item.getImagePath()));
				}
			}

			int result = itemService.modifyItem(item);
			if (result != 0) {
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/display")
	public ResponseEntity<Resource> display(@RequestParam("itemId") String itemId) throws
		IOException {
		Resource res = resourceLoader.getResource("classpath:static/resources/upload");
		Item item = new Item();
		List<Item> list = itemService.readItemList();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getItemId() == Integer.parseInt(itemId)) {
				item = list.get(i);
			}
		}

		String path = res.getFile().getCanonicalPath() + "/" + item.getImagePath();
		Resource resource = new FileSystemResource(path);
		System.out.println(item.getImagePath());

		if (!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		HttpHeaders header = new HttpHeaders();
		Path filePath = null;

		try {
			filePath = Paths.get(path);
			header.add("Content-type", Files.probeContentType(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(resource, header, HttpStatus.OK);
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {

		e.printStackTrace();

		return new ResponseEntity<>("Sorry: " + e.getMessage(),
			HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
