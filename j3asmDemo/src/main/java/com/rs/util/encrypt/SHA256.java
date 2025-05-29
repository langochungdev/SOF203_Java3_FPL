/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rs.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author ndhlt
 */
public class SHA256 {
    private static final int SALT_LENGTH = 16;
    private static final String HASH_ALGORITHM = "SHA-256";

    public static String hashPassword(String password) {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashWithSalt(password.toCharArray(), salt);

        // Lưu trữ salt và hashed password
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
    }
    
    public static String hashPassword(String password, byte[] salt) {
        byte[] hashedPassword = hashWithSalt(password.toCharArray(), salt);

        // Lưu trữ salt và hashed password
        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
    }
    
    public static byte[] getSalt(String hashedPass){
        String[] splited = hashedPass.split(":");
        return Base64.getDecoder().decode(splited[0]);
    }

    private static byte[] hashWithSalt(char[] password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            digest.update(salt);
            return digest.digest(new String(password).getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Failed to get MessageDigest instance for " + HASH_ALGORITHM, e);
        }
    }

    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return salt;
    }
}
