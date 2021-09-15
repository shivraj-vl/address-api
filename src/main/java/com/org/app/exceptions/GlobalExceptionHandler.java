package com.org.app.exceptions;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Shivraj
 * Exception controller class
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Logger instance to log messages
	 */
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return ApiError
     * This method handles MethodArgumentNotValidException and send back error to the client.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Invalid Input"),HttpStatus.BAD_REQUEST);
    }

    /**
     * @param HttpMessageNotReadableException
     * @param headers HttpHeaders
     * @param status HttpStatus
     * @param request WebRequest
     * @return ApiError
     * This method handles HttpMessageNotReadableException and send back error to the client.
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException  httpMessageNotReadableException,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request){
        String error = "Malformed JSON request";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, error),HttpStatus.BAD_REQUEST);
    }

    /**
     * @param HttpMessageConversionException
     * @return ApiError
     * This method handles HttpMessageConversionException and send back error to the client.
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<Object> handleHttpMessageConversionException(HttpMessageConversionException httpMessageConversionException){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Invalid Input"),HttpStatus.BAD_REQUEST);
    }

    /**
     * @param NoSuchElementException
     * @return ApiError
     * This method handles NoSuchElementException and send back error to the client.
     */
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNonExistenceResourceException(NoSuchElementException noSuchElementException){
        return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND, "The requested resource does not exist"),HttpStatus.BAD_REQUEST);
    }

    /**
     * @param InvalidInputException
     * @return ApiError
     * This method handles InvalidInputException and send back error to the client.
     */
    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInputException(InvalidInputException invalidInputException){
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, invalidInputException.getMessage()),HttpStatus.BAD_REQUEST);
    }
}
