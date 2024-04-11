package com.datn.watch.open.service;

import com.pet.market.api.open.model.dto.CommonDTO;
import com.pet.market.api.open.repository.CommonCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonCodeService {

    private final CommonCodeRepository repository;


    public CommonCodeService(CommonCodeRepository repository) {
        this.repository = repository;
    }

    public List<CommonDTO.CodeRes> listByGroup(String group) {
        return repository.listByGroup(group);
    }
}
