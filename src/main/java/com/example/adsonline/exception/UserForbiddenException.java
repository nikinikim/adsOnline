package com.example.adsonline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Класс - исключение, описывающий ситуацию,
 * когда пользователь прошел аутентификацию, но не имеет права на доступ к ресурсу
 *

 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserForbiddenException extends RuntimeException {
    public UserForbiddenException() {
        super("Пользователь авторизован, но не имеет разрешения на доступ к правам администратора");
    }
}