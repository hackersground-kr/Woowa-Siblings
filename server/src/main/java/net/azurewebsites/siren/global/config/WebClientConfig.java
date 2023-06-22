package net.azurewebsites.siren.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    @Bean
    public UriComponentsBuilder uriComponentsBuilder() {
        return UriComponentsBuilder.newInstance();
    }

    @Bean
    public WebClient webClient() {

        return WebClient.builder()
                .baseUrl("https://siren-emergency-bed.azurewebsites.net/api")
                .build();
    }

}
