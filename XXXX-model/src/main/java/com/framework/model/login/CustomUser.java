package com.framework.model.login;

import com.framework.model.system.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * @Author 龘鵺
 * @ClassName com.framework.common.model.login
 * @Description 自定义用户类
 * @Date 2022/3/7 15:40
 * @Version 1.0
 */
public class CustomUser extends User implements Serializable {
    private static final long serialVersionUID = -1L;
    private SystemUser systemUser;

    public CustomUser(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
        super(systemUser.getLoginName(), "", authorities);
        this.systemUser = systemUser;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomUser that = (CustomUser) o;
        return Objects.equals(systemUser, that.systemUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), systemUser);
    }
}