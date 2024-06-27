package jp.ac.it_college.std.s23009.messageboard.application.service

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import jp.ac.it_college.std.s23009.messageboard.domain.repository.UsersRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(email: String, password: String, viewName: String): Users {
        println("Registering user with email: $email")
        val hashedPassword = passwordEncoder.encode(password)
        val user = Users(
            id = 0, // 新規登録時にはIDは自動生成されるため0で初期化
            email,
            hashedPassword,
            viewName
        )
        return usersRepository.createUser(user)
    }

    fun findByEmail(email: String): Users? {
        println("Finding user by email: $email")
        return usersRepository.findByEmail(email)
    }
}
