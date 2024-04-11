package com.datn.watch.config.page.service;

import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.config.page.modal.PageDto;
import com.pet.market.api.config.page.modal.PageListDto;
import com.pet.market.api.config.page.modal.PageListReq;
import com.pet.market.api.config.page.modal.entity.Page;
import com.pet.market.api.config.page.modal.entity.PageComponent;
import com.pet.market.api.config.page.repository.PageComponentRepository;
import com.pet.market.api.config.page.repository.PageRepository;
import com.pet.market.api.common.model.entity.UpdateStatusRq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PageService {
    private final PageRepository pageRepo;
    private final PageComponentRepository pageComponentRepo;

    public PageService(PageRepository pageRepository, PageComponentRepository pageComponentRepo) {
        this.pageRepo = pageRepository;
        this.pageComponentRepo = pageComponentRepo;
    }
    public ResultPageData<List<PageListDto>> findAll(PageListReq req){
        int totalRecords = pageRepo.count(req);
        List<PageListDto> listComponent = ModelMapperUtils.mapAll(pageRepo.findAll(req), PageListDto.class);
        return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, listComponent);
    }

    @Transactional
    public void delete(List<Long> ids){
        pageRepo.delete(ids);
        for(Long id : ids){
            pageComponentRepo.delete(id);
        }
    }

    public void updateStatus(UpdateStatusRq rq){
        pageRepo.updateStatus(rq);
    }

    @Transactional
    public void save(PageDto rq){
        Page page = ModelMapperUtils.map(rq, Page.class);
        pageRepo.save(page);
        long id = page.getId();
        for(long item : rq.getComponents()){
            PageComponent pageComponent = new PageComponent();
            pageComponent.setIdPage(id);
            pageComponent.setIdComponent(item);
            pageComponentRepo.save(pageComponent);
        }
    }

    public PageDto findById(long id){
        PageDto pageDto = ModelMapperUtils.map(pageRepo.findById(id), PageDto.class);
        List<Long> components = pageComponentRepo.findByIdPage(id).stream().map(x -> x.getIdComponent()).collect(Collectors.toList());
        pageDto.setComponents(components);
        return pageDto;
    }

    @Transactional
    public void update(PageDto rq){
        Page page = ModelMapperUtils.map(rq, Page.class);
        pageRepo.update(page);
//        pageComponentRepo.delete(rq.getId());
//        for(long item : rq.getComponents()){
//            PageComponent pageComponent = new PageComponent();
//            pageComponent.setIdPage(rq.getId());
//            pageComponent.setIdComponent(item);
//            pageComponentRepo.save(pageComponent);
//        }
    }

}
