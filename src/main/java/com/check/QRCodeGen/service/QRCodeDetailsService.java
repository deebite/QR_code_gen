package com.check.QRCodeGen.service;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface QRCodeDetailsService {

    ResponseEntity<InputStreamResource> saveCustomerDetails(QRCodeDetails qrCodeDetails) throws DocumentException, IOException, WriterException;

    public void printAspect();
}
