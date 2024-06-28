import jp.ac.it_college.std.s23009.messageboard.domain.model.Users
import jp.ac.it_college.std.s23009.messageboard.domain.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService (
    private val userRepository: UsersRepository
){
    fun findUser(email:String): Users? {
        return userRepository.findByEmail(email)
    }
}