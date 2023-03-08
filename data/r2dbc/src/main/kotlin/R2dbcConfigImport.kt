package me.lavinoys.data.r2dbc

import me.lavinoys.lib.env.CustomEnvironmentPostProcessor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan
@Configuration
class R2dbcConfigImport : CustomEnvironmentPostProcessor(
    "classpath:/application-r2dbc.yaml"
)
