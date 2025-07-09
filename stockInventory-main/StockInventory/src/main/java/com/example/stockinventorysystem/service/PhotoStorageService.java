package com.example.stockinventorysystem.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Color;
import java.awt.Font;

@Service
public class PhotoStorageService {
    
    @Value("${app.photo.max-size:1048576}") // 1MB default
    private long maxPhotoSize;
    
    public byte[] processAndStorePhoto(MultipartFile file, LocalDateTime timestamp) throws IOException {
        validatePhoto(file);
        
        // Read the image
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        
        // Resize if too large (max width/height of 1024px)
        BufferedImage processedImage = resizeIfNeeded(originalImage);
        
        // Add timestamp to the image
        processedImage = addTimestampToImage(processedImage, timestamp);
        
        // Convert back to bytes
        return convertToBytes(processedImage);
    }
    
    private void validatePhoto(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Photo file is empty");
        }
        
        if (file.getSize() > maxPhotoSize) {
            throw new IllegalArgumentException("Photo file size exceeds maximum allowed size");
        }
        
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("File must be an image");
        }
    }
    
    private BufferedImage resizeIfNeeded(BufferedImage image) {
        int maxDimension = 1024;
        
        if (image.getWidth() > maxDimension || image.getHeight() > maxDimension) {
            return Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH,
                              maxDimension, maxDimension, Scalr.OP_ANTIALIAS);
        }
        
        return image;
    }
    
    private BufferedImage addTimestampToImage(BufferedImage image, LocalDateTime timestamp) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Create a new image with space for the timestamp
        BufferedImage newImage = new BufferedImage(width, height + 30, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImage.createGraphics();
        
        // Draw the original image
        g2d.drawImage(image, 0, 0, null);
        
        // Set up the timestamp text
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, height, width, 30);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Format and draw the timestamp
        String timestampText = timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        g2d.drawString(timestampText, 10, height + 20);
        
        g2d.dispose();
        return newImage;
    }
    
    private byte[] convertToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", baos);
        return baos.toByteArray();
    }
    
    public String generatePhotoFileName(String empId) {
        return empId + "_" + UUID.randomUUID().toString() + ".jpg";
    }
}
