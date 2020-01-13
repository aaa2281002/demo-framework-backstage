package com.framework.model.entity.login;

import com.framework.common.util.other.NumeralUtil;
import com.framework.model.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.model.entity.login
 * @Description 自定义用户实体类
 * @DateTime 2019/12/24 18:37
 * @Version 1.0
 */
public class UserPrincipal implements UserDetails, Serializable {
    private static final long serialVersionUID = 3670633775745565451L;
    //用户信息实体类
    private SystemUser systemUser;

    public UserPrincipal(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (StringUtils.isEmpty(this.systemUser.getRoleCode())) {
            return null;
        }
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        GrantedAuthority role = new SimpleGrantedAuthority(systemUser.getRoleCode());
        roles.add(role);
        return roles;
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getLoginName();
    }

    //帐号是否过期
    @Override
    public boolean isAccountNonExpired() {
//        return systemUser.getAccountExpired().intValue() == NumeralUtil.POSITIVE_ONE;
        return systemUser.getAccountExpired().intValue() == NumeralUtil.POSITIVE_TWO;
//        return true;
    }

    //是否锁住
    @Override
    public boolean isAccountNonLocked() {
//        return systemUser.getAccountLocked().intValue() == NumeralUtil.POSITIVE_ONE;
        return systemUser.getAccountLocked().intValue() == NumeralUtil.POSITIVE_TWO;
//        return true;
    }

    //凭证是否过期
    @Override
    public boolean isCredentialsNonExpired() {
//        return systemUser.getCredentialsExpired().intValue() == NumeralUtil.POSITIVE_ONE;
        return systemUser.getCredentialsExpired().intValue() == NumeralUtil.POSITIVE_TWO;
//        return true;
    }

    //是否禁用
    @Override
    public boolean isEnabled() {
//        return systemUser.getIsEnable().intValue() == NumeralUtil.POSITIVE_ONE;
        return systemUser.getIsEnable().intValue() == NumeralUtil.POSITIVE_TWO;
//        return true;
    }


    /**
     * @param o 1 自定义用户实体类
     * @return boolean
     * @Titel 重写eq方法用于单点登录验证，不然判断会出现匹配不上BUG
     * @Description 重写eq方法用于单点登录验证，不然判断会出现匹配不上BUG
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/18 17:39
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPrincipal)) return false;

        UserPrincipal that = (UserPrincipal) o;

//        return systemUser != null ? systemUser.equals(that.systemUser) : that.systemUser == null;
        return this.systemUser.equals(that.systemUser);
    }

    /**
     * @return int
     * @Titel 重写hashCode用户单点登录验证，不然判断出出现匹配不上BUG
     * @Description 重写hashCode用户单点登录验证，不然判断出出现匹配不上BUG
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/18 17:40
     */
    @Override
    public int hashCode() {
//        return systemUser != null ? systemUser.hashCode() : 0;
        return this.systemUser.hashCode();
    }
}
