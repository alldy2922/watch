package com.datn.watch.user.role.service;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.user.role.modal.RoleAuthDto;
import com.pet.market.api.user.role.modal.RoleDto;
import com.pet.market.api.user.role.modal.RoleListRq;
import com.pet.market.api.user.role.modal.entity.Role;
import com.pet.market.api.user.role.modal.entity.RolePermission;
import com.pet.market.api.user.role.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepo;

    public RoleService(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    public HashSet<RoleAuthDto> getRoles(Long idUser){
        List<Long> idRoles = roleRepo.getRoles(idUser);
        HashSet<RoleAuthDto> listPermission = new HashSet<>();
        for(Long idRole : idRoles){
            listPermission.addAll(roleRepo.getPermissions(idRole));
        }
        return listPermission;
    }

    public ResultPageData<List<RoleDto>> findAll(RoleListRq req){
        int totalRecords = roleRepo.count(req);
        List<RoleDto> listTemplate = ModelMapperUtils.mapAll(roleRepo.findAll(req), RoleDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listTemplate);
    }

    public void updateStatus(UpdateStatusRq rq){
        roleRepo.updateStatus(rq);
    }

    @Transactional
    public void delete(List<Long> ids){
        roleRepo.delete(ids);
        for(long id : ids){
            roleRepo.deleteRolePermission(id);
        }
    }

    @Transactional
    public Result saveOrUpdate(RoleDto rq){
        Role role = ModelMapperUtils.map(rq, Role.class);
        if(rq.getId() == null){
            if(roleRepo.checkExistRole(role.getId(),role.getCode(),0) > 0){
                return new Result("fail", "Mã quyền đã tồn tại");
            }
            roleRepo.saveRole(role);
        }else {
            if(roleRepo.checkExistRole(role.getId(),role.getCode(),1) > 0){
                return new Result("fail", "Mã quyền đã tồn tại");
            }
            roleRepo.updateRole(role);
            roleRepo.deleteRolePermission(role.getId());
        }
        for(String item : rq.getRoles()){
            String[] arr = item.split("-");
            String permission = arr[0];
            String action = arr[1];
            RolePermission rolePermission = new RolePermission();
            rolePermission.setIdPermission(roleRepo.findIdByCodePermission(permission));
            rolePermission.setIdRole(role.getId());
            rolePermission.setAction(action);
            roleRepo.saveRolePermission(rolePermission);
        }
        return new Result();
    }

    public RoleDto findRoleById(Long id){
        RoleDto roleDto = ModelMapperUtils.map(roleRepo.findRoleById(id), RoleDto.class);
        List<String> roles = roleRepo.getListRole(id);
        roleDto.setRoles(roles);
        return roleDto;
    }

}
