package com.datn.watch.config.page.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.page.modal.PageDto;
import com.pet.market.api.config.page.modal.PageListDto;
import com.pet.market.api.config.page.modal.PageListReq;
import com.pet.market.api.config.page.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 page")
@Tag(name = "page api v1")
@RequestMapping(Constant.API_CONFIG_PAGE)
public class PageController {
    private static final APILogger logger = new APILogger(PageController.class);
    private final PageService pageService;

    public PageController(PageService componentService) {
        this.pageService = componentService;
    }

    @GetMapping()
    @ApiOperation(value = "listPage", notes = "get all page.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('PAGE_V','PAGE_E')")
    public Object listPage(@ModelAttribute PageListReq req) {
        ResultPageData<List<PageListDto>> listComponent = pageService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_PAGE, req, listComponent);
        return listComponent;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PAGE_E')")
    public Object updateStatus(@RequestBody UpdateStatusRq rq) {
        try {
            pageService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_CONFIG_PAGE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deletePage", notes = "delete page.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PAGE_E')")
    public Object deletePage(@RequestParam List<Long> ids) {
        try {
            pageService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_CONFIG_PAGE, ids, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PostMapping("")
    @ApiOperation(value = "savePage", notes = "save page.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PAGE_E')")
    public Object savePage(@Valid @RequestBody PageDto rq) {
        try {
            pageService.save(rq);
            logger.logApi(RequestMethod.POST, Constant.API_CONFIG_PAGE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "getPageById", notes = "get page by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('PAGE_V','PAGE_E')")
    public ResultData<PageDto> getPageById(@PathVariable long id) {
        ResultData<PageDto> resultData = new ResultData<>(pageService.findById(id));
        logger.logApi(RequestMethod.GET, Constant.API_CONFIG_PAGE, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updatePage", notes = "update page.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PAGE_E')")
    public Object updatePage(@Valid @RequestBody PageDto rq) {
        try {
            pageService.update(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_CONFIG_PAGE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

}
