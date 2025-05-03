package board.article.entity;

import board.common.auditing.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "article")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article extends AbstractEntity {

    @Id
    private Long articleId;
    private String title;
    private String content;
    private Long boardId;
    private Long writerId;

    private Article(Long articleId, String title, String content, Long writerId, Long boardId) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.writerId = writerId;
    }

    public static Article create(Long articleId, String title, String content, Long writerId, Long boardId) {
        return new Article(articleId, title, content, writerId, boardId);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
