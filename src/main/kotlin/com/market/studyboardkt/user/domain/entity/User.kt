package com.market.studyboardkt.user.domain.entity
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class)

class User(
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "user_id")
     var id : Long? = null,
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
     var isDeleted: Boolean = false,

) {
     @CreatedDate
     @Column(updatable = false)
     lateinit var createdDate: LocalDateTime
     @LastModifiedDate
     lateinit var updatedDate: LocalDateTime

     fun checkPassword(rawPassword: String, encoder: PasswordEncoder) = encoder.matches(rawPassword, password)
}