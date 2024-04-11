package com.datn.watch.common.model.criteria;

import com.datn.watch.common.model.entity.Page;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class SearchCriteria extends Page {

    private String search;

    public String getSearch() {
        if (StringUtils.hasText(search)) {
            return search.trim();
        }
        return search;
    }

}
