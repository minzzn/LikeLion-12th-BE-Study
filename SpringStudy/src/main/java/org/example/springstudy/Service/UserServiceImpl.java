package org.example.springstudy.Service;

import org.example.springstudy.DTO.UserDTO;
import org.example.springstudy.DTO.UserResponseDTO;
import org.example.springstudy.Entity.User;
import org.example.springstudy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserDTO userDTO) {
        // DTO->Entity 변환
        User userEntity = toEntity(userDTO);

        // 엔티티 저장
        userRepository.save(userEntity);
        return toUserResponseDTO(userEntity);
    }

    @Override
    public UserResponseDTO readUser(Long id) {
        // id 값으로 엔티티 조회
        User userEntity = userRepository.getReferenceById(id);
        return toUserResponseDTO(userEntity);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserDTO userDTO) {
        // id 값으로 엔티티 조회
        User userEntity = userRepository.getReferenceById(id);

        // update
        userEntity.updateName(userDTO.getName());
        userEntity.updateEmail(userDTO.getEmail());
        userEntity.updateAddress(userDTO.getAddress());

        // 저장
        userRepository.save(userEntity);

        return toUserResponseDTO(userEntity);
    }

    @Override
    public String deleteUser(Long id) {
        // id 값으로 엔티티 삭제
        userRepository.deleteById(id);
        return "success";
    }

    // DTO -> User
    public User toEntity(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .build();
    }

    // User -> DTO
    public UserResponseDTO toUserResponseDTO(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }
}
