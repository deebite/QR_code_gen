package com.check.QRCodeGen.controller;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.check.QRCodeGen.service.QRCodeDetailsService;
import com.check.QRCodeGen.service.impl.QRCodeqDetailsServiceImpl;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CommonController {

    @Autowired
    private QRCodeDetailsService qrCodeDetailsService;


    @PostMapping("/saveDetails")
    public QRCodeDetails saveData(@RequestBody QRCodeDetails qrCodeDetails) throws WriterException {
        return qrCodeDetailsService.saveCustomerDetails(qrCodeDetails);
    }


    @GetMapping(value = "qrGenerator/{customer_number}", produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage barbecueEAN13Barcode(@PathVariable("customer_number") String barcode) throws WriterException, IOException {
        return qrCodeDetailsService.qrGenerator(barcode);
    }


}
