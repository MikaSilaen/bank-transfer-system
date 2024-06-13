package id.co.task.banktransfersystem.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Mika Silaen
 * @created on 6/11/2024
 */

@Data
@Accessors(chain = true)
public class Users{
    private int userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Date dateOfBirth;
    private String addres;
    private Date createdAt;
    private Date updatedAt;
}
