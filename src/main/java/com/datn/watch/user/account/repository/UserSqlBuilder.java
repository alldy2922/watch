package com.datn.watch.user.account.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.user.account.modal.UserLisReq;
import com.pet.market.api.user.account.modal.entity.User;
import com.pet.market.api.user.account.modal.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.pet.market.api.common.constant.TableConstant.TB_USER;
import static com.pet.market.api.common.constant.TableConstant.TB_USER_ROLE;

public class UserSqlBuilder {
    public String findByUsername(String username){
        return new SQL() {{
            SELECT("*");
            FROM(TB_USER);
            WHERE("username = #{username}");
            WHERE("status <> 0");
        }}.toString();
    }
    public String findAll(UserLisReq req){
        return new SQL() {{
            SELECT("id, name, phone_number phoneNumber, username, status");
            FROM(TB_USER);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
            WHERE("status = 1 or status = 0");
            ORDER_BY("created_date");
            LIMIT(req.getPageSize());
            OFFSET(req.getOffset());
        }}.toString();
    }

    public String count(UserLisReq req){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_USER);
            if(StringUtils.isNotBlank(req.getName())){
                WHERE("name like concat('%', #{name}, '%')");
            }
            WHERE("status = 1 or status = 0");
        }}.toString();
    }

    public String delete(List<Long> ids){
        return new SQL() {{
            String param = StringUtils.join(ids, ",");
            DELETE_FROM(TB_USER);
            WHERE("id in ( "+param+" )");
            WHERE("status = 1 or status = 0");
        }}.toString();
    }

    public String updateStatus(UpdateStatusRq rq){
        return new SQL() {{
            UPDATE(TB_USER);
            SET("status = #{status}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String save(User user){
        return new SQL() {{
            INSERT_INTO(TB_USER);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("name, phone_number, status","#{name},#{phoneNumber},#{status}");
            VALUES("username, password","#{username},#{password}");
        }}.toString();
    }

    public String findById(long id){
        return new SQL() {{
            SELECT("id, name, name, phone_number phoneNumber, username, status");
            FROM(TB_USER);
            WHERE("id = #{id}");
            WHERE("status = 1 or status = 0");
        }}.toString();
    }

    public String update(User user){
        return new SQL() {{
            UPDATE(TB_USER);
            SET("name = #{name}");
            SET("phone_number = #{phoneNumber}");
            SET("username = #{username}");
            if(StringUtils.isNotBlank(user.getPassword())) {
                SET("password = #{password}");
            }
            SET("status = #{status}");
            SET("updated_by = #{lastModifiedBy}");
            SET("updated_date = #{lastModifiedDate}");
            WHERE("id = #{id}");
        }}.toString();
    }

    public String saveUserRole(UserRole userRole){
        return new SQL() {{
            INSERT_INTO(TB_USER_ROLE);
            VALUES("created_by, created_date", "#{createdBy},#{createdDate}");
            VALUES("id_role, id_user","#{idRole},#{idUser}");
        }}.toString();
    }

    public String deleteUserRole(Long idUser){
        return new SQL() {{
            DELETE_FROM(TB_USER_ROLE);
            WHERE("id_user = #{idUser}");
        }}.toString();
    }

    public String checkUsername(Long id, String username, Integer type){
        return new SQL() {{
            SELECT("count(id)");
            FROM(TB_USER);
            if(type == 0){
                WHERE("username = #{username}");
            }else{
                WHERE("id <> #{id}");
                WHERE("username = #{username}");
            }
        }}.toString();
    }

    public String updatePasswordByUsername(String password, String username){
        return new SQL() {{
            UPDATE(TB_USER);
            SET("password = #{password}");
            WHERE("username = #{username}");
        }}.toString();
    }

    public String findUserByRole(String roleCode){
        return new SQL() {{
            SELECT("us.NAME, us.PHONE_NUMBER, us.USERNAME, us.STATUS");
            FROM("TB_USER us join TB_USER_ROLE ur on us.ID = ur.ID_USER " +
                        "join TB_ROLE ro on ur.ID_ROLE = ro.ID");
            WHERE("ro.CODE = #{roleCode}");
        }}.toString();
    }
}
