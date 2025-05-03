package board.article.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "board_article_count")
@Entity
public class BoardArticleCount {

    @Id
    private Long boardId;

    private Long articleCount;

    public static BoardArticleCount init(Long boardId, Long articleCount) {
        return new BoardArticleCount(boardId, articleCount);
    }
}
