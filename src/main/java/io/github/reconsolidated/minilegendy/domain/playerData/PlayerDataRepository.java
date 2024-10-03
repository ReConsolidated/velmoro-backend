package io.github.reconsolidated.minilegendy.domain.playerData;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDataRepository extends JpaRepository<PlayerData, Long> {

    @Query("SELECT new io.github.reconsolidated.minilegendy.domain.playerData.PlayerRankingDto(p.id, p.playerName, p.bestLegendPoints, p.bestLegendName) " +
            "FROM PlayerData p ORDER BY p.bestLegendPoints DESC")
    Page<PlayerRankingDto> findPlayerRanking(Pageable pageable);

    @Query(value = "SELECT rank FROM (" +
            "   SELECT p.id, ROW_NUMBER() OVER (ORDER BY p.best_legend_points DESC) as rank " +
            "   FROM player_data p" +
            ") as ranking " +
            "WHERE ranking.id = :playerId",
            nativeQuery = true)
    int getPlayerRank(@Param("playerId") Long playerId);
}
