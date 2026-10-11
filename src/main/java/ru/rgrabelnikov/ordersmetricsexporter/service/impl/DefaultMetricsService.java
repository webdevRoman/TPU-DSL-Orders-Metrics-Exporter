package ru.rgrabelnikov.ordersmetricsexporter.service.impl;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;
import ru.rgrabelnikov.ordersmetricsexporter.integration.OrdersApiIntegrationService;
import ru.rgrabelnikov.ordersmetricsexporter.service.MetricsService;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class DefaultMetricsService implements MetricsService {

    private final MeterRegistry meterRegistry;

    private final OrdersApiIntegrationService service;

    public DefaultMetricsService(final MeterRegistry meterRegistry, final OrdersApiIntegrationService service) {
        this.meterRegistry = meterRegistry;
        this.service = service;

        Gauge.builder("shop.stats.orders.new", () -> this.service.getOrderStats().ordersNew())
                .description("Сколько заказов ждут обработки")
                .register(meterRegistry);
        Gauge.builder("shop.stats.orders.processing", () -> this.service.getOrderStats().ordersProcessing())
                .description("Сколько заказов сейчас обрабатываются")
                .register(meterRegistry);
        Gauge.builder("shop.stats.orders.done", () -> this.service.getOrderStats().ordersDone())
                .description("Сколько заказов уже завершено")
                .register(meterRegistry);
        Gauge.builder("shop.stats.seconds.since.last.processed", () -> {
                    OffsetDateTime lastProcessedAt = this.service.getOrderStats().lastProcessedAt();
                    if (lastProcessedAt == null) {
                        lastProcessedAt = OffsetDateTime.MIN;
                    }
                    return ChronoUnit.SECONDS.between(lastProcessedAt, OffsetDateTime.now());
                })
                .description("Сколько секунд прошло с последнего обработанного заказа")
                .register(meterRegistry);
    }
}
