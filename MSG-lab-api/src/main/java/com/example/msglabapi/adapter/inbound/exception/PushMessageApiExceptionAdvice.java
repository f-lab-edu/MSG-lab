package com.example.msglabapi.adapter.inbound.exception;

import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PushMessageApiExceptionAdvice {

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<Problem> handleHttpMessageConversionException() {
        final ProblemDetail problemDetail = new ProblemDetail("A request that cannot be converted.",
                                                              "Make sure it's in the correct JSON format.");
        final Problem problem = Problem.create()
                                       .withTitle(problemDetail.getTitle())
                                       .withDetail(problemDetail.getDetail());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleMethodArgumentNotValidException() {
        final ProblemDetail problemDetail = new ProblemDetail("Fields that should not be blank are blank.",
                                                              "Check out the API specification.");
        final Problem problem = Problem.create()
                                       .withTitle(problemDetail.getTitle())
                                       .withDetail(problemDetail.getDetail());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
}
