package com.ludistudy.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "fraud",
        path = "api/v1/fraud-check"
)
public interface FraudClient {
    // INSTEAD OF USING REST TEMPLATE ALL CLIENTS USING THIS INTERFACE to call api
    @GetMapping("{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
