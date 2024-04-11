package com.datn.watch.notification.service;

import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.common.utils.AuthUtils;
import com.pet.market.api.common.utils.ModelMapperUtils;
import com.pet.market.api.common.utils.paging.ResultPageData;
import com.pet.market.api.notification.model.dto.NotificationDto;
import com.pet.market.api.notification.model.entity.Notification;
import com.pet.market.api.notification.repository.NotificationRepository;
import com.pet.market.api.notification.utils.NotificationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository mapper;

    public NotificationService(NotificationRepository mapper) {
        this.mapper = mapper;
    }

    public Object gets(NotificationDto.ListReq req) {
        if (req.getEnabledPage()) {
            int totalRecords = mapper.count();
            List<Notification> list = mapper.getAll(req);
            return new ResultPageData<>(req.getPageNo(), req.getPageSize(), totalRecords, ModelMapperUtils.mapAll(list, NotificationDto.Response.class));
        } else {
            return new ResultData<>(ModelMapperUtils.mapAll(mapper.findAll(req), NotificationDto.Response.class));
        }
    }

    public Long getMyUnreadNotificationCount(NotificationDto.ListReq req) {
        return mapper.getMyUnreadNotificationCount(req);
    }

    @Transactional
    public void markMyNotificationRead(NotificationDto.ListIdReq ids) {
        mapper.markMyNotificationRead(ids);
        NotificationUtils.markMyNotificationRead(AuthUtils.getUserId(), ids.getIds());
    }

    public Object get(Long id) {
        return mapper.getCategoryById(id).map(value -> ModelMapperUtils.map(value, NotificationDto.Response.class)).orElse(null);
    }

    public void save(NotificationDto.Request req) {
        mapper.save(req.toEntity());
    }

    public void update(NotificationDto.Request req) {
        mapper.update(req.toEntity());
    }

    public void delete(Long id) {
        mapper.delete(id);
    }

    public void updateStatus(NotificationDto.StatusReq req) {
        mapper.updateStatus(req);
    }

    public void deletes(NotificationDto.ListIdReq req) {
        mapper.deletes(req);
    }
}
