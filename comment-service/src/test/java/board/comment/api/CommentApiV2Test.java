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

        System.out.println("response1 = " + response1.getPath());
        System.out.println("\t response2 = " + response2.getPath());
        System.out.println("\t\t response3 = " + response3.getPath());

    }

    CommentResponse create(CommentCreateRequestV2 request) {
        return restClient.post()
                .uri("/v2/comments")
                .body(request)
                .retrieve()
                .body(CommentResponse.class);
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
