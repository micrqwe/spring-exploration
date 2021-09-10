//package xyz.micrqwe.config;
//
//import org.apache.http.HttpHost;
//import org.apache.http.client.config.RequestConfig;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * description TODO
// *
// * @author shaowenxing
// * @version 1.0
// * @date 2021-09-08 09:44
// **/
//@Configuration
//public class SystemConfig {
//    @Bean
//    public RestHighLevelClient restHighLevelClient() {
//        RestClientBuilder restClientBuilder = RestClient.builder(
//                new HttpHost("150.158.195.216", 9200, "http"))
//                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setConnectTimeout(5000) //连接超时（默认为1秒）
//                                .setSocketTimeout(60000);//套接字超时（默认为30秒）
//                    }
//                });
//        return new RestHighLevelClient(restClientBuilder);
//    }
//}
