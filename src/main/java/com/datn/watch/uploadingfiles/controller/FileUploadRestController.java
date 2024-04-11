package com.datn.watch.uploadingfiles.controller;

import com.pet.market.api.common.Constant;
import com.pet.market.api.common.logging.APILogger;
import com.pet.market.api.common.model.json.ResultData;
import com.pet.market.api.uploadingfiles.service.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(" API v1  File Upload")
@Tag(name = "File Upload API v1")
@RequestMapping(value = Constant.API_FILE_UPLOAD)
public class FileUploadRestController {

    private static final APILogger logger = new APILogger(FileUploadRestController.class);

    private final FileStorageService service;

    public FileUploadRestController(FileStorageService service) {
        this.service = service;
    }

    @PostMapping("/uploadFile")
    public Object uploadFile(@RequestParam(value = "folder", required = false) String folder,
                             @ModelAttribute MultipartFile file) {
        ResultData<Object> resp = new ResultData<>();
        resp.setResultData(service.uploadFile(folder, file));
        logger.logApi(RequestMethod.POST, Constant.API_FILE_UPLOAD + "?folder=" + folder, "req", resp);
        return resp;
    }

//    @PostMapping("/uploadMultipleFiles")
//    public Object uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        List<FileStoragePropertiesDto.UploadFileResponse> uploadFileResponses = new ArrayList<>();
//        Arrays.stream(files).toList().forEach(e -> uploadFileResponses.add(new FileStoragePropertiesDto.UploadFileResponse(fileName, fileDownloadUri,
//                e.getContentType(), e.getSize())));
//        return new ResultData<>(uploadFileResponses);
//    }
}
