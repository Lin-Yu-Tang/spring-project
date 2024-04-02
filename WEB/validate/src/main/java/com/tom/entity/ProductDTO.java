package com.tom.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO extends APIRequest {

	@NotNull(message = "商品名稱不可為空。")
    private String name;
    
	@Min(0)
    private int price;

}
