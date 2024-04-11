package com.datn.watch.open.controler;


import com.pet.market.api.common.model.criteria.SearchCriteria;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.open.model.criteria.AreaCriteria;
import com.pet.market.api.open.model.dto.AreaDTO;
import com.pet.market.api.open.service.AreaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/public/area")
public class AreaController {

    private final AreaService service;

    public AreaController(AreaService service) {
        this.service = service;
    }

    @GetMapping("/getAllProvince")
    public ResultData<List<AreaDTO.AreaRes>> getAllProvince(SearchCriteria criteria) {
        ResultData<List<AreaDTO.AreaRes>> response = new ResultData();
        response.setResultData(service.getAllProvince(criteria));
        return response;
    }

    @GetMapping("/getAllDistrict")
    public ResultData<List<AreaDTO.AreaRes>> getAllDistrict(AreaCriteria criteria) {
        ResultData<List<AreaDTO.AreaRes>> response = new ResultData();
        response.setResultData(service.getAllDistrict(criteria));
        return response;
    }

    @GetMapping("/getAllCommune")
    public ResultData<List<AreaDTO.AreaRes>> getAllCommune(AreaCriteria criteria) {
        ResultData<List<AreaDTO.AreaRes>> response = new ResultData();
        response.setResultData(service.getAllCommune(criteria));
        return response;
    }

    @GetMapping("/province/crawl")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result crawl() {
        Result response = new Result();
        service.deleteAllProvinces();
        service.batchProvinces();
        return response;
    }

    @GetMapping("/district/crawl")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result crawlDistrict() {
        Result response = new Result();
        service.deleteAllDistrict();
        service.batchDistrict();
        return response;
    }

    @GetMapping("/commune/crawl")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result crawlCommune() {
        Result response = new Result();
        service.deleteAllCommune();
        service.batchCommune();
        return response;
    }

}
