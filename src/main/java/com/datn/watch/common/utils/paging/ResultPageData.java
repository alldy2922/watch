package com.datn.watch.common.utils.paging;

import com.datn.watch.common.model.json.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultPageData<T> extends Result {
    private int pageNo;
    private int pageSize;
    private int total;

    @JsonProperty("data")
    private T resultData;

    public ResultPageData(Page page, int total) {
        super();
        this.pageSize = page.getPageSize();
        this.pageNo = page.getPageNo();
        this.total = total;
    }
}
