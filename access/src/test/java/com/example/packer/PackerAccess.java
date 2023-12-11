package com.example.packer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.packer")
@EntityScan("com.example.packer")
public class PackerAccess {

    public static void main(String[] args) {
        SpringApplication.run(PackerAccess.class, args);
    }
}
