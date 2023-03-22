package com.check.QRCodeGen.service;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface QRCodeDetailsService {

    QRCodeDetails saveCustomerDetails(QRCodeDetails qrCodeDetails);

    ResponseEntity<InputStreamResource> qrGenerator(String str) throws WriterException, IOException, DocumentException;


}
