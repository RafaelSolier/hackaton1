package com.demo.hackaton1.user.aplication;

import com.demo.hackaton1.requestLog.domain.RequestLog;
import com.demo.hackaton1.requestLog.domain.RequestLogService;
import com.demo.hackaton1.requestLog.dto.RequestLogDTO;
import com.demo.hackaton1.user.domain.User;
import com.demo.hackaton1.user.domain.UserService;
import com.demo.hackaton1.user.dto.UserCreateDTO;
import com.demo.hackaton1.user.dto.UserDTO;
import com.demo.hackaton1.user.dto.UserUpdateDTO;
import com.demo.hackaton1.userLimit.domain.UserLimit;
import com.demo.hackaton1.userLimit.domain.UserLimitService;
import com.demo.hackaton1.userLimit.dto.UserLimitCreateDTO;
import com.demo.hackaton1.userLimit.dto.UserLimitDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserLimitService userLimitService;
    private final RequestLogService requestLogService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<UserDTO> create(
            @RequestParam Long companyId,
            @Valid @RequestBody UserCreateDTO dto) {
        User entity = mapper.map(dto, User.class);
        User saved = userService.createUser(companyId, entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.map(saved, UserDTO.class));
    }

    @GetMapping
    public List<UserDTO> list(@RequestParam Long companyId) {
        return userService.getUsersByCompany(companyId)
                .stream()
                .map(u -> mapper.map(u, UserDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable Long id) {
        return mapper.map(userService.getUserById(id), UserDTO.class);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id,
                          @Valid @RequestBody UserUpdateDTO dto) {
        User entity = mapper.map(dto, User.class);
        return mapper.map(userService.updateUser(id, entity), UserDTO.class);
    }

    @PostMapping("/{id}/limits")
    public ResponseEntity<UserLimitDTO> assignLimit(
            @PathVariable Long id,
            @Valid @RequestBody UserLimitCreateDTO dto) {
        UserLimit entity = mapper.map(dto, UserLimit.class);
        UserLimit saved = userLimitService.assignLimitToUser(id, entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.map(saved, UserLimitDTO.class));
    }

    @GetMapping("/{id}/consumption")
    public List<RequestLogDTO> consumption(@PathVariable Long id) {
        List<RequestLog> logs = requestLogService.getLogsByUser(id);
        return logs.stream()
                .map(log -> mapper.map(log, RequestLogDTO.class))
                .toList();
    }
}
