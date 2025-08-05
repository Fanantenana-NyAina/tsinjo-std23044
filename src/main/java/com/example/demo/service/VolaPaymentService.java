package com.example.demo.service;

import com.example.demo.integration.vola.VolaPaymentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@AllArgsConstructor
public class VolaPaymentService {
    private final RestTemplate restTemplate;

    private final String volaApiUrl = System.getenv("VOLA_API_URL");;
    private final String apiKey = System.getenv("VOLA_API_KEY");;

    private VolaPaymentResponse createVolaPayment(String payerEmail, String pspPaymentId, String pspType) {
        String url = UriComponentsBuilder.fromHttpUrl(volaApiUrl + "/payment")
                .queryParam("apiKey", apiKey)
                .queryParam("payerEmail", payerEmail)
                .queryParam("pspType", pspType)
                .queryParam("pspPaymentId", pspPaymentId)
                .toUriString();

        return restTemplate.postForObject(url, null, VolaPaymentResponse.class);
    }

    private VolaPaymentResponse getPaymentStatus(String pspPaymentId, String payerEmail) {
        String url = UriComponentsBuilder.fromHttpUrl(volaApiUrl + "/payment")
                .queryParam("apiKey", apiKey)
                .queryParam("payerEmail", payerEmail)
                .queryParam("pspType", "ORANGE_MONEY")
                .queryParam("pspPaymentId", pspPaymentId)
                .toUriString();

        return restTemplate.getForObject(url, VolaPaymentResponse.class);
    }
}
