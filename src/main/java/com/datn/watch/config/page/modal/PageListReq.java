package com.datn.watch.config.page.modal;

import com.pet.market.api.common.utils.paging.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageListReq extends Page {
    private String name;
}
