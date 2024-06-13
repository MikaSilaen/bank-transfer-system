package id.co.task.banktransfersystem.repository;

import id.co.task.banktransfersystem.model.entity.Users;

import java.util.List;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */
public interface UserRepository {
    Users getUserDetail(String username);
}
