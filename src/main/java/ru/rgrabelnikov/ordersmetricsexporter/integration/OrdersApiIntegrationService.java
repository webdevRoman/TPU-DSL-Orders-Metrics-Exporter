package ru.rgrabelnikov.ordersmetricsexporter.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.rgrabelnikov.ordersmetricsexporter.dto.OrderStatsRs;
import ru.rgrabelnikov.ordersmetricsexporter.integration.properties.OrdersApiIntegrationProperties;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersApiIntegrationService {

    private final OrdersApiIntegrationProperties props;

    private final RestTemplate restTemplate = new RestTemplate();

    public OrderStatsRs getOrderStats() {
        log.info("Getting order stats from Orders-Api");
        return restTemplate.getForObject(props.statsUrl(), OrderStatsRs.class);
    }
}
