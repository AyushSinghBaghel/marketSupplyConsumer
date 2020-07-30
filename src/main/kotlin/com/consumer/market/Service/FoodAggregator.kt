package com.consumer.market.Service

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@Configuration
@ComponentScan("com.consumer.market")
class FoodAggregator {
    @Bean
    fun getRestTemplate(): RestTemplate? {
        return RestTemplate()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FoodAggregator::class.java, *args)
        }
    }
}