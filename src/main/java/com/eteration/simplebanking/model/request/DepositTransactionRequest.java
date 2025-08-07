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
@JsonDeserialize(using = DepositTransactionRequest.Deserializer.class)
public class DepositTransactionRequest {
    public double amount;

    static class Deserializer extends JsonDeserializer<DepositTransactionRequest> {
        public DepositTransactionRequest deserialize(JsonParser p, DeserializationContext c) throws IOException {
            JsonNode node = p.getCodec().readTree(p);
            DepositTransactionRequest req = new DepositTransactionRequest();
            req.amount = node.get("amount").asDouble();
            return req;
        }
    }
}

