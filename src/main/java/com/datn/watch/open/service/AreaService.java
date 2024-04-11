package com.datn.watch.open.service;

import com.pet.market.api.common.model.criteria.SearchCriteria;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea;
import com.pet.market.api.interlock.shipping.model.vnpost.VnPostArea.Commune;
import com.pet.market.api.interlock.shipping.vnPost.VnPostService;
import com.pet.market.api.open.model.Area;
import com.pet.market.api.open.model.criteria.AreaCriteria;
import com.pet.market.api.open.model.dto.AreaDTO;
import com.pet.market.api.open.repository.AreaRepository;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
public class AreaService {

    private final AreaRepository repository;

    private final SqlSessionFactory sqlSessionFactory;

    private final VnPostService vnPostService;


    public AreaService(AreaRepository repository, SqlSessionFactory sqlSessionFactory,
        VnPostService vnPostService) {
        this.repository = repository;
        this.sqlSessionFactory = sqlSessionFactory;
        this.vnPostService = vnPostService;
    }

    public List<AreaDTO.AreaRes> getAllProvince(SearchCriteria criteria) {
        List<Area> areas = repository.getAllProvince(criteria);
        return areas.stream().map(AreaDTO.AreaRes::toRes).toList();
    }

    public List<AreaDTO.AreaRes> getAllDistrict(AreaCriteria criteria) {
        List<Area> areas = repository.getAllDistrict(criteria);
        return areas.stream().map(AreaDTO.AreaRes::toRes).toList();
    }

    public List<AreaDTO.AreaRes> getAllCommune(AreaCriteria criteria) {
        List<Area> areas = repository.getAllCommune(criteria);
        return areas.stream().map(AreaDTO.AreaRes::toRes).toList();
    }

    public void deleteAllProvinces() {
        repository.deleteProvince();
    }

    public void deleteAllDistrict() {
        repository.deleteDistrict();
    }

    public void deleteAllCommune() {
        repository.deleteCommune();
    }

    public void batchProvinces() {
        List<VnPostArea.Province> provinces = vnPostService.listAllProvince();

        try(SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AreaRepository mapper = session.getMapper(AreaRepository.class);

            provinces.forEach(mapper::saveProvince);
            session.commit();
        }
    }

    public void batchDistrict() {
        List<VnPostArea.District> districts = vnPostService.listAllDistinct();

        try(SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AreaRepository mapper = session.getMapper(AreaRepository.class);

            districts.forEach(mapper::saveDistrict);
            session.commit();
        }
    }

    public void batchCommune() {

        List<Commune> communes = vnPostService.listAllCommune();

        try(SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            AreaRepository mapper = session.getMapper(AreaRepository.class);
            long start = Instant.now().toEpochMilli();

            try {
                for (int i = 0; i < communes.size(); i++) {
                    mapper.saveCommune(communes.get(i));
                    if (i % 100 == 0) {
                        session.flushStatements();
                        session.clearCache();
                    }
                }
            } catch (Exception e) {
                session.rollback();
                throw e;
            }
            session.commit();

            long end = Instant.now().toEpochMilli();
            long duration = end - start;
            System.out.println("==================== ");
            long second = duration / 1000;
            System.out.println("==================== " + second);
        }
    }

}
