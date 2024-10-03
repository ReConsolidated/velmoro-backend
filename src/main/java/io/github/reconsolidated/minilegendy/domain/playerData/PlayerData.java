package io.github.reconsolidated.minilegendy.domain.playerData;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.reconsolidated.minilegendy.application.JsonNodeConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Data
@Table(name = "player_data")
public class PlayerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String playerName;

    private Double bestLegendPoints = 0.0;

    private String bestLegendName = "";

    @Column
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> playerData;  // Store JSON as String in the database

}
