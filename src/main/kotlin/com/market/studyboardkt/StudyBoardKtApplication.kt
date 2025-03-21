package com.market.studyboardkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(scanBasePackages = ["com.market.studyboardkt"])
@EnableJpaAuditing
class StudyBoardKtApplication

fun main(args: Array<String>) {
    runApplication<StudyBoardKtApplication>(*args)

}
