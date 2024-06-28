package jp.ac.it_college.std.s23009.book.manager.presentaion.config

import AuthenticationService
import jp.ac.it_college.std.s23009.messageboard.application.service.security.MessageBoardUserDetails
import jp.ac.it_college.std.s23009.messageboard.domain.types.RoleType
import jp.ac.it_college.std.s23009.messageboard.presentation.handler.SecurityHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationService: AuthenticationService
) {
    @Bean
    @Order(1)
    fun configure(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/login", permitAll)
                authorize("/admin/**", hasAuthority(RoleType.ADMIN.toString()))
                authorize(anyRequest, authenticated)
            }
            formLogin {
                // 認証(ログイン)に関する設定(２)
                loginProcessingUrl = "/login"
                usernameParameter = "email"
                passwordParameter = "password"
                authenticationSuccessHandler = SecurityHandler
                authenticationFailureHandler = SecurityHandler
            }
            csrf {
                // クロスサイトリクエストフォージェリ対策の無効化
                // REST APIでこれがあると利用不可になるため無効化
                disable()
            }
            exceptionHandling {
                // 未認証 or 拒否権の設定
                authenticationEntryPoint = SecurityHandler
                accessDeniedHandler = SecurityHandler
            }
            cors {
                // CORSの設定(４)。多分書かなくてOK
            }
        }
        return http.build()
    }

    // パスワードエンコーダを決めるメソッド
    // パスワード暗号化アルゴリズムを指定(Argon2id)
    @Bean
    fun passwordEncoder(): PasswordEncoder = Argon2PasswordEncoder(
        16, 32, 1, 19 * 1024, 2
    )

    // 認証処理を実装したクラスの指定
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        // CORS の基本設定
        val config = CorsConfiguration().apply {
            // 許可する HTTP メソッドのリスト。実際使うのは GET POST PATCH
            allowedMethods = listOf(CorsConfiguration.ALL)
            allowedHeaders = listOf(CorsConfiguration.ALL)
            allowedOrigins = listOf("http://localhost:3000")
            allowCredentials = true
        }
        // 基本設定を適用するリクエストパスの設定
        val source = UrlBasedCorsConfigurationSource().apply {
            // 全リクエストに CORS設定を適用
            registerCorsConfiguration("/**", config)
        }
        return source
    }
}