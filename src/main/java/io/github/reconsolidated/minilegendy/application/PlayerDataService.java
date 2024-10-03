package io.github.reconsolidated.minilegendy.application;

import io.github.reconsolidated.minilegendy.domain.playerData.PlayerData;
import io.github.reconsolidated.minilegendy.domain.playerData.PlayerDataRepository;
import io.github.reconsolidated.minilegendy.domain.playerData.PlayerRankingDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerDataService {
    private final PlayerDataRepository playerDataRepository;

    @Transactional
    public PlayerData savePlayerData(PlayerData playerData) {
        PlayerData savedPlayerData = playerDataRepository.save(playerData);
        return playerDataRepository.findById(savedPlayerData.getId()).orElseThrow();
    }


    // Read
    public Optional<PlayerData> getPlayerData(Long id) {
        return playerDataRepository.findById(id);
    }

    public Page<PlayerRankingDto> getPlayerRanking(Pageable pageable) {
        return playerDataRepository.findPlayerRanking(pageable);
    }

    public Page<PlayerRankingDto> getPlayerRankingPage(Long playerId) {
        int playerRank = getPlayerRank(playerId);
        Pageable pageable = PageRequest.of(playerRank/10, 10);
        return getPlayerRanking(pageable);
    }

    public int getPlayerRank(Long playerId) {
        return playerDataRepository.getPlayerRank(playerId);
    }

}
