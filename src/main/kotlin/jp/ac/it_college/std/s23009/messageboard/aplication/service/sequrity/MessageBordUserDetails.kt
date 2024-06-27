package jp.ac.it_college.std.s23009.messageboard.application.service.security

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MessageBoardUserDetails(private val user: Users) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        // 権限を設定する場合はここに追加する。例として"USER"権限を付与
        return listOf(SimpleGrantedAuthority("USER"))
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun getId(): Long {
        return user.id
    }
}
