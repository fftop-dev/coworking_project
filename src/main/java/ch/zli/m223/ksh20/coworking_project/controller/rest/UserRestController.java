package ch.zli.m223.ksh20.coworking_project.controller.rest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.zli.m223.ksh20.coworking_project.security.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserInputDto;
import ch.zli.m223.ksh20.coworking_project.controller.rest.dto.UserNoReservationDto;
import ch.zli.m223.ksh20.coworking_project.model.User;
import ch.zli.m223.ksh20.coworking_project.security.JwtTokenProvider;
import ch.zli.m223.ksh20.coworking_project.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Authorized(allowedRoles = { "ADMIN" })
    @GetMapping()
    List<UserDto> getUserList() {
        return userService.getUserList().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Authorized(allowedRoles = {"ADMIN"})
    @GetMapping("/show/{uuid}")
    ResponseEntity<?> getUserByUuid(@CookieValue("token") String cookieToken, @PathVariable String uuid){
        UserDto user =  userService.getUserByUuid(uuid);
        return ResponseEntity.ok().body(user);
    }

    @Authorized(allowedRoles = {"MEMBER", "ADMIN"})
    @GetMapping("/show")
    ResponseEntity<?> getLoggedInUser(@CookieValue("token") String cookieToken){
        UserDto user =  userService.getUserByUuid((String) jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub"));
        return ResponseEntity.ok().body(user);
    }


    @Authorized(allowedRoles = {"ADMIN"})
    @DeleteMapping("/delete/{uuid}")
    ResponseEntity<?> deleteUserByUuid(@CookieValue("token") String cookieToken, @PathVariable String uuid){
        if (!jwtTokenProvider.getClaimsFromToken(cookieToken).get("sub").equals(uuid)){
            if (userService.deleteUserByUuid(uuid)){
                return ResponseEntity.ok().body("User [" + uuid + "] has been deleted successfully");
            }
        }
        return ResponseEntity.ok().body("There was an error");
    }


    @PostMapping("/register")
    UserDto createUser(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
            @RequestParam String password) {
        User user = userService.createUser(new UserInputDto(firstName, lastName, email, password));
        return new UserDto(user);
    }

    @PutMapping("/update")
    ResponseEntity<?> updateUser(@CookieValue("token") String cookieToken, @RequestParam String firstName,
            @RequestParam String lastName, @RequestParam String email, @RequestParam String password) {

        Map<String, ?> claims = jwtTokenProvider.getClaimsFromToken(cookieToken);

        UserNoReservationDto updatedUser;

        if (claims.get("role").equals("MEMBER") || claims.get("role").equals("ADMIN")) {
            String uuid = (String) claims.get("sub");
            updatedUser = userService.updateUser(uuid, new UserInputDto(firstName, lastName, email, password));
        } else {
            return ResponseEntity.badRequest().body("Unauthorized");
        }

        return ResponseEntity.ok().body(updatedUser);
    }


}
