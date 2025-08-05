package com.example.demo.endpoint;

import com.example.demo.model.*;
import com.example.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@AllArgsConstructor
public class TransactionController {
  private final TransactionService service;

  @GetMapping("/")
  public String showAllTransactions(Model model) {
    try {
      model.addAttribute("donations", service.getAllDonations());
      model.addAttribute("helps", service.getAllHelps());
      model.addAttribute("paymentMethods", PaymentMethodes.values());
    } catch (SQLException e) {
      throw new RuntimeException("Database error", e);
    }
    return "index";
  }

  @PostMapping("/donate")
  public String processDonation(
          @RequestParam String email,
          @RequestParam String fullName,
          @RequestParam double amount,
          @RequestParam PaymentMethodes paymentMethod) {

    try {
      service.processDonation(email, fullName, amount, paymentMethod);
    } catch (SQLException e) {
      throw new RuntimeException("Failed to process donation", e);
    }
    return "redirect:/";
  }
}
