package ir.bmi.fusion.user_management.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.AppRoleAuthentication;
import org.springframework.vault.authentication.AppRoleAuthenticationOptions;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.SessionManager;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.support.VaultToken;
import org.springframework.web.client.RestOperations;

@Configuration
public class VaultConfiguration extends AbstractVaultConfiguration {

    private final String vaultAppRoleAuthRoleID;
    private final String vaultAppRoleAuthWrappingToken;

    public VaultConfiguration(@Value("${spring.cloud.vault.app-role.role-id}") String vaultAppRoleAuthRoleID,
                              @Value("${spring.cloud.vault.app-role.wrapping-token}") String vaultAppRoleAuthWrappingToken) {
        this.vaultAppRoleAuthRoleID = vaultAppRoleAuthRoleID;
        this.vaultAppRoleAuthWrappingToken=vaultAppRoleAuthWrappingToken;
    }

    @Override
    public VaultEndpoint vaultEndpoint() {
        return VaultEndpoint.from("http://localhost:8200");
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return clientAuthentication(restOperations());
    }
    public ClientAuthentication clientAuthentication(RestOperations restOperations){
        // AppRole authentication consists of two hard to guess (secret) tokens: RoleId and SecretId.
        AppRoleAuthenticationOptions appRoleOptions = AppRoleAuthenticationOptions.builder()
                .roleId(AppRoleAuthenticationOptions.RoleId.provided(vaultAppRoleAuthRoleID))
                .secretId(AppRoleAuthenticationOptions.SecretId.wrapped(VaultToken.of(vaultAppRoleAuthWrappingToken)))
                .build();

        return new AppRoleAuthentication(appRoleOptions, restOperations);
    }

    /**
     * A SessionManager decides how often it obtains a token, about revocation and renewal
     */
    @Override
    public SessionManager sessionManager() {
        return super.sessionManager();
    }
}
