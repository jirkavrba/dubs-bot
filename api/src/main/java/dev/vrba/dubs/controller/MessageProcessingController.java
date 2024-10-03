package dev.vrba.dubs.controller;

import dev.vrba.dubs.domain.Pattern;
import dev.vrba.dubs.dto.ProcessMessageRequest;
import dev.vrba.dubs.dto.ProcessMessageResponse;
import dev.vrba.dubs.mapper.MatchedPatternMapper;
import dev.vrba.dubs.service.MessageProcessingService;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Controller("/api/v1/bot/process-message")
public class MessageProcessingController {

    @NonNull
    private final MessageProcessingService service;

    @NonNull
    private final MatchedPatternMapper mapper;

    @Post
    public HttpResponse<ProcessMessageResponse> processMessage(final @Valid @Body ProcessMessageRequest request) {
        final List<Pattern> patterns = service.processMessage(
                request.message(),
                request.user(),
                request.channel(),
                request.guild()
        );

        final ProcessMessageResponse response = mapper.mapMatchedPatternsToResponse(patterns);

        return HttpResponse.ok(response);
    }

}
