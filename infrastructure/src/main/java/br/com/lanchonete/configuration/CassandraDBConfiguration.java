package br.com.lanchonete.configuration;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
@EnableCassandraRepositories(basePackages = "br.com.lanchonete.cassandra")
public class CassandraDBConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Bean(destroyMethod = "close")
    public CqlSession session() {
        return CqlSession.builder().withKeyspace(keyspace).build();
    }

}
