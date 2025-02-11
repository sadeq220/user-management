package ir.bmi.fusion.user_management.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
    /**
     * DataSourceBuilder.create() can also be used, but @ConfigurationProperties is not always consistent with used DataSource fields
     * for example HikariDataSource have jdbc-url field instead of url
     * DataSourceProperties handles this conversion
     */
    @Bean
    @ConfigurationProperties("app.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    /**
     * It is recommended to define a Naming strategy instead of repetitive @Column and @Table
     */
    @Bean
    public ImplicitNamingStrategy implicitNamingStrategy(){
        return new ImplicitNamingStrategyJpaCompliantImpl();
    }
    @Bean
    public PhysicalNamingStrategy physicalNamingStrategy(){
        return new CamelCaseToUnderscoresNamingStrategy();
    }
}
