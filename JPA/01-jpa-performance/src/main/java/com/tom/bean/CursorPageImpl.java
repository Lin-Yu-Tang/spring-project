package com.tom.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;

public class CursorPageImpl<T> implements CursorPage<T>, Serializable {
	
	private static final long serialVersionUID = -6675550690170314072L;

	private final List<T> content = new ArrayList<>();
	private final Pageable pageable;
	private final long total;

	public CursorPageImpl(List<T> content, Pageable pageable, long total) {
		this.content.addAll(content);
		this.pageable = pageable;
		this.total = total;
	}

	@Override
	public List<T> getContent() {
		return content;
	}

	@Override
	public Pageable getPageable() {
		return pageable;
	}

	@Override
	public long getTotal() {
		return total;
	}
	
}
