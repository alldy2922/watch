package com.datn.watch.user.account.modal;

import com.pet.market.api.common.utils.paging.Page;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLisReq extends Page {
    private String name;
}
