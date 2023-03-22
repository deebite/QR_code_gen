package com.check.QRCodeGen.service.impl;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.check.QRCodeGen.repositories.QRCodeDetailsRepository;
import com.check.QRCodeGen.service.QRCodeDetailsService;
import com.check.QRCodeGen.uility.PostManImagePrinter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRCodeqDetailsServiceImpl implements QRCodeDetailsService {

    @Autowired
    private QRCodeDetailsRepository qrCodeDetailsRepository;

    @Autowired
    private PostManImagePrinter postManImagePrinter;

    public  static String die = "PDF417_DIMENSIONS";

    public static  String localPath = "C:\\Users\\amardeep.lokare\\Downloads\\QRCodeGen";

    @Override
    public QRCodeDetails saveCustomerDetails(QRCodeDetails qrCodeDetails) {
        QRCodeDetails customerDetails = new QRCodeDetails();
        customerDetails.setCustomer_name(qrCodeDetails.getCustomer_name());
        customerDetails.setNumber(qrCodeDetails.getNumber());
        customerDetails.setCustomer_bank(qrCodeDetails.getCustomer_bank());
        customerDetails.setCustomer_address(qrCodeDetails.getCustomer_address());
        customerDetails.setCustomer_bank_code(qrCodeDetails.getCustomer_bank_code());
        qrCodeDetailsRepository.save(qrCodeDetails);
        return qrCodeDetails;
    }

    @Override
    public BufferedImage qrGenerator(String str) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        byte[] pngData = pngOutputStream.toByteArray();

        QRCodeDetails qrCodeDetails = qrCodeDetailsRepository.findByNumber(str);

        qrCodeDetails.setQrCodes(pngData.toString());

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


}

