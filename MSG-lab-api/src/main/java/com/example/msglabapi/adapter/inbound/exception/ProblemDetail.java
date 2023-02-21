package com.example.msglabapi.adapter.inbound.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 익셉션에 대한 Problem Detail
 * <br>
 * 사용 예시는 아래와 같습니다.
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
public class ProblemDetail {

    private final String title;

    private final String detail;
}
