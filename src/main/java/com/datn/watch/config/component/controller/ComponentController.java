package com.datn.watch.config.component.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.component.modal.ComponentDto;
import com.pet.market.api.config.component.modal.ComponentListDto;
import com.pet.market.api.config.component.modal.ComponentListReq;
import com.pet.market.api.config.component.service.ComponentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 component")
@Tag(name = "component api v1")
@RequestMapping(Constant.API_CONFIG_COMPONENT)
public class ComponentController {
    private static final APILogger logger = new APILogger(ComponentController.class);
    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping()
    @ApiOperation(value = "getComponent", notes = "get all component.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('COMPONENT_V','COMPONENT_E')")
    public Object getComponent(@ModelAttribute ComponentListReq req) {
        ResultPageData<List<ComponentListDto>> listComponent = componentService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_COMPONENT, req, listComponent);
        return listComponent;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('COMPONENT_E')")
    public Object updateStatus(@RequestBody UpdateStatusRq rq) {
        try {
            componentService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_CONFIG_COMPONENT, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deleteComponent", notes = "delete component.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('COMPONENT_E')")
    public Object deleteComponent(@RequestParam List<Long> ids) {
        try {
            Result result = componentService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_CONFIG_COMPONENT, ids, result);
            return result;
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
    }

    @PostMapping("")
    @ApiOperation(value = "saveComponent", notes = "save component.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('COMPONENT_E')")
    public Object saveComponent(@Valid @ModelAttribute ComponentDto rq) {
        try {
            componentService.save(rq);
            logger.logApi(RequestMethod.POST, Constant.API_CONFIG_COMPONENT, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getComponentById", notes = "get component by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('COMPONENT_V','COMPONENT_E')")
    public ResultData<ComponentDto> getComponentById(@PathVariable long id) {
        ResultData<ComponentDto> resultData = new ResultData<>(componentService.findById(id));
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_COMPONENT, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updateComponent", notes = "update component.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('COMPONENT_E')")
    public Object updateComponent(@Valid @ModelAttribute ComponentDto rq) {
        try {
            componentService.update(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_CONFIG_COMPONENT, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @GetMapping("/all")
    @ApiOperation(value = "getAllByStatus", notes = "get all component.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PAGE_E')")
    public Object getAllByStatus() {
        ResultData<List<ComponentListDto>> listComponent = componentService.findByStatus();
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_COMPONENT, "", listComponent);
        return listComponent;
    }

}
