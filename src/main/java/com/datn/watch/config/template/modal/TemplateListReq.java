package com.datn.watch.config.template.modal;

import com.pet.market.api.common.utils.paging.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TemplateListReq extends Page {
    private String templateName;
}
