package io.github.reconsolidated.minilegendy.infrastracture.rest;

import io.github.reconsolidated.minilegendy.application.PlayerDataService;
import io.github.reconsolidated.minilegendy.domain.playerData.PlayerData;
import io.github.reconsolidated.minilegendy.domain.playerData.PlayerRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/player-data")
public class PlayerDataController {

    private final PlayerDataService playerDataService;

    @PostMapping
    public ResponseEntity<?> savePlayerData(@RequestBody PlayerData playerData) {
        try {
            PlayerData created = playerDataService.savePlayerData(playerData);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // Read
    @GetMapping("/{id}")
    public ResponseEntity<PlayerData> getPlayerData(@PathVariable Long id) {
        Optional<PlayerData> playerData = playerDataService.getPlayerData(id);
        return playerData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

