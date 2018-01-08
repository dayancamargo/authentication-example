
- How to Use
  The project has some examples, just import im postman :)

    - To _get token_:

        POST *http://localhost:8099/login*

        Headers :

            Authorization : basic(client:secret) -> clitato // potatosecret
            Content-Type : application/x-www-form-urlencoded

        Body :

            grant_type : password
            username : user name ( USER1 )

    - To _token refresh_:
        POST *http://localhost:8099/login*

            Headers :

                Authorization : basic(client:secret) -> clitato // potatosecret
                Content-Type : application/x-www-form-urlencoded

            Body :

                grant_type : refresh_token
                client_id : client id ( clitato )
                refresh_token : refresh token recieved earlier


    - Test some security service:

        GET *http://localhost:8099/user*

            Header :
            Authorization : Bearer {access_token}

        GET *http://localhost:8099/user/POTATO*

            Header :
            Authorization : Bearer {access_token}


* How to use in another service

    - In another service, on the main class use this *@EnableResourceServer* annotation, on its application.properties (if springboot application) put this:

            security.oauth2.resource.userInfoUri=http://localhost:8099/usuario/user

    (this endpoint is one which returns a "Principal class")

    - Implements something like that (dependencies are basically the same as this project):

           @Configuration
           public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

               @Override
               public void configure(HttpSecurity http) throws Exception {
                   http
                       .authorizeRequests()
                       .antMatchers("/user/**").hasRole("BATATA") //sets a role for this endpoint
                       .anyRequest().authenticated();             //all endpoints must have a authorization token autenticated
                   ;
               }

               ... another methods...
           }


Resources:

* Site to manipulate a jwt token

    https://jwt.io/

* Jasypt

    Homepage: http://www.jasypt.org/

    Command tutorial (bahs/cmd): http://www.jasypt.org/cli.html

    Examples:

        ./encrypt.sh input="This is my message to be encrypted" password=MYPAS_WORD

         ----ENVIRONMENT-----------------

        Runtime: Sun Microsystems Inc. Java HotSpot(TM) Client VM 1.6.0_03-b05


         ----ARGUMENTS-------------------

        input: This is my message to be encrypted
        password: MYPAS_WORD


         ----OUTPUT----------------------

        k1AwOd5XuW4VfPQtEXEdVlMnaNn19hivMbn1G4JQgq/jArjtKqryXksYX4Hl6A0e

        $ ./encrypt.sh input="This is my message to be encrypted" password=MYPAS_WORD verbose=false
        uv9+BnQFuZbfTV5Kf45oBOr0eJzBW5AS+XaYY+Lu5XWYhGgl0Ee41P0QUGpIrfyD


* Spring Security official docs
    http://projects.spring.io/spring-security-oauth/docs/oauth2.html

* Diferences about Role x Authority on Spring Security

    http://www.baeldung.com/spring-security-granted-authority-vs-role

    https://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security

* Usefull links

    https://stackoverflow.com/questions/19655911/request-new-access-token-using-refresh-token-in-username-password-grant-in-sprin

    https://www.jamasoftware.com/blog/spring-security-oauth-multi-tenant/
