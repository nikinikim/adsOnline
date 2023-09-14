package com.example.adsonline.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.adsonline.properties.S3Properties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class S3config {

    /**
     * Конфигурирует и возвращает экземпляр AmazonS3 для работы с хранилищем S3.
     *
     * @param s3Properties Параметры конфигурации S3, включая accessKey, secretKey, url и port.
     * @return Экземпляр AmazonS3, настроенный в соответствии с переданными параметрами.
     */
    @Bean
    public AmazonS3 s3(S3Properties s3Properties) {
        AWSCredentials credentials = new BasicAWSCredentials(s3Properties.getAccessKey(), s3Properties.getSecretKey());
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");
        String url = s3Properties.getUrl();
        String delimiter = "/";
        if (url.endsWith(delimiter)) {
            url = StringUtils.removeEnd(url,delimiter);
        }

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(url + ":" + s3Properties.getPort()
                        , Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }
}
