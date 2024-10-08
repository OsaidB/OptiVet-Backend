//package bzu.gradproj.optivet.backend.controller.auth.v1;
//
//import bzu.gradproj.optivet.backend.dto.UserDTO;
//import bzu.gradproj.optivet.backend.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users") // Add this annotation to handle routing
//public class UserControllerV1 extends BaseController {
//
//    private final UserService userService;
//
//    @GetMapping()
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<UserDTO> getUserByName(@PathVariable("username") String username) {
//        UserDTO aUser = userService.getUserByUsername(username);
//        return ResponseEntity.ok(aUser);
//    }
//}
