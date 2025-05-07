package com.demo.hackaton1.requestLog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestLogDTO {
    private Long id;
    private String modelType;
    private String queryText;
    private String responseText;
    private Integer tokensUsed;
    private LocalDateTime timestamp;
    private String fileName;
    private Long userId;
    private Long companyId;
}