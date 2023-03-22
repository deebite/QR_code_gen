package com.check.QRCodeGen.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.io.InputStreamResource;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeDetails {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "rate_id", columnDefinition = "binary(16)")
    private UUID code_id;

    private String customer_name;
    private String number;

    private String customer_address;
    private String customer_bank;
    private String customer_bank_code;

    @Lob
    private byte[] qrCodes;
}
