package com.example.adsonline.exception;
/**
 * Класс - исключение, описывающий ситуацию,
 * когда пользователь не найден в базе данных
 *

 */
public class NotFoundInDataBaseException extends RuntimeException {

    public NotFoundInDataBaseException(String txt) {
        super(txt);
    }

    public NotFoundInDataBaseException() {
        super("Объект не найден в базе данных");
    }
}