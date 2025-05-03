package board.article.service.response;

import board.article.entity.Article;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class ArticleResponse {

    private Long articleId;
    private String title;
    private String content;
    private Long boardId;
    private Long writerId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getContent(),
                article.getBoardId(),
                article.getWriterId(),
                article.getCreatedAt(),
                article.getModifiedAt()
        );
    }
}
