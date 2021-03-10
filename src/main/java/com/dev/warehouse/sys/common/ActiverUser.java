package com.dev.warehouse.sys.common;


import com.dev.warehouse.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {  

    private User user;

    private List<String> roles;

    private List<String> permission;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<String> getRoles()
    {
        return roles;
    }

    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }

    public List<String> getPermission()
    {
        return permission;
    }

    public void setPermission(List<String> permission)
    {
        this.permission = permission;
    }
}
