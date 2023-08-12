package br.com.prognosticare.createToken;

import java.util.Base64;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;



public class CreateTokenResetTest {
    

    @Test
    void createToken() throws Exception{
        KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
        service.setServerSecret("SECRET123");
        service.setServerInteger(12);
        service.setSecureRandom(new SecureRandomFactoryBean().getObject());
        Token token = service.allocateToken("user@gmail.com");
        System.out.println(token.getExtendedInformation());
        System.out.println(token.getKey());
    }
    //MTY5MTc5MjUyNjEyNDo0NzAwNjNkZDY0MWM5OWMzNzA4MTQyN2VmNjdhYTA4YmJlMzY2MDJlMjY3YmY4NmZlOWJmNDRkZTcwZjRkOTY5OnVzZXJAZ21haWwuY29tOmU5MmI2ZmU0OGI5YTYyMzY3NTAyZTA3YjAwMDViZDY3ZDdkNjJmNThiNmJkYmJlOWQ5ODQ0ZjQyN2I5ODZiN2EwZmUxN2E3OTA5NmE4YWI1MWQyNDhjMzUwMmZkNWJhYzNiNmM5ZWNlOTU5MjlkMzUyNTZlZjRiMGI4ZDVlYzFh

    @Test
    void readToken() throws Exception{
        KeyBasedPersistenceTokenService service = new KeyBasedPersistenceTokenService();
        service.setServerSecret("SECRET123");
        service.setServerInteger(12);
        service.setSecureRandom(new SecureRandomFactoryBean().getObject());
        String rawToken = "MTY5MTc5MjUyNjEyNDo0NzAwNjNkZDY0MWM5OWMzNzA4MTQyN2VmNjdhYTA4YmJlMzY2MDJlMjY3YmY4NmZlOWJmNDRkZTcwZjRkOTY5OnVzZXJAZ21haWwuY29tOmU5MmI2ZmU0OGI5YTYyMzY3NTAyZTA3YjAwMDViZDY3ZDdkNjJmNThiNmJkYmJlOWQ5ODQ0ZjQyN2I5ODZiN2EwZmUxN2E3OTA5NmE4YWI1MWQyNDhjMzUwMmZkNWJhYzNiNmM5ZWNlOTU5MjlkMzUyNTZlZjRiMGI4ZDVlYzFh";

        Token token = service.verifyToken(rawToken);
        System.out.println(token.getExtendedInformation());
        System.out.println(token.getKey());

    }

    @Test
    void readPublicToken() throws Exception{
         String rawToken = "MTY5MTc5MjUyNjEyNDo0NzAwNjNkZDY0MWM5OWMzNzA4MTQyN2VmNjdhYTA4YmJlMzY2MDJlMjY3YmY4NmZlOWJmNDRkZTcwZjRkOTY5OnVzZXJAZ21haWwuY29tOmU5MmI2ZmU0OGI5YTYyMzY3NTAyZTA3YjAwMDViZDY3ZDdkNjJmNThiNmJkYmJlOWQ5ODQ0ZjQyN2I5ODZiN2EwZmUxN2E3OTA5NmE4YWI1MWQyNDhjMzUwMmZkNWJhYzNiNmM5ZWNlOTU5MjlkMzUyNTZlZjRiMGI4ZDVlYzFh";
         byte[] bytes = Base64.getDecoder().decode(rawToken);
         String rawTokenDecoded = new String(bytes);
         System.out.println(rawTokenDecoded);
         String [] tokenParts = rawTokenDecoded.split(":");
         Long timestamp = Long.parseLong(tokenParts[0]);
         System.out.println(new Date(timestamp));
         System.out.println(tokenParts[2]);
    }
}
