package com.example.packer.service.impl;

import com.example.packer.domain.Package;
import com.example.packer.repository.PackageRepository;
import com.example.packer.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PackageServiceImpl implements PackageService {

    private final PackageRepository packageRepository;

    @Override
    public void save(Package pack) {
        packageRepository.save(pack);
    }
}
