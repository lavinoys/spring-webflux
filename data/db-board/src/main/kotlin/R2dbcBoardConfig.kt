package me.lavinoys.data.db.board

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import org.springframework.data.convert.WritingConverter
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.r2dbc.dialect.MySqlDialect
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.core.DatabaseClient
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

@Configuration
@EnableConfigurationProperties(R2dbcBoardConfig.DbProperty::class)
@ConditionalOnMissingBean(name = ["boardConnectionFactory"])
@EnableR2dbcRepositories(
    entityOperationsRef = "boardEntityOperations",
    basePackages = ["me.lavinoys.data.db.board"]
)
class R2dbcBoardConfig(
    val dbProperty: DbProperty
) {

    @Bean
    @Qualifier("boardConnectionFactory")
    fun boardConnectionFactory(): ConnectionFactory = ConnectionFactoryOptions
        .builder()
        .apply {
            option(ConnectionFactoryOptions.PORT, dbProperty.port)
            option(ConnectionFactoryOptions.USER, dbProperty.username)
            option(ConnectionFactoryOptions.PASSWORD, dbProperty.password)
        }
        .from(ConnectionFactoryOptions.parse(dbProperty.url))
        .build()
        .let { ConnectionFactories.get(it) }
        .let {
            ConnectionPool(
                ConnectionPoolConfiguration.builder(it)
                    .validationQuery("SELECT 1")
                    .acquireRetry(5)
                    .initialSize(1)
                    .maxSize(5)
                    .build()
            )
        }

    @Bean
    fun boardEntityOperations(
        @Qualifier("boardConnectionFactory") connectionFactory: ConnectionFactory
    ): R2dbcEntityOperations {
        val strategy = DefaultReactiveDataAccessStrategy(MySqlDialect.INSTANCE, getCustomConverters())
        val databaseClient: DatabaseClient = DatabaseClient.builder()
            .connectionFactory(connectionFactory)
            .bindMarkers(MySqlDialect.INSTANCE.bindMarkersFactory)
            .build()
        return R2dbcEntityTemplate(databaseClient, strategy)
    }
    @Bean
    fun boardTransactionManager(
        @Qualifier("boardConnectionFactory") connectionFactory: ConnectionFactory
    ) = R2dbcTransactionManager(connectionFactory)

    fun getCustomConverters(): List<Any> {
        return listOf(
            ByteBoolConverter(),
            InstantReadConverter(),
            InstantWriteConverter()
        )
    }

    @ReadingConverter
    class ByteBoolConverter : Converter<Byte, Boolean> {
        override fun convert(source: Byte): Boolean = (source.toInt() != 0)
    }

    @ReadingConverter
    class InstantReadConverter : Converter<LocalDateTime, Instant> {
        override fun convert(source: LocalDateTime): Instant = source.atOffset(ZoneOffset.UTC).toInstant()
    }

    @WritingConverter
    class InstantWriteConverter : Converter<Instant, LocalDateTime> {
        override fun convert(source: Instant): LocalDateTime = source.atOffset(ZoneOffset.UTC).toLocalDateTime()
    }

    @ConfigurationProperties("db.board")
    data class DbProperty(
        val url: String = "r2dbc:h2:mem:///legacy",
        val port: Int = 3306,
        val username: String,
        val password: String,
    )
}
