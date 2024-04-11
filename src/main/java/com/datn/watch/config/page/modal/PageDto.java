package com.datn.watch.config.page.modal;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String data;
    @NotBlank
    private String htmlData;
    private List<Long> components;
    @NotNull
    private Integer status;
}
