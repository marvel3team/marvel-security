package com.marvel.framework;

import com.marvel.common.exception.CommonException;
import com.marvel.framework.exception.ExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname GlobalDefaultExceptionHandler
 * @Description 业务异常+系统异常 统一处理
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * log对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    /**
     * 未知错误
     */
    private static final int UNKNOWED_ERROR = 100000;

    /**
     * （方法禁用） 禁用请求中指定的方法
     */
    private static final int METHOD_NOT_ALLOWED = 100001;

    /**
     * （不支持的媒体类型） 请求的格式不受请求页面的支持
     */
    private static final int UNSUPPORTED_MEDIA_TYPE = 100002;

    /**
     * （不接受）无法使用请求的内容特性响应请求的网页
     */
    private static final int NOT_ACCEPTABLE = 100003;

    /**
     * （服务器内部错误）服务器遇到错误，无法完成请求
     */
    private static final int INTERNAL_SERVER_ERROR = 100004;

    /**
     *（错误请求）服务器不理解请求的语法
     */
    private static final int BAD_REQUEST = 100005;

    /**
     *（未找到）服务器找不到请求的网页。例如，对于服务器上不存在的网页经常会返回此代码
     */
    private static final int NOT_FOUND = 100006;

    /**
     *（服务不可用）目前无法使用服务器（由于超载或进行停机维护）。通常，这只是一种暂时的状态。
     */
    private static final int SERVICE_UNAVAILABLE = 100007;

    /**
     * Description: 业务异常处理
     *
     * @param request
     * @param ex 异常信息
     * @param response
     * @return java.lang.String
     * @Date 下午4:27 2019/2/20
     * @Author zj
     **/
    @ExceptionHandler({CommonException.class, Exception.class})
    public String handlerException(HttpServletRequest request, Exception ex, HttpServletResponse response) {
        if (ex instanceof CommonException) {
            CommonException businessException = (CommonException) ex;
            LOG.error("CommonException:[" + ex.getMessage() + "]");
            return assembleErrorJson(businessException);
        }
        LOG.error("SystemException", ex);
        return assembleErrorJson(ExceptionFactory.SERVER_INTERNAL_ERROR);
    }

    /**
     * Description: 系统异常处理
     *
     * @param ex 异常信息
     * @param body
     * @param headers
     * @param status http状态
     * @param request
     * @return org.springframework.http.ResponseEntity<java.lang.Object>
     * @Date 下午5:11 2019/2/20
     * @Author zj
     **/
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOG.error("FrameworkException:[" + ex.getMessage() + "], api:[" + request.toString() + "]");
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(assembleErrorJson(METHOD_NOT_ALLOWED, ex.getMessage(), status), status);
        }
        if (ex instanceof HttpMediaTypeNotSupportedException) {
            return new ResponseEntity<>(assembleErrorJson(UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), status), status);
        }
        if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return new ResponseEntity<>(assembleErrorJson(NOT_ACCEPTABLE, ex.getMessage(), status), status);
        }
        if (ex instanceof MissingPathVariableException) {
            return new ResponseEntity<>(assembleErrorJson(INTERNAL_SERVER_ERROR, ex.getMessage(), status), status);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof ServletRequestBindingException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof ConversionNotSupportedException) {
            return new ResponseEntity<>(assembleErrorJson(INTERNAL_SERVER_ERROR, ex.getMessage(), status), status);
        }
        if (ex instanceof TypeMismatchException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof HttpMessageNotWritableException) {
            return new ResponseEntity<>(assembleErrorJson(INTERNAL_SERVER_ERROR, ex.getMessage(), status), status);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof MissingServletRequestPartException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof BindException) {
            return new ResponseEntity<>(assembleErrorJson(BAD_REQUEST, ex.getMessage(), status), status);
        }
        if (ex instanceof NoHandlerFoundException) {
            return new ResponseEntity<>(assembleErrorJson(NOT_FOUND, ex.getMessage(), status), status);
        }
        if (ex instanceof AsyncRequestTimeoutException) {
            return new ResponseEntity<>(assembleErrorJson(SERVICE_UNAVAILABLE, ex.getMessage(), status), status);
        }
        return new ResponseEntity<>(assembleErrorJson(UNKNOWED_ERROR, ex.getMessage(), status), status);
    }

    /**
     * Description: 组装错误json串
     *
     * @param be 业务异常
     * @return java.lang.String
     * @Date 下午7:50 2019/2/20
     * @Author zj
     **/
    private String assembleErrorJson(CommonException be) {
        return assembleErrorJson(be.getCode(), be.getMessage(), HttpStatus.OK);
    }

    /**
     * Description: 组装错误json串
     *
     * @param code 错误code
     * @param message 错误信息
     * @param status http状态
     * @return java.lang.String
     * @Date 下午5:05 2019/2/20
     * @Author zj
     **/
    private String assembleErrorJson(int code, String message, HttpStatus status){
        StringBuilder result = new StringBuilder();
        result.append("{\"code\":").append(code);
        result.append(",\"message\":\"").append(message.replaceAll("\"", "'").replaceAll(":", "")).append("\"");
        result.append(",\"httpStatus\":").append(status.value());
        result.append(",\"data\":null}");
        return result.toString();
    }
}
