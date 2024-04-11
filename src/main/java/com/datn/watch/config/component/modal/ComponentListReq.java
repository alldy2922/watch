package com.datn.watch.config.component.modal;

import com.pet.market.api.common.utils.paging.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComponentListReq extends Page {
    private String name;
}
