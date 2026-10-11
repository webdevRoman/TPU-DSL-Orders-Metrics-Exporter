package ru.rgrabelnikov.ordersmetricsexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OrdersMetricsExporterApplication {

    static void main(String[] args) {
        SpringApplication.run(OrdersMetricsExporterApplication.class, args);
    }

}
