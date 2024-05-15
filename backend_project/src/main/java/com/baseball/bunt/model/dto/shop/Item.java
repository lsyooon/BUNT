package com.baseball.bunt.model.dto.shop;

import org.springframework.web.multipart.MultipartFile;

import com.baseball.bunt.exception.NotEnoughStockException;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "상품")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
	@Schema(description = "상품 번호")
	private int itemId;
	@Schema(description = "구단 번호")
	private int teamId;
	@Schema(description = "상품명")
	private String name;
	@Schema(description = "상품 가격")
	private int price;
	@Schema(description = "재고 수량")
	private int stockQuantity;
	@Schema(description = "이미지 경로")
	private String imagePath;
	@Schema(description = "이전 이미지 경로")
	private String originalImagePath;
	@Schema(description = "카테고리")
	private String category;
	@Schema(description = "상품 상세 설명")
	private String itemDetail;
	@Schema(description = "등록 날짜")
	private String regDate;
	@Schema(description = "수정 날짜")
	private String modDate;

	private MultipartFile file;

	public void addStockQuantity(int stockQuantity) {
		this.stockQuantity += stockQuantity;
	}

	public void removeQuantity(int stockQuantity) {
		int restStockQuantity = this.stockQuantity - stockQuantity;
		if(restStockQuantity < 0) {
			throw new NotEnoughStockException("재고 수량이 부족합니다.");
		}
		this.stockQuantity = restStockQuantity;
	}
}
