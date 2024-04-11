package com.datn.watch.common.utils.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page{
    private int pageNo = 1;
    private int pageSize = 10;
    private int total = 0;

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }
}
