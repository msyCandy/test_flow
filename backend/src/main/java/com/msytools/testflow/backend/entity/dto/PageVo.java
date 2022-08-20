package com.msytools.testflow.backend.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {
    private Integer pageNum;
    private Integer pageSize;
    private List<T> list;
    private Long total;

    public PageVo(int pageNum, int pageSize, List<T> list, long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }
}
