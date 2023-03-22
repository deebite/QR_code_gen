package com.check.QRCodeGen.repositories;

import com.check.QRCodeGen.entities.QRCodeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QRCodeDetailsRepository extends JpaRepository<QRCodeDetails, UUID> {

    QRCodeDetails findByNumber(String str);
}
