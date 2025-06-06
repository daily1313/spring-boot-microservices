package board.like.repository;

import board.like.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {

    Optional<ArticleLike> findByArticleIdAndUserId(Long articleId, Long userId);

    @Modifying
    @Query("delete from ArticleLike al where al.articleId = :articleId and al.userId = :userId")
    int deleteIfExistsByArticleIdAndUserId(@Param("articleId") Long articleId, @Param("userId") Long userId);
}
