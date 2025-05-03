package board.comment.service.response;

import board.comment.entity.Comment;
import board.comment.entity.CommentV2;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class CommentResponse {

    private Long commentId;

    private String content;

    private Long parentCommentId;

    private Long articleId;

    private Long writerId;

    private boolean deleted;

    private LocalDateTime createdAt;

    private String path;

    private CommentResponse(Long commentId, String content, Long parentCommentId, Long articleId, Long writerId, Boolean deleted, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.parentCommentId = parentCommentId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }

    private CommentResponse(Long commentId, String content, String path, Long articleId, Long writerId, Boolean deleted, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.content = content;
        this.path = path;
        this.articleId = articleId;
        this.writerId = writerId;
        this.deleted = deleted;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getParentCommentId(),
                comment.getArticleId(),
                comment.getWriterId(),
                comment.getDeleted(),
                comment.getCreatedAt()
        );
    }

    public static CommentResponse from(CommentV2 comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getContent(),
                comment.getCommentPath().getPath(),
                comment.getArticleId(),
                comment.getWriterId(),
                comment.getDeleted(),
                comment.getCreatedAt()
        );
    }
}
