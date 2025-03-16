package com.market.studyboardkt.user.domain.entity
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "users")
data class User(
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "user_id")
     var id : Long = 0,
     @Column(nullable = false)
     var name: String,
     @Column(nullable = false)
     var email: String,
     @Column(nullable = false)
     var birthDay: String,
     @Column(nullable = false)
     var password: String,
     @Column(nullable = false)
     var nickName: String,
     @Column(nullable = false)
     var address: String,
     @Column(nullable = false)
     var phoneNumber: String,
     @Column(nullable = false)
     var isDeleted: Boolean = false
) {
     fun checkPassword(rawPassword: String, encoder: PasswordEncoder) = encoder.matches(rawPassword, password)
}