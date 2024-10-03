package io.github.reconsolidated.minilegendy.infrastracture.rest;

import io.github.reconsolidated.minilegendy.application.PlayerDataService;
import io.github.reconsolidated.minilegendy.domain.playerData.PlayerRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ranking")
public class RankingController {
    private final PlayerDataService playerDataService;


    @GetMapping
    public Page<PlayerRankingDto> getRanking(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return playerDataService.getPlayerRanking(PageRequest.of(page, size));
    }

    @GetMapping("/{playerId}/rankNumber")
    public ResponseEntity<Integer> getPlayerRank(@PathVariable Long playerId) {
        int rank = playerDataService.getPlayerRank(playerId);
        return ResponseEntity.ok(rank);
    }

    @GetMapping("/{playerId}")
    public Page<PlayerRankingDto> getRankingPageOfPlayer(@PathVariable Long playerId) {
        return playerDataService.getPlayerRankingPage(playerId);
    }

}
