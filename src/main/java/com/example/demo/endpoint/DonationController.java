package com.example.demo.endpoint;

import com.example.demo.model.Donor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DonationController {
    @GetMapping("/donors")
    public List<Donor> getDonors() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
