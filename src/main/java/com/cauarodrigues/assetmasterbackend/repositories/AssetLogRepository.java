package com.cauarodrigues.assetmasterbackend.repositories;

import com.cauarodrigues.assetmasterbackend.entities.AssetLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AssetLogRepository extends JpaRepository<AssetLog, UUID> {
}