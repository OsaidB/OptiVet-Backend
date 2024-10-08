package bzu.gradproj.optivet.backend.service;

import bzu.gradproj.optivet.backend.dto.UserDTO;
import bzu.gradproj.optivet.backend.model.entity.User.UserRole; // Import the UserRole enum

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDto);
    UserDTO getUserById(Long userId);
    List<UserDTO> getAllUsers();
    List<UserDTO> getUsersByRole(UserRole role); // Accepting UserRole enum directly from User entity
    UserDTO updateUser(Long userId, UserDTO updatedUser);
    void deleteUser(Long userId);
    UserDTO getUserByEmail(String email); // Changed from username to email
}
