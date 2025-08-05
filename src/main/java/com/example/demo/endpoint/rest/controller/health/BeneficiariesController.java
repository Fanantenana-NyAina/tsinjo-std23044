package com.example.demo.endpoint.rest.controller.health;

import com.example.demo.model.Beneficiary;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeneficiariesController {
  @GetMapping("/beneficiaries")
  public List<Beneficiary> getBeneficiaries() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
