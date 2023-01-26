package com.compass.mscatalog.domain.dto.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Envio dos dados da imagem")
public class MediaFormDto {

    @ApiModelProperty(value = "Url")
    @NotNull
    private String imageUrl;

    @ApiModelProperty(value = "Id da sku")
    @NotNull
    private Long skuId;

}
