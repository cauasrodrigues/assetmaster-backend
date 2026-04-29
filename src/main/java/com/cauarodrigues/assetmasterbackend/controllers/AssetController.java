package com.cauarodrigues.assetmasterbackend.controllers;

import com.cauarodrigues.assetmasterbackend.entities.Asset;
import com.cauarodrigues.assetmasterbackend.entities.AssetLog;
import com.cauarodrigues.assetmasterbackend.repositories.AssetLogRepository;
import com.cauarodrigues.assetmasterbackend.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetLogRepository logRepository;

    @Autowired
    private AssetRepository repository;

    @GetMapping
    public List<Asset> getAllAssets() {
        return repository.findAll();
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return repository.save(asset);
    }

    // Deletar um ativo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Atualizar um ativo (mudar dono ou status)
    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable UUID id, @RequestBody Asset details) {
        return repository.findById(id).map(asset -> {
            asset.setName(details.getName());
            asset.setCurrentOwner(details.getCurrentOwner());
            asset.setStatus(details.getStatus());
            Asset updated = repository.save(asset);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/logs")
    public ResponseEntity<AssetLog> addLogToAsset(@PathVariable UUID id, @RequestBody AssetLog log) {
        return repository.findById(id).map(asset -> {
            log.setAsset(asset);
            AssetLog savedLog = logRepository.save(log);
            return ResponseEntity.ok(savedLog);
        }).orElse(ResponseEntity.notFound().build());
    }
}