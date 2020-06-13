package br.com.hrs.api.support;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * Utility class to build an @{link {@link ResponseEntity}
 * @author renato-rs
 *
 * @param <T>
 */
public class ResponseEntitySupport<T> {

    private final T body;
    private final HttpStatus httpStatus;
    private final ResponseEntity<T> responseEntity;

    private ResponseEntitySupport(T body, HttpStatus httpStatus, ResponseEntity<T> responseEntity) {
        this.body = body;
        this.httpStatus = httpStatus;
        this.responseEntity = responseEntity;
    }

    /**
     * Create an instance of {@link ResponseEntitySupport<T>}, storing the httpStatus in it's instance
     * @return {@link ResponseEntitySupport<T>} containing an httpStatus to build
     */
    public static <T> ResponseEntitySupport<T> of(HttpStatus httpStatus) {
        return new ResponseEntitySupport<T>(null, httpStatus, null);
    }

    /**
     * Create an instance of {@link ResponseEntitySupport<T>}, storing the body in it's instance
     * @param body for return in {@link ResponseEntity}
     * @return {@link ResponseEntitySupport<T>} containing an body to build
     */
    public static <T> ResponseEntitySupport<T> of(T body) {
        return new ResponseEntitySupport<T>(body, null, null);
    }

    /**
     * Create an instance of {@link ResponseEntitySupport<T>}, storing the body with the httpStatus in it's instance
     * <br>Note: If there is any body, assumes httpStatus as parameter, otherwise httpStatus there is no sense and assumes null
     * @param body for return in {@link ResponseEntity}
     * @param httpStatus for return in {@link ResponseEntity}
     * @return {@link ResponseEntitySupport<T>} containing an body with the httpStatus to build
     */
    public static <T> ResponseEntitySupport<T> of(T body, HttpStatus httpStatus) {
        return new ResponseEntitySupport<T>(body, body != null ? httpStatus :  null, null);
    }

    /**
     * Create an instance of {@link ResponseEntitySupport<T>}, storing the responseEntity in it's instance
     * @param responseEntity for return in this builder
     * @return {@link ResponseEntitySupport<T>} containing an responseEntity to build
     */
    public static <T> ResponseEntitySupport<T> of(ResponseEntity<T> responseEntity) {
        return new ResponseEntitySupport<T>(null, null, responseEntity);
    }

    /**
     * Builds a {@link ResponseEntity} with the given body and status (set in instantiation time).
     * <br>Note: If there is any body, assumes {@link HttpStatus#OK}, otherwise {@link HttpStatus#NO_CONTENT}
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<?> buildResponse() {
        return buildFromInstanceOr(new ResponseEntity<T>(HttpStatus.NO_CONTENT));
    }

    /**
     * Builds a {@link ResponseEntity} with the given body
     * @param body
     * <br>Note: If there is any body, assumes {@link HttpStatus#OK}, otherwise {@link HttpStatus#NO_CONTENT}
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> buildResponse(T body) {
        return new ResponseEntity<T>(body, body != null ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    /**
     * Builds a {@link ResponseEntity} with the given body and httpStatus
     * @param body
     * @param httpStatus
     * @return {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> buildResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<T>(body, httpStatus);
    }

    /**
     * Builds a {@link ResponseEntity<?>} with the given body (set in instantiation time)
     * or a new {@link ResponseEntity<?>} based on THIS body, if there is none was created in instantiation time
     * <br>Note: If there is any body, assumes {@link HttpStatus#OK}, otherwise {@link HttpStatus#NO_CONTENT}
     * @param body as ResponseEntity if there is any
     * @return {@link ResponseEntity<?>}
     */
    public <B> ResponseEntity<?> orElse(B body) {
        return buildFromInstanceOr(new ResponseEntity<B>(body, body != null ? HttpStatus.OK : HttpStatus.NO_CONTENT));
    }

    /**
     * Builds a {@link ResponseEntity<?>} with the given body (set in instantiation time)
     * or new {@link ResponseEntity<?>} based on THIS body and THIS httpStatus if none was created in instantiation time
     * @param body as ResponseEntity if there is any
     * @param httpStatus
     * @return {@link ResponseEntity<?>}
     */
    public <B> ResponseEntity<?> orElse(B body, HttpStatus httpStatus) {
        return buildFromInstanceOr(new ResponseEntity<B>(body, httpStatus));
    }

    /**
     * Builds a {@link ResponseEntity<?>} with the given body (set in instantiation time)
     * or an new {@link ResponseEntity<?>} based on THIS httpStatus if none was created in instantiation time
     * @param httpStatus
     * @return {@link ResponseEntity<?>}
     */
    public ResponseEntity<?> orElse(HttpStatus httpStatus) {
        return buildFromInstanceOr(new ResponseEntity<>(httpStatus));
    }

    /**
     * Builds a {@link ResponseEntity<?>} with the given body (set in instantiation time)
     * or THIS {@link ResponseEntity<?>} if none was created in instantiation time
     * @param responseEntity
     * @return {@link ResponseEntity<?>}
     */
    public <B> ResponseEntity<?> orElse(ResponseEntity<B> responseEntity) {
        return buildFromInstanceOr(responseEntity);
    }

    /**
     * Builds a {@link ResponseEntity<?>} with the given body, httpStatus or responseEntity (set in instantiation time)
     * or THIS {@link ResponseEntity<?>} received as parameter.
     * @param responseEntity to return if there is any responseEntity or body defined in instantiation time;
     * @return ResponseEntity<?> created with the given body, httpStatus or responseEntity (set in instantiation time),
     * otherwise, THE {@link ResponseEntity<?>} received as parameter.
     */
    private ResponseEntity<?> buildFromInstanceOr(ResponseEntity<?> responseEntity){

        // if there is some 'responseEntity', return it
        if (this.responseEntity != null) {
            return this.responseEntity;
        }

        ResponseEntity<?> response = null;

        if (this.httpStatus != null) {
            // If there is any httpStatus, create a new ResponseEntity with the given body and httpStatus,
            response = new ResponseEntity<T>(this.body, this.httpStatus);
        }else if (this.body != null) {
            // If there is any body, create a new ResponseEntity with the given body,
            response = new ResponseEntity<T>(this.body,  HttpStatus.OK);
        }else{
            // otherwise return the responseEntity received by parameter
            response = responseEntity;
        }

        return response;
    }
}

