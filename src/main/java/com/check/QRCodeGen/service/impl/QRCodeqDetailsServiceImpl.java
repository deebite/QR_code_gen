package com.check.QRCodeGen.service.impl;

import com.check.QRCodeGen.entities.QRCodeDetails;
import com.check.QRCodeGen.repositories.QRCodeDetailsRepository;
import com.check.QRCodeGen.service.QRCodeDetailsService;
import com.check.QRCodeGen.uility.PDFGenerator;
import com.check.QRCodeGen.uility.PostManImagePrinter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class QRCodeqDetailsServiceImpl implements QRCodeDetailsService {

    @Autowired
    private QRCodeDetailsRepository qrCodeDetailsRepository;
    @Autowired
    private PDFGenerator pdfGenerator;
    @Value("${localpath.config}")
    private String localpath;

    InputStreamResource inputStreamResource;

    @Override
    public ResponseEntity<InputStreamResource> saveCustomerDetails(QRCodeDetails qrCodeDetails) throws DocumentException, IOException, WriterException {
        QRCodeDetails customerDetails = new QRCodeDetails();
        customerDetails.setCustomer_name(qrCodeDetails.getCustomer_name());
        customerDetails.setNumber(qrCodeDetails.getNumber());
        customerDetails.setCustomer_bank(qrCodeDetails.getCustomer_bank());
        customerDetails.setCustomer_address(qrCodeDetails.getCustomer_address());
        customerDetails.setCustomer_bank_code(qrCodeDetails.getCustomer_bank_code());
        qrCodeDetailsRepository.save(qrCodeDetails);
        log.info("Customer Details saved Successfully!");
        return ResponseEntity.ok().body(qrGenerator(customerDetails));
    }

    @Override
    public void printAspect() {
        System.out.println("Hello To Every one I am QR Code Generator");
    }


    public InputStreamResource qrGenerator(QRCodeDetails customerDetails) throws WriterException, IOException, DocumentException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(String.valueOf(customerDetails), BarcodeFormat.QR_CODE, 250, 250);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageConfig con = new MatrixToImageConfig(0xFF000002, 0xFFFFC041);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
        byte[] pngData = pngOutputStream.toByteArray();

        File directoryPath = new File(localpath + customerDetails.getNumber() + ".png");
        log.info("File saved at the local directory successfully in PNG format!");
        FileOutputStream fileOutputStream = new FileOutputStream(directoryPath);
        fileOutputStream.write(pngData);
        QRCodeDetails qrCodeDetails = qrCodeDetailsRepository.findByNumber(customerDetails.getNumber());
        qrCodeDetails.setQrCodes(pngOutputStream.toByteArray());
        qrCodeDetailsRepository.save(qrCodeDetails);
        log.info("File saved in database successfully!");
        fileOutputStream.close();

        inputStreamResource = pdfGenerator.InputStreamResource(pngData);
        return inputStreamResource;
    }


}

