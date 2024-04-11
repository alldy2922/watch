package com.datn.watch.config.template.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.template.modal.TemplateDto;
import com.pet.market.api.config.template.modal.TemplateListReq;
import com.pet.market.api.config.template.modal.TemplateOptionDto;
import com.pet.market.api.config.template.service.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 template")
@Tag(name = "template api v1")
@RequestMapping(Constant.API_CONFIG_TEMPLATE)
public class TemplateController {
    private static final APILogger logger = new APILogger(TemplateController.class);
    private final TemplateService templateService;

    public TemplateController(TemplateService service) {
        this.templateService = service;
    }

    @GetMapping()
    @ApiOperation(value = "getTemplate", notes = "get all template.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('TEMPLATE_V','TEMPLATE_E')")
    public Object getTemplate(@ModelAttribute TemplateListReq req) {
        ResultPageData<List<TemplateDto>> listTemplate = templateService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_TEMPLATE, req, listTemplate);
        return listTemplate;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('TEMPLATE_E')")
    public Object updateStatus(@RequestBody UpdateStatusRq rq) {
        try {
            templateService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_CONFIG_TEMPLATE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PostMapping("")
    @ApiOperation(value = "saveTemplate", notes = "save template.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('TEMPLATE_E')")
    public Object saveTemplate(@Valid @ModelAttribute TemplateDto rq) {
        try {
            templateService.save(rq);
            logger.logApi(RequestMethod.POST, Constant.API_CONFIG_TEMPLATE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getTemplateById", notes = "get template by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('TEMPLATE_V','TEMPLATE_E')")
    public ResultData<TemplateDto> getTemplateById(@PathVariable long id) {
        ResultData<TemplateDto> resultData = new ResultData<>(templateService.getTemplateById(id));
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_TEMPLATE, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updateTemplate", notes = "update template.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('TEMPLATE_E')")
    public Object updateTemplate(@Valid @ModelAttribute TemplateDto rq) {
        try {
            templateService.update(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_CONFIG_TEMPLATE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deleteTemplate", notes = "delete template.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('TEMPLATE_E')")
    public Object deleteTemplate(@RequestParam List<Long> ids) {
        try {
            templateService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_CONFIG_TEMPLATE, ids, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @GetMapping("/all")
    @ApiOperation(value = "getAllTemplate", notes = "get all template.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('COMPONENT_E')")
    public ResultData<List<TemplateOptionDto>> getAllTemplate() {
        ResultData<List<TemplateOptionDto>> resultData = new ResultData<>();
        List<TemplateOptionDto> listTemplate = templateService.selectAll();
        resultData.setResultData(listTemplate);
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_TEMPLATE, "", listTemplate);
        return resultData;
    }
}
