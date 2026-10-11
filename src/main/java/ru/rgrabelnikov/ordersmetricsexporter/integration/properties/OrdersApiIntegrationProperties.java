package ru.rgrabelnikov.ordersmetricsexporter.integration.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@JsonIgnoreProperties
@ConfigurationProperties("integration.orders-api")
public record OrdersApiIntegrationProperties(
        @URL @NotBlank String statsUrl
) {
}
