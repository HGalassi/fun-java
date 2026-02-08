package fun.infrastructure.dynamodb.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
@Component
public class DynamoFactory {
    @Value("${aws.region}")
    private String region;

    @Value("${aws.dynamodb.url}")
    private String dynamoDbEndpointUrl;

    @Value("${aws.dynamodb.access-key}")
    private String accessKey;

    @Value("${aws.dynamodb.secret-key}")
    private String secretKey;
    /**
     * @return an instance of DynamoDbClient
     */
    public DynamoDbClient dynamoDbClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                accessKey,
                secretKey
        );
        AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);

        return DynamoDbClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(dynamoDbEndpointUrl))
                .credentialsProvider(provider)
                .httpClientBuilder(ApacheHttpClient.builder())
                .build();
    }
}
