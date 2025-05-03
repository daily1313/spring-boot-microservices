package board.comment.entity;

import board.common.auditing.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "comment")
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends AbstractEntity {

    @Id
    private Long commentId;

    private String content;

    private Long parentCommentId;

    private Long articleId; // Shard Key

    private Long writerId;

    private Boolean deleted;

    private Comment(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId, boolean deleted) {
        this.commentId = commentId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.deleted = deleted;
    }

    public static Comment create(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId) {
        return new Comment(commentId, content, parentCommentId == null ? commentId : parentCommentId, articleId, writerId, false);
    }

    public boolean isRoot() {
        return parentCommentId.longValue() == commentId;
    }

    public void delete() {
        deleted = true;
    }
}
