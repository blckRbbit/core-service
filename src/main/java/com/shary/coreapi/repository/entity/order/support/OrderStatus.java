package com.shary.coreapi.repository.entity.order.support;

public enum OrderStatus {
    NONE("none"),
    HIRED("hired"),
    HANDED_OVER_TO_COURIER("handed over to courier"),
    IN_TRANSIT("in transit"),
    RECEIVED_BY_CUSTOMER("received by customer"),
    RETURNED_TO_THE_COURIER("returned to the courier"),
    RETURNED_TO_THE_COURIER_AND_PAYED("returned to the courier and payed"),
    RETURNED_TO_THE_COURIER_AND_NOT_PAYED("returned to the courier and not payed"),
    RETURNED_TO_WAREHOUSE("returned to warehouse"),
    RETURNED("returned"),
    OPEN_DISPUTE("open dispute"),
    DISPUTE_ENDED("dispute ended"),
    CLOSED("closed");


    /**
     * Description value of the order statuses
     */
    final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
