package jp.ac.it_college.std.s23009.messageboard.presentation.controller

import jp.ac.it_college.std.nakasone.message.board.presentation.form.GetUserInfoResponse
import jp.ac.it_college.std.nakasone.message.board.presentation.form.PostUserRegisterRequest
import jp.ac.it_college.std.s23009.messageboard.application.service.UserService
import jp.ac.it_college.std.s23009.messageboard.application.service.security.MessageBoardUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@CrossOrigin
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/register")
    fun register(@RequestBody user: PostUserRegisterRequest) {
        user.run {
            userService.register(viewName, email, password)
        }
    }

    @GetMapping("/info")
    fun getInfo(
        @AuthenticationPrincipal user: MessageBoardUserDetails
    ): GetUserInfoResponse {
        return userService.find(user.getId()).run {
            GetUserInfoResponse(id, viewName)
        }
    }
}
