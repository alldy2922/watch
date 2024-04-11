package com.datn.watch.user.account.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.anotation.ApiResponsesOk;
import com.pet.market.api.common.exception.PetMarketApiException;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.user.account.modal.PasswordDto;
import com.pet.market.api.user.account.modal.UserDto;
import com.pet.market.api.user.account.modal.UserLisReq;
import com.pet.market.api.user.account.service.UserService;
import com.pet.market.api.user.role.modal.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("api v1 account")
@Tag(name = "account api v1")
@RequestMapping(Constant.API_USER_ACCOUNT)
public class AccountController {
    private static final APILogger logger = new APILogger(AccountController.class);
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @ApiOperation(value = "getAccount", notes = "get all account.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('EMPLOYEE_V','EMPLOYEE_E')")
    public Object getAccount(@ModelAttribute UserLisReq req) {
        ResultPageData<List<UserDto>> listUser = userService.findAll(req);
        logger.logApi(RequestMethod.GET, Constant.API_USER_ACCOUNT, req, listUser);
        return listUser;
    }

    @PatchMapping("")
    @ApiOperation(value = "updateStatus", notes = "update status.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('EMPLOYEE_E')")
    public Object updateStatus(@RequestBody UpdateStatusRq rq) {
        try {
            userService.updateStatus(rq);
            logger.logApi(RequestMethod.PATCH, Constant.API_USER_ACCOUNT, rq, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @DeleteMapping("")
    @ApiOperation(value = "deleteAccount", notes = "delete account.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('EMPLOYEE_E')")
    public Object deleteAccount(@RequestParam List<Long> ids) {
        try {
            userService.delete(ids);
            logger.logApi(RequestMethod.DELETE, Constant.API_USER_ACCOUNT, ids, "");
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }

    @PostMapping("")
    @ApiOperation(value = "saveAccount", notes = "save user.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('EMPLOYEE_E')")
    public Object saveAccount(@Valid @ModelAttribute UserDto rq) {
        Result result = new Result();
        try {
            result = userService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.POST, Constant.API_USER_ACCOUNT, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getUserById", notes = "get user by id.")
    @ApiResponsesOk()
    @PreAuthorize("hasAnyRole('EMPLOYEE_V','EMPLOYEE_E')")
    public ResultData<UserDto> getUserById(@PathVariable long id) {
        ResultData<UserDto> resultData = new ResultData<>(userService.findById(id));
        logger.logApi(RequestMethod.GET, Constant.API_USER_ACCOUNT, id, resultData);
        return resultData;
    }

    @PutMapping("")
    @ApiOperation(value = "updateAccount", notes = "update account.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('EMPLOYEE_E')")
    public Object updateAccount(@Valid @ModelAttribute UserDto rq) {
        Result result = new Result();
        try {
            result = userService.saveOrUpdate(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_USER_ACCOUNT, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }

    @GetMapping("/all-role")
    @ApiOperation(value = "getAllRole", notes = "get all role.")
    @ApiResponsesOk()
    @PreAuthorize("hasRole('EMPLOYEE_E')")
    public Object getAllRole() {
        ResultData<List<RoleDto>> listRole = new ResultData<>();
        listRole.setResultData(userService.findAllRole());
        logger.logApi(RequestMethod.GET, Constant.API_USER_ACCOUNT, "", listRole);
        return listRole;
    }

    @PutMapping("/password")
    @ApiOperation(value = "updatePassword", notes = "update password.")
    @ApiResponsesOk()
    public Object updatePassword(@Valid @ModelAttribute PasswordDto rq) {
        Result result = new Result();
        try {
            result = userService.updatePassword(rq);
            logger.logApi(RequestMethod.PUT, Constant.API_USER_ACCOUNT, rq, result);
        } catch (PetMarketApiException ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return result;
    }
}
