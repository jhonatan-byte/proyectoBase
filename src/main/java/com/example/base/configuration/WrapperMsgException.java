package com.example.base.configuration;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

public class WrapperMsgException extends Exception {
    public static final String NOT_NOTIFICATIONS = "No se pudo recuperar el objeto de Notifications";
    @Getter
    private final String detail;

    public static String merge(String message, String detail) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            if (jsonNode.has("message") && jsonNode.has("detail")) {
                return message;
            } else {
                String formatDetail = detail.replaceAll("\"", "'");
                return String.format(
                        "{\"message\":\"%s\", \"detail\":\"%s\"}",
                        message,
                        formatDetail
                );
            }
        } catch (Exception e) {
            String formatDetail = detail.replaceAll("\"", "'");
            return String.format(
                    "{\"message\":\"%s\", \"detail\":\"%s\"}",
                    message,
                    formatDetail
            );
        }
    }

//    public static String mergeNotifications(NotificationEnvelope notificationEnvelope) {
//        String message = (notificationEnvelope.getNotifications() == null || notificationEnvelope.getNotifications().isEmpty()) ? NOT_NOTIFICATIONS : notificationEnvelope.getNotifications().get(0).getMessage();
//        String detail = extractDetail(notificationEnvelope);
//        return String.format(
//                "{\"message\":\"%s\", \"detail\":\"%s\"}",
//                message,
//                detail
//        );
//    }
//
//    private static String extractDetail(NotificationEnvelope notificationEnvelope) {
//        return (notificationEnvelope.getNotifications() == null || notificationEnvelope.getNotifications().isEmpty()) ? NOT_NOTIFICATIONS : notificationEnvelope.getNotifications().get(0).getDetail();
//    }
//
//    WrapperMsgException(NotificationEnvelope notificationEnvelope) {
//        super(WrapperMsgException.mergeNotifications(notificationEnvelope));
//        this.detail = extractDetail(notificationEnvelope);
//    }

    WrapperMsgException(String message, String detail) {
        super(WrapperMsgException.merge(message, detail));
        this.detail = detail;
    }

    WrapperMsgException(String message) {
        super(WrapperMsgException.merge(message, ""));
        this.detail = message;
    }

    WrapperMsgException(String message, String detail, Throwable cause) {
        super(WrapperMsgException.merge(message, detail), cause);
        this.detail = detail;
    }
}
