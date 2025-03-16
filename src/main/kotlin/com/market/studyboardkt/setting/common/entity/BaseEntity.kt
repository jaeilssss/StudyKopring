package com.market.studyboardkt.setting.common.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class BaseEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
) {

}