package me.lavinoys.data.db.board

import me.lavinoys.lib.env.CustomEnvironmentPostProcessor
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@ComponentScan
@Configuration
class BoardImportConfig : CustomEnvironmentPostProcessor(
    "classpath:/application-r2dbc.yaml"
)
