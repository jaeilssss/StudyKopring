package com.market.studyboardkt.category.domain.entity

import com.market.studyboardkt.category.application.dto.request.ModifyCategoryDto
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
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
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var parent: Category? = null,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = arrayOf(CascadeType.ALL))
    var children: List<Category>? = null
) {
    @CreatedDate
    @Column(updatable = false)
    lateinit var createdDate: LocalDateTime

    @LastModifiedDate
    lateinit var updatedDate: LocalDateTime

    fun modify(parent: Category?, modifyRequest: ModifyCategoryDto) {
        this.parent = parent
        this.name = modifyRequest.name
    }

}