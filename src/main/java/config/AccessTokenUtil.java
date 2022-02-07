package config;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Objects;

public class AccessTokenUtil {


    private final WebClient webClient = WebClient.builder().build();

    public String getAccessToken() {

        String clientId = System.getenv("client_id");
        String clientSecret = System.getenv("client_secret");

        System.out.println(clientId + " --- " + clientSecret);

        String encodedClientData =
                Base64Utils.encodeToString((clientId + ":" + clientSecret).getBytes());

        String response = Objects.requireNonNull(webClient.post()
                        .uri("host.docker.internal:8180/auth/realms/dojo-realm/protocol/openid-connect/token")
                        .header("Authorization", "Basic " + encodedClientData)
                        .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .map(json -> json.get("access_token"))
                        .block())
                .toString().replace("\"","");

        return response;
    }
}
