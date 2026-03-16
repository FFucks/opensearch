import org.opensearch.client.opensearch.OpenSearchClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(OpenSearchClient client) {
        return args -> {

            boolean exists = client.indices()
                    .exists(e -> e.index("products"))
                    .value();

            if (!exists) {

                client.indices().create(c -> c
                        .index("products")
                        .mappings(m -> m
                                .properties("name", p -> p.text(t -> t))
                                .properties("city", p -> p.keyword(k -> k))
                                .properties("price", p -> p.double_(d -> d))
                        )
                );

                System.out.println("Index 'products' created with mapping.");
            }
        };
    }

}
