package com.ludistudy.customer;

import com.ludistudy.clients.fraud.FraudCheckResponse;
import com.ludistudy.clients.fraud.FraudClient;
import com.ludistudy.clients.notification.NotificationClient;
import com.ludistudy.clients.notification.NotificationRequest;
import com.ludistudy.clients.notification.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        // todo: store customer in db
        customerRepository.saveAndFlush(customer);

        // todo: check if fraud
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );


        //after enable the open feign
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster is not supported");
        }

        //todo: send notification add it to message queue
        NotificationRequest notificationRequest = NotificationRequest.builder()
                .toCustomerId(customer.getId())
                .toCustomerEmail(customer.getEmail())
                .message(String.format("Hi %s, welcome to Microservice World...",
                        customer.getFirstName()))
                .build();
        NotificationResponse notificationResponse = notificationClient.sendNotification(notificationRequest);

        log.info("Notification Response: {}", notificationResponse);

    }
}
