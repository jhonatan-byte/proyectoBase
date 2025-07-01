package com.example.base.configuration;

/**
 * Allow to initialize the application
 *
 * @author Williams Gomez - williams.gomez@deltmarg.com
 * @version 1.0.0-alpha - 22 mar 2023
 * @since 1.0.0-alpha - 22 mar 2023
 */

public class BusinessException extends WrapperMsgException {

    public enum Type {
        RESPONSE_EMPTY("La respuesta esta vacia", 400, "RESPONSE_EMPTY"),
        XXX_UNAUTHORIZER("Token expirado", 401, "XXX_UNAUTHORIZER"),
        XXX_400("Peticion con problemas",400,"XXX_400"),
        XXX_409("Peticion con problemas",409,"XXX_409"),
        XXX_204("Base de dato sin usuarios",204,"XXX_204"),
        XXX_404("No Encontrado",400,"XXX_404"),
        XXX_500("No Disponible",400,"XXX_500"),
        SERVICE_NOT_AVAILABLE("",500,"SERVICE_NOT_AVAILABLE"),
        INTENTS_EXCEEDED("-701",409,"INTENTS_EXCEEDED"),
        INCORRECT_ANSWERS("-702",409,"INCORRECT_ANSWERS"),
        DOES_NOT_MATCH("-703",409,"DOES_NOT_MATCH"),
        CLIENT_NOT_REGISTERED("-704",409,"CLIENT_NOT_REGISTERED"),
        PROVIDER_NOT_AVAILABLE("-705",409,"PROVIDER_NOT_AVAILABLE"),
        NOTIFY_FAILED("",409,"NOTIFY_ISIDER_FAILED");


        private final String message;
        private final Integer status;
        private final String code;

        Type(String message, Integer status, String code) {
            this.message = message;
            this.status = status;
            this.code = code;
        }

        public static BusinessException autoNotFound(String autoNoExiste) {
            return XXX_409.buildWithDetail(autoNoExiste);
        }


        public String getMessage() {
            return message;
        }

        public Integer getStatus() {
            return status;
        }

        public String getCode() {
            return code;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public BusinessException build(Throwable throwable) {
            return new BusinessException(this, throwable);
        }

        public BusinessException buildWithDetail(String detail) {
            return new BusinessException(this, detail);
        }

        public BusinessException buildWithType(Type type, Throwable throwable) {
            return new BusinessException(type, throwable);
        }

        public BusinessException buildWithDetail(String detail, Throwable throwable) {
            return new BusinessException(this, detail, throwable);
        }
    }

    private final Type type;

    private final String detail;

    private BusinessException(Type type) {
        super(type.getMessage());
        this.type = type;
        this.detail = "";
    }

    private BusinessException(Type type, String detail) {
        super(type.getMessage(), detail);
        this.type = type;
        this.detail = detail;
    }

    private BusinessException(Type type, String detail, Throwable throwable) {
        super(type.getMessage(),detail, throwable);
        this.type = type;
        this.detail = detail;
    }

    private BusinessException(Type type, Throwable throwable) {
        super(type.getMessage(),"", throwable);
        this.type = type;
        this.detail = "";
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getDetail() {
        return detail;
    }
}
