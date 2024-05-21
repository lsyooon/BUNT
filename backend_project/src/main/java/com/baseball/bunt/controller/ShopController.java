package com.baseball.bunt.controller;

import com.baseball.bunt.model.dto.community.SearchCondition;
import com.baseball.bunt.model.dto.shop.Item;
import com.baseball.bunt.model.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Shop Controller", description = "쇼핑몰 관리하는 컨트롤러")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/shop")
public class ShopController {
    private final ItemService itemService;

    // 상품 목록
    @Operation(summary = "상품 목록")
    @GetMapping("/products/{teamId}")
    public ResponseEntity<?> getList(@ModelAttribute SearchCondition condition) {
        System.out.println(condition);
        List<Item> list = itemService.getItemList(condition);
        return ResponseEntity.ok(list);
    }

    // 상품 상세 보기
    @Operation(summary = "상품 상세 보기")
    @GetMapping("/products/{teamId}/{itemId}")
    public ResponseEntity<?> getDetail(@PathVariable int itemId){
        Item item = itemService.getItemDetail(itemId);
        return ResponseEntity.ok(item);
    }

}
