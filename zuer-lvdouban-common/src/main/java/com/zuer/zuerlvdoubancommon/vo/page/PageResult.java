package com.zuer.zuerlvdoubancommon.vo.page;

import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> {
    private static final long serialVersionUID = 1L;
    private int total;

    private List<T> data;
    public PageResult(){

    }

    public PageResult(int total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
