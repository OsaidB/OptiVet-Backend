package bzu.gradproj.optivet.backend.service.impl;

import bzu.gradproj.optivet.backend.dto.UserDTO;
//import bzu.gradproj.optivet.backend.exception.NoUserFoundException;
import bzu.gradproj.optivet.backend.exception.ResourceNotFoundException;
import bzu.gradproj.optivet.backend.mapper.UserMapper;
//import bzu.gradproj.optivet.backend.model.entity.FunctionalRole;
import bzu.gradproj.optivet.backend.model.entity.User;
//import bzu.gradproj.optivet.backend.repository.FuncRoleRepo;
import bzu.gradproj.optivet.backend.repository.UserRepo;
import bzu.gradproj.optivet.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bzu.gradproj.optivet.backend.exception.NoUserFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepo userRepo;
//    @Autowired
//    private FuncRoleRepo funcRoleRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> get(Long id) {
        return userRepo.findById(id);
    }

    public User update(User entity) {
        return userRepo.save(entity);
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return userRepo.findAll(filter, pageable);
    }

    public int count() {
        return (int) userRepo.count();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
//        FunctionalRole role = funcRoleRepo.findById(userDTO.getFunctionalRoleId())
//                .orElseThrow(() -> new ResourceNotFoundException("FunctionalRole not found with id : " + userDTO.getFunctionalRoleId()));
        //        //        Role role = FuncRoleRepo.findByRoleName(userDTO.getRole());
//
//        Role role = FuncRoleRepo.findById(userDTO.getRole())
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
//
//        if (role == null) {
//            throw new RuntimeException("Role not found");
//        }
//
//        User user = new User();
//        user.setRole(role);
//
//        UserMapper.INSTANCE.toUserEntity(userDTO);
////        user.setRole(role);

//        Role role = FuncRoleRepo.findByRoleName(userDTO.getRole());
        if (userDTO.getRole() == null) {
            throw new RuntimeException("FunctionalRole not found");
        }

        User user = UserMapper.INSTANCE.toUserEntity(userDTO);
        user.setRole(userDTO.getRole());

        User savedUser = userRepo.save(user);
        return UserMapper.INSTANCE.toUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        return UserMapper.INSTANCE.toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(UserMapper.INSTANCE::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUsersByRole(User.UserRole role) {
//        funcRoleRepo.findById(roleId)
//                .orElseThrow(() -> new ResourceNotFoundException("FunctionalRole not found with id : " + roleId));
        List<User> users = userRepo.findUsersByRole(role);
        return users.stream().map(UserMapper.INSTANCE::toUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO updatedUser) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with the given id: " + userId));

        // Fetch the FunctionalRole entity from the database
//        String role = funcRoleRepo.findById(updatedUser.getFunctionalRoleId())
//                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id : " + updatedUser.getFunctionalRoleId()));
        if (updatedUser.getRole() == null) {
            throw new RuntimeException("Role not found");
        }

        user.setUpdatedAt(updatedUser.getUpdatedAt());
        user.setEmail(updatedUser.getEmail());

//        user.setPassword(updatedUser.getPassword());
//        user.setHash(updatedUser.getHashedPassword());
//        user.setPassword(updatedUser.getHashedPassword());

        user.setRole(updatedUser.getRole()); // Set the FuncRole entity
//        user.setIsTeamLeader(updatedUser.getIsTeamLeader());
//        user.setUsername(updatedUser.getUsername());

        user.setFirstName(updatedUser.getFirstName());

        user.setLastName(updatedUser.getLastName());

        User updatedUserObj = userRepo.save(user);
        return UserMapper.INSTANCE.toUserDTO(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id: " + userId));
        userRepo.deleteById(userId);
    }

//    @Override
//    public UserDTO getUserByUsername(String email) {
////        return Optional.empty();
//
//        User user = userRepo.findByEmail(email).orElseThrow(() -> new NoUserFoundException(String.format("No user found with email '%s'.", email)));
////        User user = userRepo.findByEmail(email).orElseThrow(() -> new NoUserFoundException(String.format("No user found with email '%s'.", email)));
//
//
//
//        return UserMapper.INSTANCE.toUserDTO(user);
//    }
}