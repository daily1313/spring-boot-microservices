package board.comment.api;

import board.comment.service.request.CommentCreateRequest;
import board.comment.service.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class CommentApiV2Test {

    RestClient restClient = RestClient.create("http://localhost:9001");

    @Test
    void create() {
        CommentResponse response1 = create(new CommentCreateRequestV2(2L, "my comment1", null, 1L));
        CommentResponse response2 = create(new CommentCreateRequestV2(2L, "my comment2", response1.getPath(), 1L));
        CommentResponse response3 = create(new CommentCreateRequestV2(2L, "my comment3", response2.getPath(), 1L));

        System.out.println("response1.getPath() = " + response1.getPath());
        System.out.println("response1.getCommentId() = " + response1.getCommentId());
        System.out.println("\t response2.getPath() = " + response2.getPath());
        System.out.println("\t response2.getCommentId() = " + response2.getCommentId());
        System.out.println("\t\t response3.getPath() = " + response3.getPath());
        System.out.println("\t\t response3.getCommentId() = " + response3.getCommentId());
    }

    /*
        response1.getPath() = 00004
        response1.getCommentId() = 174893784257478656
	    response2.getPath() = 0000400000
	    response2.getCommentId() = 174893784618188800
		 response3.getPath() = 000040000000000
		 response3.getCommentId() = 174893784710463488
     */

    CommentResponse create(CommentCreateRequestV2 request) {
        return restClient.post()
                .uri("/v2/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
    }

    @Test
    void read() {
        CommentResponse response = restClient.get()
                .uri("/v2/comments/{commentId}", 174893784257478656L)
                .retrieve()
                .body(CommentResponse.class);

        System.out.println("response = " + response);
    }

    @Test
    void delete() {
        restClient.delete()
                .uri("/v2/comments/{commentId}", 174893784257478656L)
                .retrieve();
    }

    @Getter
    @AllArgsConstructor
    public static class CommentCreateRequestV2 {

        private Long articleId;
        private String content;
        private String parentPath;
        private Long writerId;
    }
}
