package board.view.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article_view_count")
@Entity
public class ArticleViewCount {

    @Id
    private Long articleId; // Shard Key
    private Long viewCount;

    private ArticleViewCount(Long articleId, Long viewCount) {
        this.articleId = articleId;
        this.viewCount = viewCount;
    }

    public static ArticleViewCount init(Long articleId, Long viewCount) {
        return new ArticleViewCount(articleId, viewCount);
    }
}
