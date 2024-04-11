package com.datn.watch.config.component.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String data;
    private String templateData;
    private String templateName;
    private String imgPreview;
    private String htmlAdmin;
    private String htmlUser;
    private Long idTemplate;
    @NotNull
    private Integer status;
}
