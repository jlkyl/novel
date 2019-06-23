package com.qianqiu.novel.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pages<T> {
	private List<T> rows;
	private Integer total;
}
