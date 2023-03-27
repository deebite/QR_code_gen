package com.check.QRCodeGen.controller;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.check.QRCodeGen.service.QRCodeDetailsService;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CommonController {

    @Autowired
    private QRCodeDetailsService qrCodeDetailsService;


    @PostMapping(value = "/saveDetails",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> saveData(@RequestBody QRCodeDetails qrCodeDetails) throws WriterException, DocumentException, IOException {
        return qrCodeDetailsService.saveCustomerDetails(qrCodeDetails);
    }

}
