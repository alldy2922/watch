package com.datn.watch.config.template.service;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.template.modal.TemplateDto;
import com.pet.market.api.config.template.modal.TemplateListReq;
import com.pet.market.api.config.template.modal.TemplateOptionDto;
import com.pet.market.api.config.template.modal.entity.Template;
import com.pet.market.api.config.template.repository.TemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TemplateService {
    private final TemplateRepository templateRepo;

    public TemplateService(TemplateRepository templateRepo) {
        this.templateRepo = templateRepo;
    }

    public ResultPageData<List<TemplateDto>> findAll(TemplateListReq req){
        int totalRecords = templateRepo.count(req);
        List<TemplateDto> listTemplate = ModelMapperUtils.mapAll(templateRepo.findAll(req), TemplateDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listTemplate);
    }

    public void updateStatus(UpdateStatusRq rq){
        templateRepo.updateStatus(rq);
    }

    @Transactional
    public void save(TemplateDto rq){
        Template template = ModelMapperUtils.map(rq, Template.class);
        templateRepo.save(template);
    }

    public TemplateDto getTemplateById(long id){
        TemplateDto templateDto = ModelMapperUtils.map(templateRepo.findById(id), TemplateDto.class);
        return templateDto;
    }

    @Transactional
    public void update(TemplateDto rq){
        Template template = ModelMapperUtils.map(rq, Template.class);
        templateRepo.update(template);
    }

    @Transactional
    public void delete(List<Long> ids){
        templateRepo.delete(ids);
    }

    public List<TemplateOptionDto> selectAll(){
        List<TemplateOptionDto> listTemplate = ModelMapperUtils.mapAll(templateRepo.selectAll(), TemplateOptionDto.class);
        return listTemplate;
    }
}
