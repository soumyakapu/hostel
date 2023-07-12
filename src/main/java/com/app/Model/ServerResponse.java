package com.app.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Builder
@Setter
@Getter
public class ServerResponse {
    private HttpStatus status;
    private String message;
    private Object data;
}
