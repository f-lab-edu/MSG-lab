package com.example.msglabapi.adapter.outbound.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HttpServerErrorExceptionDetail {
    private final String title = "There is an error on the requested server.";
    private final String detail = "Please check the status of the server that sent the request.";
}
