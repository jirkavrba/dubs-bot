package dev.vrba.dubs.controller;

import dev.vrba.dubs.dto.ProcessMessageRequest;
import dev.vrba.dubs.dto.ProcessMessageResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;

@Controller("/api/v1/bot/process-message")
public class MessageProcessingController {

    @Post
    public HttpResponse<ProcessMessageResponse> processMessage(@Valid @Body ProcessMessageRequest request) {
        return HttpResponse.ok(null);
    }

}
