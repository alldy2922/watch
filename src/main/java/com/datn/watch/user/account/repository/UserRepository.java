package com.datn.watch.user.account.repository;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.user.account.modal.UserLisReq;
import com.pet.market.api.user.account.modal.entity.User;
import com.pet.market.api.user.account.modal.entity.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {
    @SelectProvider(type= UserSqlBuilder.class, method="findByUsername")
    Optional<User> findByUsername(String username);
    @SelectProvider(type= UserSqlBuilder.class, method="findAll")
    List<User> findAll(UserLisReq req);
    @SelectProvider(type=UserSqlBuilder.class, method="count")
    int count(UserLisReq req);
    @DeleteProvider(type=UserSqlBuilder.class, method="delete")
    void delete(List<Long> ids);
    @UpdateProvider(type=UserSqlBuilder.class, method="updateStatus")
    void updateStatus(UpdateStatusRq rq);
    @InsertProvider(type=UserSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);
    @SelectProvider(type=UserSqlBuilder.class, method="findById")
    Optional<User> findById(long id);
    @UpdateProvider(type=UserSqlBuilder.class, method="update")
    void update(User user);
    @InsertProvider(type=UserSqlBuilder.class, method="saveUserRole")
    void saveUserRole(UserRole userRole);
    @DeleteProvider(type=UserSqlBuilder.class, method="deleteUserRole")
    void deleteUserRole(Long idUser);
    @SelectProvider(type=UserSqlBuilder.class, method="checkUsername")
    int checkUsername(Long id, String username, Integer type);
    @UpdateProvider(type=UserSqlBuilder.class, method="updatePasswordByUsername")
    void updatePasswordByUsername(String password, String username);
    @SelectProvider(type= UserSqlBuilder.class, method="findUserByRole")
    List<User> findUserByRole(String roleCode);
}
