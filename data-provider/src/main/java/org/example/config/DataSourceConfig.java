package org.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "hospital1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hospital1")
    public DataSource hospital1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hospital2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hospital2")
    public DataSource hospital2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hospital3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.hospital3")
    public DataSource hospital3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hospital1JdbcTemplate")
    public JdbcTemplate hospital1JdbcTemplate(@Qualifier("hospital1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "hospital2JdbcTemplate")
    public JdbcTemplate hospital2JdbcTemplate(@Qualifier("hospital2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "hospital3JdbcTemplate")
    public JdbcTemplate hospital3JdbcTemplate(@Qualifier("hospital3DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    @Bean
    public DataSourceInitializer hospital1Initializer(@Qualifier("hospital1DataSource") DataSource dataSource) {
        return createInitializer(dataSource,
                "createTables/postgresql_5.4_ddl.sql",
                "createTables/postgresql_5.4_primary_keys.sql",
                "createTables/postgresql_5.4_indices.sql");
    }

    // Hospital 1 Insert Patient Data
    @Bean
    public DataSourceInitializer hospital1InsertPatientDataInitializer(@Qualifier("hospital1DataSource") DataSource dataSource) {
        return createInitializer(dataSource,
                "data_insert_scripts/insert_condition_era_1.sql",
                "data_insert_scripts/insert_condition_occurrence_1.sql",
                "data_insert_scripts/insert_death_1.sql",
                "data_insert_scripts/insert_drug_era_1.sql",
                "data_insert_scripts/insert_drug_exposure_1.sql",
                "data_insert_scripts/insert_person_1.sql",
                "data_insert_scripts/insert_procedure_occurrence_1.sql");
    }

    // Hospital 1 Insert Concept Data
    @Bean
    public DataSourceInitializer hospital1InsertConceptDataInitializer(@Qualifier("hospital1DataSource") DataSource dataSource) {
        return createInitializer(dataSource,
                "CONCEPT_insert_scripts/splitted_files/insert_01_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_01_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_01_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_01_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_02_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_02_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_02_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_02_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_03_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_03_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_03_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_03_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_04_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_04_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_04_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_04_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_05_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_05_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_05_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_05_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_06_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_06_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_06_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_06_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_07_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_07_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_07_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_07_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_08_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_08_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_08_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_08_4.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_09_1.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_09_2.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_09_3.sql",
                "CONCEPT_insert_scripts/splitted_files/insert_09_4.sql",
                "CONCEPT_insert_scripts/original_files/insert_10.sql");
    }

    @Bean
    public DataSourceInitializer hospital2Initializer(@Qualifier("hospital2DataSource") DataSource dataSource) {
        return createInitializer(dataSource,
                "createTables/postgresql_5.4_ddl.sql",
                "createTables/postgresql_5.4_primary_keys.sql",
                "createTables/postgresql_5.4_indices.sql");
    }

    @Bean
    public DataSourceInitializer hospital3Initializer(@Qualifier("hospital3DataSource") DataSource dataSource) {
        return createInitializer(dataSource,
                "createTables/postgresql_5.4_ddl.sql",
                "createTables/postgresql_5.4_primary_keys.sql",
                "createTables/postgresql_5.4_indices.sql");
    }

    private DataSourceInitializer createInitializer(DataSource dataSource, String... schemaScripts) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        for (String script : schemaScripts)
        {
            populator.addScript(new ClassPathResource(script));
        }
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}