package com.datn.watch.front.manage.sample.controller.rest;

import com.datn.watch.common.Constant;
import com.datn.watch.common.logging.APILogger;
import com.datn.watch.common.model.json.Result;
import com.datn.watch.common.model.json.ResultData;
import com.datn.watch.common.utils.paging.ResultPageData;
import com.datn.watch.front.manage.sample.model.dto.ArticleDto;
import com.datn.watch.front.manage.sample.model.entity.Article;
import com.datn.watch.front.manage.sample.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tobi
 */
@RestController
@RequestMapping(value = Constant.API_DEMO)
public class DemoRestController {

    private static final APILogger logger = new APILogger(DemoRestController.class);

    private final ArticleService service;


    public DemoRestController(ArticleService service) {
        this.service = service;
    }

    @GetMapping()
    public ResultPageData<List<Article>> getArticles(@Valid @ModelAttribute ArticleDto.ArticleListReq req) {
        ResultPageData<List<Article>> pagingData = service.gets(req);
        logger.logApi(RequestMethod.GET, Constant.API_DEMO, req, pagingData);
        return pagingData;
    }

    @GetMapping(path = "/{id}")
    public ResultData<Object> getArticle(@PathVariable Long id) {
        ResultData<Object> resultData = new ResultData<>();
        Object object = service.get(id);
        resultData.setResultData(object);
        logger.logApi(RequestMethod.GET, Constant.API_DEMO + "/" + id, object);
        return resultData;
    }

    @PostMapping()
    public Object addArticle(@RequestBody ArticleDto.Request req) {
        try {
            service.save(req);
            logger.logApi(RequestMethod.POST, Constant.API_DEMO, req, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }
    @PatchMapping()
    public Object updateArticle(@RequestBody ArticleDto.Request req) {
        try {
            service.update(req);
            logger.logApi(RequestMethod.PATCH, Constant.API_DEMO, req, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }
    @DeleteMapping(path = "/{id}")
    public Object deleteArticle(@PathVariable Long id) {
        try {
            service.delete(id);
            logger.logApi(RequestMethod.DELETE, Constant.API_DEMO + "/" + id, null, null);
        } catch (Exception ex) {
            return new Result(Result.FAIL, ex.getMessage());
        }
        return new Result();
    }
}
