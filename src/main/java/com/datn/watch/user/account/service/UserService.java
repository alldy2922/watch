package com.datn.watch.user.account.service;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.user.account.modal.PasswordDto;
import com.pet.market.api.user.account.modal.UserDto;
import com.pet.market.api.user.account.modal.UserLisReq;
import com.pet.market.api.user.account.modal.entity.User;
import com.pet.market.api.user.account.modal.entity.UserRole;
import com.pet.market.api.user.account.repository.UserRepository;
import com.pet.market.api.user.role.modal.RoleDto;
import com.pet.market.api.user.role.repository.RoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepository;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username).get();
    }

    public ResultPageData<List<UserDto>> findAll(UserLisReq req){
        int totalRecords = userRepo.count(req);
        List<UserDto> listUser = ModelMapperUtils.mapAll(userRepo.findAll(req), UserDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listUser);
    }

    @Transactional
    public void delete(List<Long> ids){
        userRepo.delete(ids);
        for(long id : ids){
            userRepo.deleteUserRole(id);
        }
    }

    public void updateStatus(UpdateStatusRq rq){
        userRepo.updateStatus(rq);
    }

    @Transactional
    public Result saveOrUpdate(UserDto rq){
        User user = ModelMapperUtils.map(rq, User.class);
        if(user.getId() == null){
            if(userRepo.checkUsername(user.getId(), user.getUsername(), 0) > 0){
                return new Result("fail", "Tên đăng nhập đã tồn tại");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }else{
            if(userRepo.checkUsername(user.getId(), user.getUsername(), 1) > 0){
                return new Result("fail", "Tên đăng nhập đã tồn tại");
            }
            if(StringUtils.isNotBlank(user.getPassword())){
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepo.update(user);
            userRepo.deleteUserRole(user.getId());
        }
        for(long idRole : rq.getRoles()){
            UserRole userRole = new UserRole();
            userRole.setIdUser(user.getId());
            userRole.setIdRole(idRole);
            userRepo.saveUserRole(userRole);
        }
        return new Result();
    }
    public UserDto findById(long id){
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            UserDto userDto = ModelMapperUtils.map(user.get(), UserDto.class);
            userDto.setRoles(roleRepo.findRoleByUser(id));
            return userDto;
        }
        return null;
    }

    public List<RoleDto> findAllRole(){
        return ModelMapperUtils.mapAll(roleRepo.findAllRole(), RoleDto.class);
    }

    public Result updatePassword(PasswordDto passwordDto){
        String username = AuthUtils.getUserId();
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isPresent()){
            if(passwordEncoder.matches(passwordDto.getOldPassword(),user.get().getPassword())){
                userRepo.updatePasswordByUsername(passwordEncoder.encode(passwordDto.getNewPassword()), username);
            }else{
                return new Result("fail", "Mật khẩu cũ không chính xác");
            }
        }else {
            return new Result("fail", "usenname không tồn tại");
        }
        return new Result();
    }
}
