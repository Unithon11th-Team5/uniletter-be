package unithon.team5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import unithon.team5.login.service.AppleOauthClient;

@Configuration
public class HttpInterfaceConfig {

  @Bean
  public AppleOauthClient appleOauthClient() {
    return createHttpInterface(AppleOauthClient.class);
  }

  private <T> T createHttpInterface(final Class<T> clazz) {
    final WebClient webClient = WebClient.create();
    final HttpServiceProxyFactory build = HttpServiceProxyFactory
        .builder(WebClientAdapter.forClient(webClient))
        .build();
    return build.createClient(clazz);
  }
}
