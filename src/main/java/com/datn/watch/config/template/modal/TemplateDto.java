package com.datn.watch.config.template.modal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {
    private long id;
    @NotBlank
    private String templateName;
    private String type;
    private String htmlAdmin;
    private String htmlUser;
    @NotBlank
    private String imgPreview;
    @NotNull
    private Integer status;
}
