package com.demo.hackaton1.requestLog.domain;

import java.util.List;

public interface RequestLogService {
    RequestLog logRequest(RequestLog log);
    List<RequestLog> getLogsByUser(Long userId);
    List<RequestLog> getLogsByCompany(Long companyId);
}
