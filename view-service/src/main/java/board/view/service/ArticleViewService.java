package board.view.service;

import board.view.repository.ArticleViewCountRepository;
import board.view.repository.ArticleViewDistributedLockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class ArticleViewService {

    private final ArticleViewDistributedLockRepository articleViewDistributedLockRepository;
    private final ArticleViewCountRepository articleViewCountRepository;
    private final ArticleViewCountBackupProcessor articleViewCountBackupProcessor;

    private static final int BACK_UP_BATCH_SIZE = 100;
    private static final Duration ttl = Duration.ofMinutes(10);

    public Long increase(Long articleId, Long userId) {
        if(!articleViewDistributedLockRepository.lock(articleId, userId, ttl)) {
            return articleViewCountRepository.read(articleId);
        }

        Long count = articleViewCountRepository.increase(articleId);

        if(count % BACK_UP_BATCH_SIZE == 0) {
            articleViewCountBackupProcessor.backup(articleId, count);
        }

        return count;
    }

    public Long count(Long articleId) {
        return articleViewCountRepository.read(articleId);
    }
}
