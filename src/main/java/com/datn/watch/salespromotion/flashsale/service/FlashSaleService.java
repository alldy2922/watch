package com.datn.watch.salespromotion.flashsale.service;

import com.pet.market.api.common.exception.DataNotFoundException;
import com.pet.market.api.common.exception.InvalidTimeException;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.request.utils.RequestUtils;
import com.pet.market.api.salespromotion.flashsale.model.dto.FlashSaleDto;
import com.pet.market.api.salespromotion.flashsale.model.entity.FlashSale;
import com.pet.market.api.salespromotion.flashsale.repository.FlashSaleRepository;
import com.pet.market.api.salespromotion.productflashsale.model.dto.ProductFlashSaleDto;
import com.pet.market.api.salespromotion.productflashsale.repository.ProductFlashSaleRepository;
import com.pet.market.api.salespromotion.productflashsale.service.ProductFlashSaleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tobi
 */
@Service
public class FlashSaleService {
    private final FlashSaleRepository mapper;
    private final ProductFlashSaleRepository productFlashSaleMapper;
    private final ProductFlashSaleService productFlashSaleService;

    public FlashSaleService(FlashSaleRepository mapper, ProductFlashSaleRepository productFlashSaleMapper, ProductFlashSaleService productFlashSaleService) {
        this.mapper = mapper;
        this.productFlashSaleMapper = productFlashSaleMapper;
        this.productFlashSaleService = productFlashSaleService;
    }

    public Object gets(FlashSaleDto.ListReq req) {
        if (req.getEnabledPage()) {
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), mapper.count(req), ModelMapperUtils.mapAll(mapper.getAll(req), FlashSaleDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), FlashSaleDto.Response.class));
        }
    }

    public Object get(Long id) {
        Optional<FlashSale> flashSaleOptional = mapper.getById(id);
        if (flashSaleOptional.isEmpty()){
            throw new DataNotFoundException();
        }else{
            FlashSaleDto.ResponseDetails responseDetails = ModelMapperUtils.map(flashSaleOptional.get(), FlashSaleDto.ResponseDetails.class);
            ProductFlashSaleDto.ListReq listReq = new ProductFlashSaleDto.ListReq();
            listReq.setIdFlashSale(responseDetails.getId());
            responseDetails.setProducts(ModelMapperUtils.mapAll(productFlashSaleMapper.findAll(listReq), ProductFlashSaleDto.Response.class));
            return  responseDetails;
        }
    }

    @Transactional
    public long save(FlashSaleDto.Request req) {
        //check startDate and endDate does not exist in DataBase with status = 1
        FlashSale flashSale = req.toEntity();
        if (timeValid(flashSale)) {
            mapper.save(flashSale);
            req.getProducts().forEach((obj)->{
                obj.setIdFlashSale(flashSale.getId());
                productFlashSaleService.save(obj);
            });
            RequestUtils.createRequest(flashSale.toRequest());
            return flashSale.getId();
        } else
            throw new InvalidTimeException(InvalidTimeException.TIME_PERIOD_INVALID);
    }

    @Transactional
    public void update(FlashSaleDto.Request req) {
        FlashSale flashSale = req.toEntity();
        if (timeValid(flashSale)) {
            mapper.update(flashSale);
            req.getProducts().forEach((obj)->{
                obj.setIdFlashSale(flashSale.getId());
                productFlashSaleService.save(obj);
            });
        } else
            throw new InvalidTimeException(InvalidTimeException.TIME_PERIOD_INVALID);
    }

    public void updateStatus(FlashSaleDto.StatusReq req) {
        Optional<FlashSale> flashSaleOptional = mapper.getById(req.getId());
        FlashSale flashSale =flashSaleOptional.orElseThrow(DataNotFoundException::new);
        if (timeValid(flashSale)) {
            mapper.updateStatus(req);
        } else
            throw new InvalidTimeException(InvalidTimeException.TIME_PERIOD_INVALID);
    }
    public void approveStatus(FlashSaleDto.StatusReq req){
        mapper.approveStatus(req);
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void deletes(FlashSaleDto.DeleteReq req) {
        mapper.deletes(req);
    }

    public boolean timeValid(FlashSale flashSale) {
        List<FlashSale> flashSales = listFlashSaleDuplicate(flashSale);
        flashSales.removeIf(flashSaleInList -> flashSaleInList.getId() == flashSale.getId());
        return flashSales.isEmpty();
    }
    private List<FlashSale> listFlashSaleDuplicate(FlashSale flashSale) {
        return mapper.findAllWithinDate(flashSale);
    }


}
