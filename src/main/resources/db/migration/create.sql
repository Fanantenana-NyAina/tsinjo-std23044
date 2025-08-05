-- Table Donor (Donateur)
CREATE TABLE donor (
                       id VARCHAR(255) PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL
);

-- Table Payment (Paiement)
CREATE TABLE payment (
                         id VARCHAR(255) PRIMARY KEY,
                         amount DECIMAL(10, 2) NOT NULL,
                         payment_method VARCHAR(50) NOT NULL,
                         status VARCHAR(20) NOT NULL CHECK (status IN ('VERIFYING', 'SUCCEEDED', 'FAILED')),
                         vola_reference VARCHAR(255),
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Table Beneficiary (Bénéficiaire)
CREATE TABLE beneficiary (
                             id VARCHAR(255) PRIMARY KEY,
                             email VARCHAR(255) NOT NULL UNIQUE,
                             full_name VARCHAR(255) NOT NULL
);

-- Table Donation (Don)
CREATE TABLE donation (
                          id VARCHAR(255) PRIMARY KEY,
                          payment_id VARCHAR(255) NOT NULL,
                          donor_id VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (payment_id) REFERENCES payment(id),
                          FOREIGN KEY (donor_id) REFERENCES donor(id)
);

-- Table Help (Aide)
CREATE TABLE help (
                      id VARCHAR(255) PRIMARY KEY,
                      payment_id VARCHAR(255) NOT NULL,
                      beneficiary_id VARCHAR(255) NOT NULL,
                      accident_description TEXT NOT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      FOREIGN KEY (payment_id) REFERENCES payment(id),
                      FOREIGN KEY (beneficiary_id) REFERENCES beneficiary(id)
);