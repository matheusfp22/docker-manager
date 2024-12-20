package com.mfp.docker_manager.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.RemoteApiVersion;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DockerClientConfig {

    @Value("${docker.host}")
    private String dockerHost;

    @Value("${docker.tsl_verify}")
    private String dockerTslVerify;

    @Value("${docker.cert_path}")
    private String dockerCertPath;

    @Bean
    public DockerClient buildDockerClient() {
        DefaultDockerClientConfig.Builder dockerClientConfigBuilder = DefaultDockerClientConfig.createDefaultConfigBuilder();

        dockerClientConfigBuilder
                .withDockerHost(dockerHost)
                .withDockerTlsVerify(dockerTslVerify)
                .withDockerCertPath(dockerCertPath)
                .withApiVersion(RemoteApiVersion.VERSION_1_24);

        DefaultDockerClientConfig dockerClientConfig = dockerClientConfigBuilder.build();

        ApacheDockerHttpClient dockerHttpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(dockerClientConfig.getDockerHost())
                .maxConnections(5)
                .connectionTimeout(Duration.ofMillis(300))
                .responseTimeout(Duration.ofSeconds(3))
                .build();

        DockerClient client = DockerClientBuilder.getInstance(dockerClientConfig)
                .withDockerHttpClient(dockerHttpClient)
                .build();

        client.pingCmd().exec();

        return client;
    }

}
