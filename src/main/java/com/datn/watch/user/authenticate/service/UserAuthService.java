package com.datn.watch.user.authenticate.service;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.user.account.modal.entity.User;
import com.pet.market.api.user.account.repository.UserRepository;
import com.pet.market.api.user.authenticate.model.entity.UserAuth;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthService {
    private final UserRepository userRepository;

    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserAuth findUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return ModelMapperUtils.map(user.get(), UserAuth.class);
        }
        return null;
    }
}

