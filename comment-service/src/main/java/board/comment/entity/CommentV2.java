package board.comment.entity;

import board.common.auditing.AbstractEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment_v2")
@Entity
public class CommentV2 extends AbstractEntity {

    @Id
    private Long commentId;

    private String content;

    private Long articleId; // Shard Key

    private Long writerId;

    @Embedded
    private CommentPath commentPath;

    private Boolean deleted;

    private CommentV2(Long commentId, String content, Long articleId, Long writerId, CommentPath commentPath, boolean deleted) {
        this.commentId = commentId;
        this.content = content;
        this.articleId = articleId;
        this.writerId = writerId;
        this.commentPath = commentPath;
        this.deleted = deleted;
    }

    public static CommentV2 create(Long commentId, String content, Long articleId, Long writerId, CommentPath commentPath) {
        return new CommentV2(commentId, content, articleId, writerId, commentPath, false);
    }

    public boolean isRoot() {
        return commentPath.isRoot();
    }

    public void delete() {
        deleted = true;
    }
}
