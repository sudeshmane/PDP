package com.example.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter{
	
	private String clientid = "tutorialspoint";
	   private String clientSecret = "my-secret-key";
	  // private String privateKey = "private key";
	   static String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n"
	   		+ "MIIEowIBAAKCAQEAzmSgXCnUA9/AxqGs92Y0zDUrtL8kuYCtkA7km8kQH85URw1j\r\n"
	   		+ "eWmjUhlTbtWnqUiUa+Zp4rcuXhKHq6h/p/6h6Gg6GCxMg2e5rLq3/o9v9aWaWqG1\r\n"
	   		+ "xVwR09xPysuF/WVhEzlvq1Bg1sKmXp0F2laBx6PdKNAvOuNQskgWvohXBXaaSow7\r\n"
	   		+ "TGcW0hj6cdf+Yy+OapJVNiwadSca6P9+9SNzU6R+LlhtjUKxVT14G0vWY/zguA+f\r\n"
	   		+ "ClYSgGCVLIruVWrgsfVOlLvQ0GCMX+6ZQSkV4qQqC6BicB517azfoZUd4gJJZ/BS\r\n"
	   		+ "Y+pMeSaILEhVE/Llet5hFHg21xrk9RZQ0aNlRQIDAQABAoIBAGm4fbP38RQy7ypP\r\n"
	   		+ "8quffPgFXGfvaT5kPYOHPW7XJR1FsUHcB9JPXEtXkbc6VfGOrgg/NInfTy78yuYR\r\n"
	   		+ "H6LL+d90VB3Bw0ekQCXMVydcDfuEYnZ+Uv/PU4p0VaMJcY8E+R7B0wOr3o7jkhjw\r\n"
	   		+ "0eivWzcKJuUbDrZZbiKEbrIWYIz48ihN++iYzMncump/Snjx5r/h27FtWU8hvKJy\r\n"
	   		+ "dzZZjrp9mVnFiwf1W8lBVWfnxozL5TUyCwbFrECxowEmFOT+sbrr9iq/iHy3EzOt\r\n"
	   		+ "1gcEf5gqoro1z8y+eyH3i4zJCf9vGllAIpp/Fcn8fW4Vh8UVwaNJBJUsL7fcpwIU\r\n"
	   		+ "zeTrh4ECgYEA7DhDrPU+emYjmr+WKXu4qthWVBD/1H+e19XRCWN3S8F0FpnXNR49\r\n"
	   		+ "e3ODjmFOAMCxxrX9jTnYlwNXamqm4WMc6OVmtPOiXn2AgzzXF47VPF6B4YF0Eiw0\r\n"
	   		+ "qSHDBHJq/TrqapE9X9+CijDgpEiWgHkUBEIxyM66B+Woibc8bluaY1UCgYEA36z6\r\n"
	   		+ "/9rCoKQx+2RHAoUt9SSR58WVH4lQOp/DbOJ0dlxsnpzyiNgxdSgLF8MSkskF0CK+\r\n"
	   		+ "1tw+cHGBoJn0HDxa0dNBQBio7N6NvPLzEpDsGlSzB9H9Qw2VpFh31ouR+HIR5Lkf\r\n"
	   		+ "S+7lKASwx1V3ERasIb5R6L0wPn/hLxj5UY6A2jECgYEAn/gibJjQrNdKuVuRd5pf\r\n"
	   		+ "nuNO4zN37RJYIdac73y6p0TlNwEL6t8Fx/qcVumyh3P3cJiCkwdflDUhF+IODYiv\r\n"
	   		+ "ZVElUtnDVbYsN7QV/dAuu+lMj8i5MC1bpEZG6VYK+l7RrUWaiLi2kJs1Jkw6D9rv\r\n"
	   		+ "IUsqp9DHzt1KW6kJdbr3PNkCgYBsbvRjco7lx5O/dlaCX0PvUqI1BPEND8lIEklw\r\n"
	   		+ "ELw64QV7Q0TEmGIplJcEFvp7FF7wDtgLW0wdwLkBvYC863lvvQUl6I6D/kQiNHko\r\n"
	   		+ "J/99qE/+W4t2pTBFMuotpQ3Hrq0D/8avTgnjg5qbl9AYBbC+zv/wXz7zHZVx+p5r\r\n"
	   		+ "LjVDEQKBgGHFjEZOPHOfjVo5w9oi5CcxQQdENtFWbRt+ziNCpOdWYoJLCNj8KWnJ\r\n"
	   		+ "zsDriCMdcYt5ibbKjzJFTx4lbAHdCC8B6y0JfzGRDQkm4W45UT9YrrmkMWMx2CBu\r\n"
	   		+ "1MgTV8lCXP7RZugaEZOCCAtVDE040WRpsJMdoERBU/hta1VtsbaW\r\n"
	   		+ "-----END RSA PRIVATE KEY-----";
	  // private String publicKey = "public key";
	   static String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
	   		+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzmSgXCnUA9/AxqGs92Y0\r\n"
	   		+ "zDUrtL8kuYCtkA7km8kQH85URw1jeWmjUhlTbtWnqUiUa+Zp4rcuXhKHq6h/p/6h\r\n"
	   		+ "6Gg6GCxMg2e5rLq3/o9v9aWaWqG1xVwR09xPysuF/WVhEzlvq1Bg1sKmXp0F2laB\r\n"
	   		+ "x6PdKNAvOuNQskgWvohXBXaaSow7TGcW0hj6cdf+Yy+OapJVNiwadSca6P9+9SNz\r\n"
	   		+ "U6R+LlhtjUKxVT14G0vWY/zguA+fClYSgGCVLIruVWrgsfVOlLvQ0GCMX+6ZQSkV\r\n"
	   		+ "4qQqC6BicB517azfoZUd4gJJZ/BSY+pMeSaILEhVE/Llet5hFHg21xrk9RZQ0aNl\r\n"
	   		+ "RQIDAQAB\r\n"
	   		+ "-----END PUBLIC KEY-----";
	   
	   
	   
	   
	   
	   static String dummyprivateKey =  "MIIEowIBAAKCAQEAzmSgXCnUA9/AxqGs92Y0zDUrtL8kuYCtkA7km8kQH85URw1j\r\n"
		   		+ "eWmjUhlTbtWnqUiUa+Zp4rcuXhKHq6h/p/6h6Gg6GCxMg2e5rLq3/o9v9aWaWqG1\r\n"
		   		+ "xVwR09xPysuF/WVhEzlvq1Bg1sKmXp0F2laBx6PdKNAvOuNQskgWvohXBXaaSow7\r\n"
		   		+ "TGcW0hj6cdf+Yy+OapJVNiwadSca6P9+9SNzU6R+LlhtjUKxVT14G0vWY/zguA+f\r\n"
		   		+ "ClYSgGCVLIruVWrgsfVOlLvQ0GCMX+6ZQSkV4qQqC6BicB517azfoZUd4gJJZ/BS\r\n"
		   		+ "Y+pMeSaILEhVE/Llet5hFHg21xrk9RZQ0aNlRQIDAQABAoIBAGm4fbP38RQy7ypP\r\n"
		   		+ "8quffPgFXGfvaT5kPYOHPW7XJR1FsUHcB9JPXEtXkbc6VfGOrgg/NInfTy78yuYR\r\n"
		   		+ "H6LL+d90VB3Bw0ekQCXMVydcDfuEYnZ+Uv/PU4p0VaMJcY8E+R7B0wOr3o7jkhjw\r\n"
		   		+ "0eivWzcKJuUbDrZZbiKEbrIWYIz48ihN++iYzMncump/Snjx5r/h27FtWU8hvKJy\r\n"
		   		+ "dzZZjrp9mVnFiwf1W8lBVWfnxozL5TUyCwbFrECxowEmFOT+sbrr9iq/iHy3EzOt\r\n"
		   		+ "1gcEf5gqoro1z8y+eyH3i4zJCf9vGllAIpp/Fcn8fW4Vh8UVwaNJBJUsL7fcpwIU\r\n"
		   		+ "zeTrh4ECgYEA7DhDrPU+emYjmr+WKXu4qthWVBD/1H+e19XRCWN3S8F0FpnXNR49\r\n"
		   		+ "e3ODjmFOAMCxxrX9jTnYlwNXamqm4WMc6OVmtPOiXn2AgzzXF47VPF6B4YF0Eiw0\r\n"
		   		+ "qSHDBHJq/TrqapE9X9+CijDgpEiWgHkUBEIxyM66B+Woibc8bluaY1UCgYEA36z6\r\n"
		   		+ "/9rCoKQx+2RHAoUt9SSR58WVH4lQOp/DbOJ0dlxsnpzyiNgxdSgLF8MSkskF0CK+\r\n"
		   		+ "1tw+cHGBoJn0HDxa0dNBQBio7N6NvPLzEpDsGlSzB9H9Qw2VpFh31ouR+HIR5Lkf\r\n"
		   		+ "S+7lKASwx1V3ERasIb5R6L0wPn/hLxj5UY6A2jECgYEAn/gibJjQrNdKuVuRd5pf\r\n"
		   		+ "nuNO4zN37RJYIdac73y6p0TlNwEL6t8Fx/qcVumyh3P3cJiCkwdflDUhF+IODYiv\r\n"
		   		+ "ZVElUtnDVbYsN7QV/dAuu+lMj8i5MC1bpEZG6VYK+l7RrUWaiLi2kJs1Jkw6D9rv\r\n"
		   		+ "IUsqp9DHzt1KW6kJdbr3PNkCgYBsbvRjco7lx5O/dlaCX0PvUqI1BPEND8lIEklw\r\n"
		   		+ "ELw64QV7Q0TEmGIplJcEFvp7FF7wDtgLW0wdwLkBvYC863lvvQUl6I6D/kQiNHko\r\n"
		   		+ "J/99qE/+W4t2pTBFMuotpQ3Hrq0D/8avTgnjg5qbl9AYBbC+zv/wXz7zHZVx+p5r\r\n"
		   		+ "LjVDEQKBgGHFjEZOPHOfjVo5w9oi5CcxQQdENtFWbRt+ziNCpOdWYoJLCNj8KWnJ\r\n"
		   		+ "zsDriCMdcYt5ibbKjzJFTx4lbAHdCC8B6y0JfzGRDQkm4W45UT9YrrmkMWMx2CBu\r\n"
		   		+ "1MgTV8lCXP7RZugaEZOCCAtVDE040WRpsJMdoERBU/hta1VtsbaW";
		  // private String publicKey = "public key";
		   static String dummyPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzmSgXCnUA9/AxqGs92Y0\r\n"
		   		+ "zDUrtL8kuYCtkA7km8kQH85URw1jeWmjUhlTbtWnqUiUa+Zp4rcuXhKHq6h/p/6h\r\n"
		   		+ "6Gg6GCxMg2e5rLq3/o9v9aWaWqG1xVwR09xPysuF/WVhEzlvq1Bg1sKmXp0F2laB\r\n"
		   		+ "x6PdKNAvOuNQskgWvohXBXaaSow7TGcW0hj6cdf+Yy+OapJVNiwadSca6P9+9SNz\r\n"
		   		+ "U6R+LlhtjUKxVT14G0vWY/zguA+fClYSgGCVLIruVWrgsfVOlLvQ0GCMX+6ZQSkV\r\n"
		   		+ "4qQqC6BicB517azfoZUd4gJJZ/BSY+pMeSaILEhVE/Llet5hFHg21xrk9RZQ0aNl\r\n"
		   		+ "RQIDAQAB";
		   		


	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      System.out.println("private  //n"+privateKey);
	      System.out.println("public  //n"+publicKey);
	      return converter;
	   }
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientid).secret(clientSecret).scopes("ROLE_SYSTEMADMIN","read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
