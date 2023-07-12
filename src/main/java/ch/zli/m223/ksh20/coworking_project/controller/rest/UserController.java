package ch.zli.m223.ksh20.coworking_project.controller.rest;


import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.coworking_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    List<UserDto> getUserList() {
        return userService.getUserList().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
