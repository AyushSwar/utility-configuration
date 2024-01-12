package com.example.exceptions;

public class DaoException {

    private DaoException(){}
    public static class AddException extends RuntimeException {
        public AddException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class GetException extends RuntimeException {
        public GetException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class RemoveException extends RuntimeException {
        public RemoveException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
