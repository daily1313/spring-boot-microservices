package board.comment.entity;

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
@Table(name = "article_comment_count")
@Entity
public class ArticleCommentCount {

    @Id
    private Long articleId; // Shard Key

    private Long commentCount;

    private ArticleCommentCount(Long articleId, Long commentCount) {
        this.articleId = articleId;
        this.commentCount = commentCount;
    }

    public static ArticleCommentCount init(Long articleId, Long commentCount) {
        return new ArticleCommentCount(articleId, commentCount);
    }
}
