package com.datn.watch.common.utils.paging;

import lombok.Data;
@Data
public class Search extends Page {

    protected String field;

    protected String value;

    protected String startDate;

    protected String endDate;

    protected String status;

    protected String isEnabled;

    protected String orderBy;
}
