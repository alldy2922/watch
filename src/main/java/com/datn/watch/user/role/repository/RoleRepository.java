package com.datn.watch.user.role.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.user.role.modal.RoleAuthDto;
import com.pet.market.api.user.role.modal.RoleListRq;
import com.pet.market.api.user.role.modal.entity.Role;
import com.pet.market.api.user.role.modal.entity.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.HashSet;
import java.util.List;

@Mapper
public interface RoleRepository {
    @SelectProvider(type= RoleSqlBuilder.class, method="getRoles")
    List<Long> getRoles(Long idUser);
    @SelectProvider(type= RoleSqlBuilder.class, method="getPermissions")
    HashSet<RoleAuthDto> getPermissions(Long idRole);
    @SelectProvider(type= RoleSqlBuilder.class, method="findAll")
    List<Role> findAll(RoleListRq req);
    @SelectProvider(type=RoleSqlBuilder.class, method="count")
    int count(RoleListRq req);
    @DeleteProvider(type= RoleSqlBuilder.class, method="delete")
    void delete(List<Long> ids);
    @UpdateProvider(type=RoleSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusRq rq);
    @InsertProvider(type=RoleSqlBuilder.class, method="saveRole")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void saveRole(Role role);
    @InsertProvider(type=RoleSqlBuilder.class, method="saveRolePermission")
    void saveRolePermission(RolePermission rolePermission);
    @SelectProvider(type=RoleSqlBuilder.class, method="findIdByCodePermission")
    Long findIdByCodePermission(String code);
    @UpdateProvider(type=RoleSqlBuilder.class, method="updateRole")
    void updateRole(Role role);
    @DeleteProvider(type=RoleSqlBuilder.class, method="deleteRolePermission")
    void deleteRolePermission(Long idRole);
    @SelectProvider(type= RoleSqlBuilder.class, method="getListRole")
    List<String> getListRole(Long idRole);
    @SelectProvider(type= RoleSqlBuilder.class, method="findRoleById")
    Role findRoleById(Long id);
    @SelectProvider(type= RoleSqlBuilder.class, method="findAllRole")
    List<Role> findAllRole();
    @SelectProvider(type= RoleSqlBuilder.class, method="findRoleByUser")
    List<Long> findRoleByUser(long idUser);
    @SelectProvider(type= RoleSqlBuilder.class, method="checkExistRole")
    int checkExistRole(Long id, String code, Integer type);

}
