//package com.datn.watch.common.config;
//
//import com.datn.watch.common.exception.*;
//import com.datn.watch.common.model.json.Result;
//import com.datn.watch.common.model.json.ResultData;
//import com.datn.watch.common.utils.MessageUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.validation.ConstraintViolationException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.ConversionNotSupportedException;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.BindException;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.ServletRequestBindingException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.multipart.support.MissingServletRequestPartException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import java.sql.SQLException;
//import java.util.stream.Collectors;
//
//@org.springframework.web.bind.annotation.RestControllerAdvice
//@Slf4j
//@SuppressWarnings("unused")
//public class RestControllerAdvice {
//
//    private final MessageSource messageSource;
//
//    public RestControllerAdvice(MessageSource source) {
//        this.messageSource = source;
//    }
//
//
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
//            HttpMediaTypeNotSupportedException.class,
//            HttpMediaTypeNotAcceptableException.class, MissingServletRequestParameterException.class,
//            ServletRequestBindingException.class, ConversionNotSupportedException.class,
//            HttpMessageNotReadableException.class, MissingServletRequestPartException.class,
//            NoHandlerFoundException.class, MethodArgumentTypeMismatchException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultData<?> handleMvcException(HttpServletRequest req, Exception e) {
//        e.printStackTrace();
//        log.warn("MVC_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.PARAM_ERROR, e.getMessage(), null);
//    }
//
//
//    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultData<?> handleMethodArgumentNotValidException(HttpServletRequest req,
//                                                               BindException e) {
//        String errors = e.getBindingResult().getFieldErrors().stream()
//                .map(x -> x.getField() + ":" + MessageUtils.getMessage(x.getDefaultMessage()))
//                .collect(Collectors.joining(", "));
//        log.warn("INVALID_REQUEST : {} | {}", req.getRequestURI(), errors);
//        return new ResultData<>(Result.PARAM_ERROR, errors, null);
//    }
//
//    @ExceptionHandler(value = ConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResultData<?> handleConstraintViolationException(HttpServletRequest req,
//                                                               ConstraintViolationException e) {
//        String errors = e.getConstraintViolations().stream()
//                .map(x -> {
//                    int lastPosition = x.getPropertyPath().toString().lastIndexOf(".");
//                    if (lastPosition > 0) {
//                        lastPosition++;
//                    }
//                    return x.getPropertyPath().toString().substring(lastPosition) + ":" + x.getMessage();
//                })
//                .collect(Collectors.joining(", "));
//        log.warn("INVALID_REQUEST : {} | {}", req.getRequestURI(), errors);
//        return new ResultData<>(Result.PARAM_ERROR, errors, null);
//    }
//
//
//    @ExceptionHandler(InvalidRequestException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultData<?> handleInvalidRequestException(HttpServletRequest req,
//                                                       InvalidRequestException e) {
//        log.warn("INVALID_REQUEST_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.PARAM_ERROR, MessageUtils.getMessage(e.getMessage()), null);
//    }
//
//
//    @ExceptionHandler({UnauthorizedException.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ResultData<?> handleUnauthorizedException(HttpServletRequest req,
//                                                     UnauthorizedException e) {
//        log.warn("UNAUTHORIZED_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.ERROR, MessageUtils.getMessage(e.getMessage()), null);
//    }
//
//
//    @ExceptionHandler({Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultData<?> handleException(HttpServletRequest req, Exception e) {
//        log.error("EXCEPTION : {}", req.getRequestURI(), e);
//        String message = "[Exception]" + e.getMessage();
//        if (message.length() > 50) {
//            message = message.substring(0, 50) + "...";
//        }
//        return new ResultData<>(Result.ERROR, message, null);
//    }
//
//    /**
//     * handle NullPointerException.
//     *
//     * @param req HttpServletRequest Object
//     * @param e   Exception Object
//     * @return ResultData Object
//     * @author Obito
//     */
//    @ExceptionHandler({NullPointerException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultData<?> handleNullException(HttpServletRequest req, Exception e) {
//        log.error("NullPointerException : {}", req.getRequestURI());
//        String message = "[NullPointerException]" + e.getMessage();
//        if (message.length() > 50) {
//            message = message.substring(0, 50) + "...";
//        }
//        return new ResultData<>(Result.ERROR, message, null);
//    }
//
//    /**
//     * hand for access define feature.
//     *
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public ResultData<?> handleAccessDeniedException(HttpServletRequest req,
//                                                     AccessDeniedException e) {
//        log.warn("ACCESS_DENIED_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.FORBIDDEN, MessageUtils.getMessage(e.getMessage()), null);
//    }
//
//    /**
//     * hand for case data not found.
//     *
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(DataNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResultData<?> handleNotFoundException(HttpServletRequest req,
//                                                 DataNotFoundException e) {
//        log.warn("NOT_FOUND_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.NO_DATA, MessageUtils.getMessage(e.getMessage()), null);
//    }
//
//    @ExceptionHandler(PetMarketApiException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultData<?> handleBizException(HttpServletRequest req,
//                                            PetMarketApiException e) {
//        log.warn("BIZ_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.ERROR, MessageUtils.getMessage(e.getMessage()), null);
//    }
//
//    /**
//     * Handle for SQL Exception
//     *
//     * @param req HttpServletRequest 객체
//     * @param e   SQLException 객체
//     * @return ResultData 객체
//     */
//    @ExceptionHandler({SQLException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResultData<?> handleSQLException(HttpServletRequest req, SQLException e) {
//        e.printStackTrace();
//        log.warn("SQL_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.PARAM_ERROR, MessageUtils.getMessage("common.error.sql"), null);
//    }
//
//    @ExceptionHandler({InvalidTimeException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultData<?> handleTimeException(HttpServletRequest req, InvalidTimeException e) {
//        log.warn("INVALID_REQUEST_TIME_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.ERROR, e.getMessage(), null);
//    }
//    @ExceptionHandler({PaymentException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResultData<?> handlePaymentException(HttpServletRequest req, PaymentException e) {
//        log.warn("INVALID_REQUEST_PAYMENT_EXCEPTION : {} | {}", req.getRequestURI(), e.getMessage());
//        return new ResultData<>(Result.ERROR, e.getMessage(), null);
//    }
//
//}
