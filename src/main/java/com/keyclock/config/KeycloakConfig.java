package com.keyclock.config;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {

    static Keycloak keycloak = null;
//    final static String serverUrl = "http://localhost:8080/realms/Test/protocol/openid-connect/auth";

    final static String serverUrl ="http://localhost:8080/realms/Test";
    public final static String realm = "Test";
    final static String clientId = "test";
    final static String clientSecret = "MENCrCjYGN3qXSjXskvIcAWeAjKVLBlJ";
    final static String userName = "admin";
    final static String password = "admin";

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }
}
