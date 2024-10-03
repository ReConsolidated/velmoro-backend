package io.github.reconsolidated.minilegendy.domain.playerData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerRankingDto {
    private Long id;
    private String playerName;
    private Double bestLegendPoints;
    private String bestLegendName;
}
