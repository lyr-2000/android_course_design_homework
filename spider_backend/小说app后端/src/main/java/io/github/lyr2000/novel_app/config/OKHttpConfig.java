package io.github.lyr2000.novel_app.config;

import okhttp3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @Author lyr
 * @create 2021/4/13 21:13
 */
@Configuration
public class OKHttpConfig {
    /**
     * 创建 连接池
     * @return
     */
    @Bean
    public OkHttpClient okHttpClient() {

        // ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        //         .tlsVersions(TlsVersion.TLS_1_2)
        //         .cipherSuites(
        //                 CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
        //                 CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
        //                 CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
        //         .build();



        return new OkHttpClient
                .Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                // .connectionSpecs(Collections.singletonList(spec))
                .connectionPool(new ConnectionPool(16,3, TimeUnit.MINUTES))
                .readTimeout(Duration.ofSeconds(10))
                .writeTimeout(Duration.ofSeconds(6))
                .build();
    }
}
