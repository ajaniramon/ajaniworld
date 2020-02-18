package es.heathcliff.ajaniworld.controller.handlers

import es.heathcliff.ajaniworld.exception.ColivingException
import es.heathcliff.ajaniworld.exception.StorageItemNotExistsException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {
    // TODO: Modify method to return custom error models
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              httpHeaders: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest) : ResponseEntity<Any>{
        return ResponseEntity(ex.bindingResult.allErrors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [ColivingException::class])
    fun handleColivingException(ex: ColivingException, request: WebRequest) :
            ResponseEntity<Any>{
        return ResponseEntity(ex.errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [StorageItemNotExistsException::class])
    fun handleStorageItemNotExistsException(ex: StorageItemNotExistsException, request: WebRequest) :
            ResponseEntity<Any>{
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}