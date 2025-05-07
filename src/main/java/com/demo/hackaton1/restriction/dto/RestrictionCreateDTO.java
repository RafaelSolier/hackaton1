package com.demo.hackaton1.restriction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrictionCreateDTO {
    @NotNull
    private String modelType;
    private Integer requestLimit;
    private Integer tokenLimit;
    private Integer windowMinutes;
}
