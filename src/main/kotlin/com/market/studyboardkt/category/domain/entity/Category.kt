package com.market.studyboardkt.category.domain.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@Entity
@EntityListeners(AuditingEntityListener::class)
class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var id: Long? = null,
    var name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    var parent: Category? = null,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    var children: List<Category>? = null
) {
    @CreatedDate
    @Column(updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    lateinit var updatedDate: LocalDateTime
}