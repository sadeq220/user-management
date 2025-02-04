package ir.bmi.fusion.user_management.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.client.RestTemplateBuilder;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.client.VaultEndpointProvider;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.core.env.VaultPropertySource;
import org.springframework.web.client.RestTemplate;

public class VaultPropertySourceRegisterer implements ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        ConfigurableEnvironment configurableEnvironment = applicationContext.getEnvironment();
        MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
        String roleId = configurableEnvironment.getProperty("spring.cloud.vault.app-role.role-id");
        String wrappingToken = configurableEnvironment.getProperty("spring.cloud.vault.app-role.wrapping-token-properties");
        VaultConfiguration vaultConfiguration = new VaultConfiguration(roleId, wrappingToken);
        VaultEndpoint vaultEndpoint = vaultConfiguration.vaultEndpoint();
        RestTemplate restTemplate = this.restTemplate(vaultConfiguration.vaultEndpointProvider());
        ClientAuthentication clientAuthentication = vaultConfiguration.clientAuthentication(restTemplate);
        VaultTemplate vaultTemplate = new VaultTemplate(vaultEndpoint, clientAuthentication);

        propertySources.addFirst(vaultPropertySource(vaultTemplate)); // highest precedence
    }
    private PropertySource<VaultOperations> vaultPropertySource(VaultTemplate vaultTemplate){

        return new VaultPropertySource(vaultTemplate, "kv/user-management/properties");
    }
    private RestTemplate restTemplate(VaultEndpointProvider endpointProvider){
        RestTemplateBuilder builder = RestTemplateBuilder.builder()
                .endpointProvider(endpointProvider);
        return builder.build();
    }
}
