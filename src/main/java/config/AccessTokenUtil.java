package config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AccessTokenUtil {


    private final WebClient webClient = WebClient.builder().build();


//    public String getOAuthDetail()  {
//        URL url = AccessTokenUtil.class.getResource("application.yml");
//        File file = new File(url.getPath());
//
//        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//        ApplicationConfig config = null;
//
//        try {
//            config = objectMapper.readValue(file, ApplicationConfig.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return config.getOauth().getClientId();
//    }

    public String getAccessToken() {

        String encodedClientData =
                Base64Utils.encodeToString("dojo-code-execution:qjsD7J3pkbdJ6jtGCkI7tjfdw2OctS8y".getBytes());

        String response = Objects.requireNonNull(webClient.post()
                        .uri("host.docker.internal:8180/auth/realms/dojo-realm/protocol/openid-connect/token")
                        .header("Authorization", "Basic " + encodedClientData)
                        .body(BodyInserters.fromFormData("grant_type", "client_credentials"))
                        .retrieve()
                        .bodyToMono(JsonNode.class)
                        .map(json -> json.get("access_token"))
                        .block())
                .toString().replace("\"","");

        System.out.println("getting access token");
    //    System.out.println("client_id is " + getOAuthDetail());
     //   System.out.println(response);

        return response;
    }
}
