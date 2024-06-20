package jp.ac.it_college.std.s23009.messageboard.aplication.service

import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import jp.ac.it_college.std.s23009.messageboard.domain.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val usersRepository: UsersRepository
){
    fun  createUser(user: Users): Users{
        return usersRepository.createUser(user)
    }
}