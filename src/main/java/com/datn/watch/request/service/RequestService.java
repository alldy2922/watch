package com.datn.watch.request.service;

import com.pet.market.api.common.exception.DataNotFoundException;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.notification.utils.NotificationUtils;
import com.pet.market.api.request.enumeration.Status;
import com.pet.market.api.request.model.dto.RequestDto;
import com.pet.market.api.request.model.entity.Request;
import com.pet.market.api.request.repository.RequestRepository;
import com.pet.market.api.request.utils.RequestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author tobi
 */
@Service
public class RequestService {
    private final RequestRepository mapper;

    public RequestService(RequestRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(RequestDto.ListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count();
            List<Request> list = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(list, RequestDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), RequestDto.Response.class));
        }
    }

    public Object get(Long id) {
        return mapper.getById(id).map(value -> ModelMapperUtils.map(value, RequestDto.Response.class)).orElse(null);
    }

    public void save(RequestDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(RequestDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    @Transactional
    public void updateStatus(RequestDto.ListIdReq req, Status status) {
        Long statusValue = status.equals(Status.APPROVED) ? 1L : 2L;
        mapper.approveStatus(req, statusValue);
        for (Long id : req.getIds()) {
            Optional<Request> requestOptional = mapper.getById(id);
            Request request = requestOptional.orElseThrow(DataNotFoundException::new);
            RequestUtils.approveStatus(request);
            NotificationUtils.createNotification(request);
        }
    }

    public void deletes(RequestDto.ListIdReq req) {
        mapper.deletes(req);
    }

}
