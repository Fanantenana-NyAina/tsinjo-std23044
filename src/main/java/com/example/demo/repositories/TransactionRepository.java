package com.example.demo.repositories;

import com.example.demo.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class TransactionRepository {
  private final DBConnection connection;

  // Méthodes pour Donor
  public Donor saveDonor(Donor donor) throws SQLException {
    String sql = "INSERT INTO donor (email, full_name) VALUES (?, ?) RETURNING id";
    try (PreparedStatement pstmt = connection.getConnection().prepareStatement(sql)) {
      pstmt.setString(1, donor.getEmail());
      pstmt.setString(2, donor.getFullName());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        donor.setId(rs.getInt("id"));
      }
      return donor;
    }
  }

  // Méthodes pour Payment
  public Payment savePayment(Payment payment) throws SQLException {
    String sql =
        "INSERT INTO payment (payement_date, amount, payment_methodes, payment_status) "
            + "VALUES (?, ?, ?, ?) RETURNING id";
    try (PreparedStatement pstmt = connection.getConnection().prepareStatement(sql)) {
      pstmt.setTimestamp(1, Timestamp.valueOf(payment.getPayementDate()));
      pstmt.setDouble(2, payment.getAmount());
      pstmt.setString(3, payment.getPaymentMethodes().name());
      pstmt.setString(4, payment.getPaymentStatus().name());

      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        payment.setId(rs.getInt("id"));
      }
      return payment;
    }
  }

  // Méthodes pour Donation
  public Donation saveDonation(Donation donation) throws SQLException {
    String sql = "INSERT INTO donation (donor_id, payment_id) VALUES (?, ?) RETURNING id";
    try (PreparedStatement pstmt = connection.getConnection().prepareStatement(sql)) {
      pstmt.setInt(1, donation.getDonor().getId());
      pstmt.setInt(2, donation.getPayment().getId());

      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        donation.setId(rs.getInt("id"));
      }
      return donation;
    }
  }

  // Méthodes pour Help
  public Help saveHelp(Help help) throws SQLException {
    String sql =
        "INSERT INTO help (beneficiary_id, payment_id, accident_description) "
            + "VALUES (?, ?, ?) RETURNING id";
    try (PreparedStatement pstmt = connection.getConnection().prepareStatement(sql)) {
      pstmt.setInt(1, help.getBeneficiary().getId());
      pstmt.setInt(2, help.getPayment().getId());
      pstmt.setString(3, help.getAccidentDescription());

      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        help.setId(rs.getInt("id"));
      }
      return help;
    }
  }

  // Récupération pour l'interface
  public List<Donation> getAllDonations() throws SQLException {
    List<Donation> donations = new ArrayList<>();
    String sql =
        "SELECT d.id, d.donor_id, d.payment_id, "
            + "dn.email as donor_email, dn.full_name as donor_name, "
            + "p.payement_date, p.amount, p.payment_methodes, p.payment_status "
            + "FROM donation d "
            + "JOIN donor dn ON d.donor_id = dn.id "
            + "JOIN payment p ON d.payment_id = p.id "
            + "ORDER BY p.payement_date DESC";

    try (Statement stmt = connection.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Donor donor =
            Donor.builder()
                .id(rs.getInt("donor_id"))
                .email(rs.getString("donor_email"))
                .fullName(rs.getString("donor_name"))
                .build();

        Payment payment =
            Payment.builder()
                .id(rs.getInt("payment_id"))
                .payementDate(rs.getTimestamp("payement_date").toLocalDateTime())
                .amount(rs.getDouble("amount"))
                .paymentMethodes(PaymentMethodes.valueOf(rs.getString("payment_methodes")))
                .paymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")))
                .build();

        donations.add(Donation.builder().id(rs.getInt("id")).donor(donor).payment(payment).build());
      }
    }
    return donations;
  }

  public List<Help> getAllHelps() throws SQLException {
    List<Help> helps = new ArrayList<>();
    String sql =
        "SELECT h.id, h.beneficiary_id, h.payment_id, h.accident_description, "
            + "dn.email as beneficiary_email, dn.full_name as beneficiary_name, "
            + "p.payement_date, p.amount, p.payment_methodes, p.payment_status "
            + "FROM help h "
            + "JOIN donor dn ON h.beneficiary_id = dn.id "
            + "JOIN payment p ON h.payment_id = p.id "
            + "ORDER BY p.payement_date DESC";

    try (Statement stmt = connection.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        Donor beneficiary =
            Donor.builder()
                .id(rs.getInt("beneficiary_id"))
                .email(rs.getString("beneficiary_email"))
                .fullName(rs.getString("beneficiary_name"))
                .build();

        Payment payment =
            Payment.builder()
                .id(rs.getInt("payment_id"))
                .payementDate(rs.getTimestamp("payement_date").toLocalDateTime())
                .amount(rs.getDouble("amount"))
                .paymentMethodes(PaymentMethodes.valueOf(rs.getString("payment_methodes")))
                .paymentStatus(PaymentStatus.valueOf(rs.getString("payment_status")))
                .build();

        helps.add(
            Help.builder()
                .id(rs.getInt("id"))
                .beneficiary(beneficiary)
                .payment(payment)
                .accidentDescription(rs.getString("accident_description"))
                .build());
      }
    }
    return helps;
  }
}
