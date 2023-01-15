package com.example.msglabapi.adapter.inbound.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JsonConvert 익셉션에 대한 ProblemDetail입니다.
 * <br>
 * 예시는 아래와 같습니다.
 * <br>
 * <pre>
 * {
 *     "title": "your request is not convertible",
 *     "detail": "json parsing error Please check the format again"
 * }
 * </pre>
 * @see <a href="https://www.rfc-editor.org/rfc/rfc7807">RFC7807</a>
 */
@Getter
@RequiredArgsConstructor
public class JsonConvertProblemDetail {

    private final String title = "your request is not convertible";

    private final String detail = "json parsing error Please check the format again";
}
