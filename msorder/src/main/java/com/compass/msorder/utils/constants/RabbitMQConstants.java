package com.compass.msorder.utils.constants;

public class RabbitMQConstants {

    public static final String ORDER_TO_AUDIT_QUEUE_NAME = "order.to.audit.queue";
    public static final String ORDER_TO_CATALOG_QUEUE_NAME = "order.to.catalog.queue";
    public static final String ORDER_TO_PAYMENT_QUEUE_NAME = "order.to.payment.queue";
    public static final String PAYMENT_TO_ORDER_QUEUE_NAME = "payment.to.order.queue";
    public static final String EXCHANGE_NAME = "exchange";
    public static final String ORDER_TO_AUDIT_ROUTINGKEY_NAME = "order.to.audit.routingkey";
    public static final String ORDER_TO_CATALOG_ROUTINGKEY_NAME = "order.to.catalog.routingkey";
    public static final String ORDER_TO_PAYMENT_ROUTINGKEY_NAME = "order.to.payment.routingkey";
    public static final String PAYMENT_TO_ORDER_ROUTINGKEY_NAME = "payment.to.order.routingkey";

}
