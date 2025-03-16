package com.market.studyboardkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.market.studyboardkt"])
class StudyBoardKtApplication

fun main(args: Array<String>) {
    runApplication<StudyBoardKtApplication>(*args)

}
