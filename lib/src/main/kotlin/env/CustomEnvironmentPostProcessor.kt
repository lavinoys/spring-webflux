package me.lavinoys.lib.env

import org.springframework.boot.SpringApplication
import org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.boot.env.EnvironmentPostProcessor
import org.springframework.core.Ordered
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource

abstract class CustomEnvironmentPostProcessor(
    location: String,
    vararg additionalLocations: String,
    propertySourceName: String? = null,
) : EnvironmentPostProcessor, Ordered {

    open val locations: List<String> = listOf(location, *additionalLocations)

    open val propertySourceName: String = propertySourceName
        ?: this::class.qualifiedName
        ?: CustomEnvironmentPostProcessor::class.qualifiedName!!

    override fun getOrder(): Int {
        // should before spring-boot ConfigDataEnvironmentPostProcessor
        return ConfigDataEnvironmentPostProcessor.ORDER - 1
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        // current imports (spring.config.import)
        val imports = Binder.get(environment)
            .bind("spring.config.import", Array<String>::class.java)
            .orElse(emptyArray())
            .toList()

        // locations added imports
        val addedImports = (imports + locations).joinToString()

        // add property source
        environment.propertySources.addFirst(
            MapPropertySource(
                propertySourceName,
                mapOf(
                    "spring.config.import" to addedImports
                )
            )
        )
    }
}
