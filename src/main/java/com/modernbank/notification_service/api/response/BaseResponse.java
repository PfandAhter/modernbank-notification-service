package com.modernbank.notification_service.api.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonPropertyOrder({
            "Status",
        "IslemKodu",
        "IslemMesaji"
})
public class BaseResponse {
    @JsonProperty("Status")
    private String status = "1";

    @JsonProperty("IslemKodu")
    private String processCode = "SUCCESS";

    @JsonProperty("IslemMesaji")
    private String processMessage = "SUCCESS";

    public BaseResponse(){

    }

    public BaseResponse(String processMessage){
        this.processMessage = processMessage;
    }
}