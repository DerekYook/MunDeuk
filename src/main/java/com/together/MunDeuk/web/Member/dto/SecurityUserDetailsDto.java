/*
package com.together.MunDeuk.web.Member.dto;

import java.util.Collection;
import java.util.Collections;
import lombok.Getter;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@Getter
public class SecurityUserDetailsDto implements UserDetails {
  @Delegate
  private MemberDto memberDto;
  private Collection<? extends GrantedAuthority> authorities;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(new SimpleGrantedAuthority(memberDto.roleType().toString()));
  }

  @Override
  public String getPassword() {
    return memberDto.password();
  }

  @Override
  public String getUsername() {
    return memberDto.loginId();
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
*/
