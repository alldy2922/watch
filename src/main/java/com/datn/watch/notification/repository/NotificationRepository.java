package com.datn.watch.notification.repository;

import com.pet.market.api.notification.model.dto.NotificationDto;
import com.pet.market.api.notification.model.entity.Notification;
import com.pet.market.api.salespromotion.flashsale.repository.FlashSaleSqlBuilder;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NotificationRepository {

    @SelectProvider(type = NotificationSqlBuilder.class, method = "getAll")
    List<Notification> getAll(NotificationDto.ListReq req);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "findAll")
    List<Notification> findAll(NotificationDto.ListReq req);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "getById")
    Optional<Notification> getCategoryById(Long id);

    @InsertProvider(type= NotificationSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(Notification obj);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "delete")
    void delete(Long id);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "deletes")
    void deletes(NotificationDto.ListIdReq req);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "update")
    void update(Notification brand);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "count")
    int count();

    @SelectProvider(type = FlashSaleSqlBuilder.class, method = "updateStatus")
    void updateStatus(NotificationDto.StatusReq req);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "getMyUnreadNotificationCount")
    Long getMyUnreadNotificationCount(NotificationDto.ListReq req);

    @UpdateProvider(type = NotificationSqlBuilder.class, method = "markMyNotificationRead")
    void markMyNotificationRead(NotificationDto.ListIdReq req);

    @SelectProvider(type = NotificationSqlBuilder.class, method = "getByIds")
    List<Notification> getByIds(NotificationDto.ListIdReq req);
}
