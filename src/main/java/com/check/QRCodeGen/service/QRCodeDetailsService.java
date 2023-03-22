package com.check.QRCodeGen.service;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.google.zxing.WriterException;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface QRCodeDetailsService {

    QRCodeDetails saveCustomerDetails(QRCodeDetails qrCodeDetails);

    BufferedImage qrGenerator(String str) throws WriterException, IOException;


}
