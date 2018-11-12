package mainpackage.dto;

import mainpackage.model.Logins;
import mainpackage.type.Role;

import java.util.List;

public class RoleDTO {
    private String username;

    private Role role;

    private List<Logins> logins;
}
