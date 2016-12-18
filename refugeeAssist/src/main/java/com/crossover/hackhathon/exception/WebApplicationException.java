package com.crossover.hackhathon.exception;

import org.springframework.http.HttpStatus;

/**
 * Runtime exception for applications.
 * <p>
 * This exception may be thrown by a resource method, provider or 
 * {@link javax.ws.rs.core.StreamingOutput} implementation if a specific 
 * HTTP error response needs to be produced. Only effective if thrown prior to
 * the response being committed.
 *
 * @author Murat Hacioglu.
 */
public class WebApplicationException extends RuntimeException {

    private static final long serialVersionUID = 11660101L;
    
    private final HttpStatus httpStatus;

    /**
     * Construct a new instance with a blank message and default HTTP status code of 500
     */
    public WebApplicationException() {
        this(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Construct a new instance with a message and default HTTP status code of 500
     */
    public WebApplicationException(String message) {
        this(null, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }    
    
    /**
     * Construct a new instance with a blank message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     */
    public WebApplicationException(int status) {
        this(null, status);
    }
    
    /**
     * Construct a new instance with a message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     */
    public WebApplicationException(int status, String message) {
        this(null, status);
    }    
    
    /**
     * Construct a new instance with a blank message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     * @throws IllegalArgumentException if status is null
     */
    public WebApplicationException(HttpStatus status) {
        this(null, status);
    }
    
    /**
     * Construct a new instance with a message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     * @throws IllegalArgumentException if status is null
     */
    public WebApplicationException(HttpStatus status, String message) {
        this(null, status, message);
    }
    
    /**
     * Construct a new instance with a blank message and default HTTP status code of 500
     * @param cause the underlying cause of the exception
     */
    public WebApplicationException(Throwable cause) {
        this(cause,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Construct a new instance with a message and default HTTP status code of 500
     * @param cause the underlying cause of the exception
     */
    public WebApplicationException(Throwable cause, String message) {
        this(cause,HttpStatus.INTERNAL_SERVER_ERROR, message);
    }    
    
    /**
     * Construct a new instance using the supplied response
     * @param response the response that will be returned to the client, a value
     * of null will be replaced with an internal server error response (status
     * code 500)
     * @param cause the underlying cause of the exception
     */
    public WebApplicationException(Throwable cause, HttpStatus response) {
        super(cause);
        if (response==null)
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        else
            this.httpStatus = response;        
    } 
    
    /**
     * Construct a new instance using the supplied response
     * @param response the response that will be returned to the client, a value
     * of null will be replaced with an internal server error response (status
     * code 500)
     * @param cause the underlying cause of the exception
     */
    public WebApplicationException(Throwable cause, HttpStatus response, String message) {
        super(message, cause);
        if (response==null)
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        else
            this.httpStatus = response;        
    }    
    
    /**
     * Construct a new instance with a blank message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     * @param cause the underlying cause of the exception
     * @throws IllegalArgumentException if status is null
     */
    public WebApplicationException(Throwable cause, int statusCode) {
        this(cause, HttpStatus.valueOf(statusCode));
    }
    
    /**
     * Construct a new instance with a blank message and specified HTTP status code
     * @param status the HTTP status code that will be returned to the client
     * @param cause the underlying cause of the exception
     * @throws IllegalArgumentException if status is null
     */
    public WebApplicationException(Throwable cause, int statusCode, String message) {
        this(cause, HttpStatus.valueOf(statusCode), message);
    }    
    
    /**
     * Get the HTTP response.
     *
     * @return the HTTP response.
     */
    public HttpStatus getStatus() {
        return httpStatus;
    }
}
