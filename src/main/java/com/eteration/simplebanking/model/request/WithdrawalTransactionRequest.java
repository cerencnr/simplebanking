package com.eteration.simplebanking.model.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonDeserialize(using = WithdrawalTransactionRequest.Deserializer.class)
public class WithdrawalTransactionRequest {
    public double amount;

    static class Deserializer extends JsonDeserializer<WithdrawalTransactionRequest> {
        public WithdrawalTransactionRequest deserialize(JsonParser p, DeserializationContext c) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            WithdrawalTransactionRequest req = new WithdrawalTransactionRequest();
            req.amount = node.get("amount").asDouble();
            return req;
        }
    }
}
