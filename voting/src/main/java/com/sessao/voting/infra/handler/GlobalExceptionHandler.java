package com.sessao.voting.infra.handler;

import com.sessao.voting.exception.*;
import com.sessao.voting.exception.details.*;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<InternalServerErroDetails> handleInternalServerErrorException(InternalServerErrorException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                InternalServerErroDetails.builder()
                        .title("Internal Server Error")
                        .details(ex.getMessage())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()

        );
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestDetails> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }



    @ExceptionHandler(CpfAlreadyExistsException.class)
    public ResponseEntity<CpfAlreadyExistsDetails> handleCpfAlreadyExistsException(CpfAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CpfAlreadyExistsDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<IllegalArgumentDetails> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                IllegalArgumentDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<BadRequestDetails> handleIllegalStateException(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BadRequestDetails> handleIllegalStateException(FeignException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BadRequestDetails.builder()
                        .title("Bad Request check the documentation")
                        .details(ex.getMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<NullPointerDetails> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                NullPointerDetails.builder()
                        .title("Bad Request check the documentation")
                        .details("Bad Request, check the documentation")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundDetails> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                NotFoundDetails.builder()
                        .title("Content not found, verify the id")
                        .details(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationDetails> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).reduce("", (a, b) -> a + ", " + b);
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).reduce("", (a, b) -> a + ", " + b);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ValidationDetails.builder()
                        .title("Bad Request Exception, invalid fields")
                        .details("Check the fields error")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .developerMessage(ex.getClass().getName())
                        .timestamp(LocalDateTime.now())
                        .field(fields)
                        .fieldsMessage(fieldsMessage)
                        .build()
        );
    }



}