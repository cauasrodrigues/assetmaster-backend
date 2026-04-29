package com.cauarodrigues.assetmasterbackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "asset_logs")
@Getter
@Setter
public class AssetLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String progressName;
    private Double cpuUsage;
    private String classification;
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonBackReference // O "Filho" não tenta exibir o pai de volta
    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
}
