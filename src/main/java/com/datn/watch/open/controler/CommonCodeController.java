package com.datn.watch.open.controler;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.open.model.dto.CommonDTO;
import com.pet.market.api.open.service.CommonCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/public/common-code")
public class CommonCodeController
{
    private final CommonCodeService service;


    public CommonCodeController(CommonCodeService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResultData<List<CommonDTO.CodeRes>> list(@RequestParam("group") String group) {
        ResultData<List<CommonDTO.CodeRes>> response = new ResultData<>();
        response.setResultData(service.listByGroup(group));
        return response;
    }

}
