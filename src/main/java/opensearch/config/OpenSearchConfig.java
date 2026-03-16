package opensearch.config;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.transport.OpenSearchTransport;
import org.opensearch.client.RestClient;
import org.apache.http.HttpHost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenSearchConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();
    }

    @Bean
    public OpenSearchClient openSearchClient(RestClient restClient) {

        OpenSearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        return new OpenSearchClient(transport);
    }
}
