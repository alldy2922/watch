package com.datn.watch.request.repository;

import com.pet.market.api.request.model.dto.RequestDto;
import com.pet.market.api.request.model.entity.Request;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RequestRepository {

    @SelectProvider(type = RequestSqlBuilder.class, method = "getAll")
    List<Request> getAll(RequestDto.ListReq req);

    @SelectProvider(type = RequestSqlBuilder.class, method = "findAll")
    List<Request> findAll(RequestDto.ListReq req);

    @SelectProvider(type = RequestSqlBuilder.class, method = "getById")
    Optional<Request> getById(Long id);

    @InsertProvider(type= RequestSqlBuilder.class, method="save")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    long save(Request request);

    @SelectProvider(type = RequestSqlBuilder.class, method = "delete")
    void delete(Long id);

    @SelectProvider(type = RequestSqlBuilder.class, method = "deletes")
    void deletes(RequestDto.ListIdReq req);

    @SelectProvider(type = RequestSqlBuilder.class, method = "update")
    void update(Request request);
    @SelectProvider(type = RequestSqlBuilder.class, method = "updateWithTypeId")
    void updateWithTypeId(Request request);

    @SelectProvider(type = RequestSqlBuilder.class, method = "count")
    int count();

    @SelectProvider(type = RequestSqlBuilder.class, method = "updateStatus")
    void updateStatus(RequestDto.StatusReq req);
    @SelectProvider(type = RequestSqlBuilder.class, method = "approveStatus")
    void approveStatus(RequestDto.ListIdReq req, Long status);
    @SelectProvider(type = RequestSqlBuilder.class, method = "reject")
    void reject(RequestDto.ListIdReq req);
}
