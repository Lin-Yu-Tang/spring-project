package com.tom.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO extends APIRequest {

	@NotNull
    private String name;
    
	@NotNull
	@Min(0)
    private int price;

}
