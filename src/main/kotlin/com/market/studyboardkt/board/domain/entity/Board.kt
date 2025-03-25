package com.market.studyboardkt.board.domain.entity

import com.market.studyboardkt.user.domain.entity.User
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Board(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    var id: Long? = null,
    var title: String,
    var content: String,
    var likeCount: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var author : User
) {

    @CreatedDate
    @Column(updatable = false)
    lateinit var createdDate: LocalDateTime
    @LastModifiedDate
    lateinit var updatedDate: LocalDateTime
}