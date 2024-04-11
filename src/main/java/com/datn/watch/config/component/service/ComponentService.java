package com.datn.watch.config.component.service;

import com.pet.market.api.common.model.entity.UpdateStatusRq;
import com.pet.market.api.common.model.json.Result;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.component.modal.ComponentDto;
import com.pet.market.api.config.component.modal.ComponentListDto;
import com.pet.market.api.config.component.modal.ComponentListReq;
import com.pet.market.api.config.component.modal.entity.Component;
import com.pet.market.api.config.component.repository.ComponentRepository;
import com.pet.market.api.config.page.repository.PageComponentRepository;
import com.pet.market.api.config.template.modal.TemplateDto;
import com.pet.market.api.config.template.service.TemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComponentService {
    private final ComponentRepository componentRepo;

    private final PageComponentRepository pageComponentRepo;
    private final TemplateService templateService;
    public ComponentService(ComponentRepository componentRepo, PageComponentRepository pageComponentRepo, TemplateService templateService) {
        this.componentRepo = componentRepo;
        this.pageComponentRepo = pageComponentRepo;
        this.templateService = templateService;
    }
    public ResultPageData<List<ComponentListDto>> findAll(ComponentListReq req){
        int totalRecords = componentRepo.count(req);
        List<ComponentListDto> listComponent = ModelMapperUtils.mapAll(componentRepo.findAll(req), ComponentListDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listComponent);
    }

    @Transactional
    public Result delete(List<Long> ids){
        for(Long id : ids){
           int amount = pageComponentRepo.countByIdComponent(id);
           if(amount > 0){
               return new Result("fail","Tồn tại component đang sử dụng trong page");
           }
        }
        componentRepo.delete(ids);
        return new Result();
    }

    public void updateStatus(UpdateStatusRq rq){
        componentRepo.updateStatus(rq);
    }

    @Transactional
    public void save(ComponentDto rq){
        Component component = ModelMapperUtils.map(rq, Component.class);
        TemplateDto template = templateService.getTemplateById(rq.getIdTemplate());
        component.setTemplateName(template.getTemplateName());
        component.setHtmlAdmin(template.getHtmlAdmin());
        component.setHtmlUser(template.getHtmlUser());
        component.setImgPreview(template.getImgPreview());
        componentRepo.save(component);
    }

    @Transactional
    public void update(ComponentDto rq){
        Component component = ModelMapperUtils.map(rq, Component.class);
        if(rq.getIdTemplate() != null){
            TemplateDto template = templateService.getTemplateById(rq.getIdTemplate());
            component.setTemplateName(template.getTemplateName());
            component.setHtmlAdmin(template.getHtmlAdmin());
            component.setHtmlUser(template.getHtmlUser());
            component.setImgPreview(template.getImgPreview());
        }
        componentRepo.update(component);
    }

    public ComponentDto findById(long id){
        return ModelMapperUtils.map(componentRepo.findById(id), ComponentDto.class);
    }

    public ResultData<List<ComponentListDto>> findByStatus(){
        ResultData<List<ComponentListDto>> data = new ResultData<>();
        List<ComponentListDto> listComponent = ModelMapperUtils.mapAll(componentRepo.findByStatus(), ComponentListDto.class);
        data.setResultData(listComponent);
        return data;
    }
}
