package com.demo.hackaton1.restriction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestrictionDTO {
    private Long id;
    private String modelType;
    private Integer requestLimit;
    private Integer tokenLimit;
    private Integer windowMinutes;
}
