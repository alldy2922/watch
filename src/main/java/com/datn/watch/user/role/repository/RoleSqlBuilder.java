package com.datn.watch.user.role.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.user.role.modal.RoleListRq;
import com.pet.market.api.user.role.modal.entity.Role;
import com.pet.market.api.user.role.modal.entity.RolePermission;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.*;

public class RoleSqlBuilder {
    public String getRoles(Long userId){
        return new SQL() {{
            SELECT("r.id_role idRole");
            FROM(TB_USER + " u");
            INNER_JOIN(TB_USER_ROLE + " r on r.id_user = u.id");
            WHERE("u.id = #{userId}");
        }}.toString();
    }

    public String getPermissions(Long roleId){
        return new SQL() {{
            SELECT("concat('ROLE_',tp.code, '_', trp.action) as role");
            FROM(TB_ROLE + " tr");
            INNER_JOIN(TB_ROLE_PERMISSION + " trp on tr.id = trp.id_role");
            INNER_JOIN(TB_PERMISSION + " tp on trp.id_permission = tp.id");
            WHERE("tr.id = #{roleId}");
        }}.toString();
    }

    public String findAll(RoleListRq req){
        return new SQL() {{
            SELECT("id, code, description, status");
            FROM("TB_ROLE");
            WHERE("status = 1 or status = 0");
            if(StringUtils.isNotBlank(req.getCode())){
                WHERE("code like concat('%', #{code}, '%')");
            }
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(RoleListRq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_ROLE);
            if(StringUtils.isNotBlank(req.getCode())){
                WHERE("code like concat('%', #{code}, '%')");
            }
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_ROLE);
            WHERE("id in ( "+param+" )");
        }}.toString();
    }

    public String updateStatus(UpdateStatusRq rq){
        return new SQL() {{
            UPDATE(TB_ROLE);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String saveRole(Role role){
        return new SQL() {{
            INSERT_INTO(TB_ROLE);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("code, description, status","#{code},#{description},#{status}");
        }}.toString();
    }

    public String saveRolePermission(RolePermission rq){
        return new SQL() {{
            INSERT_INTO(TB_ROLE_PERMISSION);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("id_permission, id_role, action","#{idPermission},#{idRole},#{action}");
        }}.toString();
    }

    public String findIdByCodePermission(String code){
        return new SQL() {{
            SELECT("id");
            FROM(TB_PERMISSION);
            WHERE("code = #{code}");
        }}.toString();
    }

    public String updateRole(Role role){
        return new SQL() {{
            UPDATE("tb_role");
            SET("code = #{code}");
            SET("description = #{description}");
            SET("status = #{status}");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = #{lastModifiedDate}");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String deleteRolePermission(Long idRole){
        return new SQL() {{
            DELETE_FROM(TB_ROLE_PERMISSION);
            WHERE("id_role = #{idRole}");
        }}.toString();
    }

    public String getListRole(Long idRole){
        return new SQL() {{
            SELECT("concat(tp.code, '-', trp.action)");
            FROM(TB_ROLE + " tr");
            INNER_JOIN(TB_ROLE_PERMISSION + " trp on tr.id = trp.id_role");
            INNER_JOIN(TB_PERMISSION + " tp on trp.id_permission = tp.id");
            WHERE("tr.id = #{roleId}");
        }}.toString();
    }

    public String findRoleById(Long id){
        return new SQL() {{
            SELECT("id, code, description, status");
            FROM(TB_ROLE);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String findAllRole(){
        return new SQL() {{
            SELECT("id, code");
            FROM(TB_ROLE);
            WHERE("status = 1");
        }}.toString();
    }

    public String findRoleByUser(long idUser){
        return new SQL() {{
            SELECT("id");
            FROM(TB_ROLE);
            WHERE("status = 1");
        }}.toString();
    }

    public String checkExistRole(Long id, String code, Integer type){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_ROLE);
            if(type == 0){
                WHERE("code = #{code}");
            }else{
                WHERE("id <> #{id}");
                WHERE("code = #{code}");
            }
        }}.toString();
    }

}
