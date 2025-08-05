package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final Connection connection;
    private final VolaPaymentService volaService;

    @Transactional
    public Donation processDonation(String email, String fullName, double amount, PaymentMethodes method) throws SQLException {
        Donor donor = repository.saveDonor(Donor.builder()
                .email(email)
                .fullName(fullName)
                .build());

        Payment payment = repository.savePayment(Payment.builder()
                .payementDate(LocalDateTime.now())
                .amount(amount)
                .paymentMethodes(method)
                .paymentStatus(PaymentStatus.VERIFYING)
                .build());

        Donation donation = repository.saveDonation(Donation.builder()
                .donor(donor)
                .payment(payment)
                .build());

        // Vérification asynchrone avec Vola
        volaService.verifyPaymentAsync(payment.getId(), email, method.name());

        return donation;
    }

    public List<Donation> getAllDonations() throws SQLException {
        return repository.getAllDonations();
    }

    public List<Help> getAllHelps() throws SQLException {
        return repository.getAllHelps();
    }
}
