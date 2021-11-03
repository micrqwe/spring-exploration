package xyz.micrqwe.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import xyz.micrqwe.model.ShardingTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:287507016@qq.com">qq</a>
 * @since 2020/4/28 10:03.
 */
@Configuration
public class DataSourceConfig {
    @Value("${sharding.sql.show:false}")
    private String sqlShow;
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.consumer")
    public DataSource hikariDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.ltuser")
    public DataSource dataSource() {
        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds${0..1}.t_order${0..1}");
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getOrderTableRuleConfiguration());
        Map<String, DataSource> result = new HashMap<>();
        result.put("ds0",hikariDataSource());
//        Map<String,Object> map = new HashMap<>();
//        map.put("sql.show",true);
        DataSource dataSource = null;
        Properties p =    new Properties();
        p.setProperty("sql.show",sqlShow);
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(result, shardingRuleConfig,p);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
    public TableRuleConfiguration getOrderTableRuleConfiguration() {
        TableRuleConfiguration result = new TableRuleConfiguration("sharding_test_","ds0.sharding_test_${0..1}");
        result.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(ShardingTest.PROPERTY_NAME_AGE, "sharding_test_${age % 2}"));
        return result;
    }


    // ------------ mybatis配置
    private static ResourceLoader loader = new DefaultResourceLoader();
    private static String MYBATIS_SETTING = "classpath:conf/mybatis.xml";
    @Autowired
    private DataSource dataSource;
/*    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DatasourceProperties datasourceProperties() {
        return new DatasourceProperties();
    }
    @ConfigurationProperties(prefix = "spring.datasource.ltuser")
    public DataSource daatasource() {
        return DataSourceBuilder.create().build();
    }*/
//    @Bean
//    public DataSource dataSource() {
//        return DbUtils.getDataSource(datasourceProperties());
//    }

//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
//        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
//        return paginationInterceptor;
//    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public GlobalConfig mpGlobalConfig() {
        // 全局配置文件
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        // 默认为自增
        dbConfig.setIdType(IdType.AUTO);
        globalConfig.setDbConfig(dbConfig);
//        globalConfig.setSqlInjector(sqlInjector());
        return globalConfig;
    }

    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
//        mybatisConfiguration.setGlobalConfig(mpGlobalConfig());
        mybatisConfiguration.setMapUnderscoreToCamelCase(false);

        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setConfiguration(mybatisConfiguration);
        sqlSessionFactory.setGlobalConfig(mpGlobalConfig());

        sqlSessionFactory.setDataSource(dataSource);
//        sqlSessionFactory.setConfigLocation(loader.getResource(MYBATIS_SETTING));
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:com/micrqwe/dao/*.xml"));
//        Interceptor[] plugins = {paginationInterceptor()};
//        sqlSessionFactory.setPlugins(plugins);
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

/*    @Bean
    public TransactionInterceptor txAdvice() {
        return TransactionBuilder.buildAdvice(transactionManager());
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        return TransactionBuilder.buildAdvisor(txAdvice());
    }*/

//    @Bean
//    public MySqlInjector sqlInjector() {
//        return new MySqlInjector();
//    }
}
