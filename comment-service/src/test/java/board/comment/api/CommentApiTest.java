package board.comment.api;

import board.comment.service.request.CommentCreateRequest;
import board.comment.service.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class CommentApiTest {

    RestClient restClient = RestClient.create("http://localhost:9001");

    @Test
    void create() {
        CommentResponse response1 = createComment(new CommentCreateRequest(1L, "my comment1", null, 1L));
        CommentResponse response2 = createComment(new CommentCreateRequest(1L, "my comment2", response1.getCommentId(), 1L));
        CommentResponse response3 = createComment(new CommentCreateRequest(1L, "my comment3", response1.getCommentId(), 1L));

        System.out.println("commentId=%s".formatted(response1.getCommentId()));
        System.out.println("\t commentId=%s".formatted(response2.getCommentId()));
        System.out.println("\t commentId=%s".formatted(response3.getCommentId()));

//        commentId=172237412403634176
//        commentId=172237413104082944
//        commentId=172237413162803200
    }

    CommentResponse createComment(CommentCreateRequest request) {
        return restClient.post()
                .uri("/v1/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v1/comments/{commentId}", 172237412403634176L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        //        commentId=172237412403634176L
//        commentId=172237413104082944L
//        commentId=172237413162803200L

        restClient.delete()
                .uri("/v1/comments/{commentId}", 172237413162803200L)
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequest {

        private Long articleId;
        private String content;
        private Long parentCommentId;
        private Long writerId;
    }
}
