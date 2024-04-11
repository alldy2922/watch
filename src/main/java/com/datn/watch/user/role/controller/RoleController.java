package com.datn.watch.user.role.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.user.role.modal.RoleDto;
import com.pet.market.api.user.role.modal.RoleListRq;
import com.pet.market.api.user.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 role")
@Tag(name = "role api v1")
@RequestMapping(Constant.API_USER_ROLE)
public class RoleController {
    private static final APILogger logger = new APILogger(RoleController.class);
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    @ApiOperation(value = "getRole", notes = "get all role.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('PERMISSION_V','PERMISSION_E')")
    public Object getRole(@ModelAttribute RoleListRq req) {
        ResultPageData<List<RoleDto>> listRole = roleService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_USER_ROLE, req, listRole);
        return listRole;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PERMISSION_E')")
    public Object updateStatus(@RequestBody UpdateStatusRq rq) {
        try {
            roleService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_USER_ROLE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PostMapping("")
    @ApiOperation(value = "saveRole", notes = "save role.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PERMISSION_E')")
    public Object saveRole(@Valid @ModelAttribute RoleDto rq) {
        Result result = new Result();
        try {
            result = roleService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.POST, Constant.API_USER_ROLE, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getRoleById", notes = "get role by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PERMISSION_E')")
    public ResultData<RoleDto> getRoleById(@PathVariable long id) {
        ResultData<RoleDto> resultData = new ResultData<>(roleService.findRoleById(id));
        logger.logApi(RequestMethod.GET, Constant.API_USER_ROLE, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updateRole", notes = "update role.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PERMISSION_E')")
    public Object updateRole(@Valid @ModelAttribute RoleDto rq) {
        try {
            roleService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_USER_ROLE, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deleteRole", notes = "delete role.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('PERMISSION_E')")
    public Object deleteRole(@RequestParam List<Long> ids) {
        try {
            roleService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_USER_ROLE, ids, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

}
