# Keycloak

keycloak addresses (RSA public key):

http://localhost:8888/realms/master/.well-known/openid-configuration

### Keycloak Postman configurations

POST http://localhost:8888/realms/master/protocol/openid-connect/token (body => x-www-form-urlencoded)
grant_type: (client_credentials)
client_id: (client_id)
client_secret: (client_secret)
scope: openid email profile

### Verify Keycloak access token

```java

public PublicKey generatePublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
    KeyFactory kf = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec pubKeySpecX509EncodedKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsDLfKvAKR+mGzMY+5YX0fA+28uuR7AGZk2aYMqQtxbSGCr4W6xdqNjtKdrGItHEVYsF2iUMRpQMNCWyqN4RzmImKQs2NijOsnVe9hwe+nu/H+p2bYryHg8RjtuODcr5sFyEXfgEK1Li8Vzb3jjieYDMJoG1ROzx+VHEr39VfkXuoYM47cYcIhRoEvpuuJ6oyry40b39yBGZaAlcYg4qfl5V0ln3QPF9n+DdP4K+y+ejaxhRaKC3h2u16grFasqW6dWQeUCa0lzOjSIDFzcrwcQQDvPsktRG0Y5GpkxRUXumLNjrpizYCFd6UhfFOY2D8F5DAX3zl+4gNqChKv0SxcQIDAQAB"));
    return kf.generatePublic(pubKeySpecX509EncodedKeySpec);
}

@Test
public void verifyAccessTokenJwtKeycloak() throws NoSuchAlgorithmException, InvalidKeySpecException {
    String accessTokenJwt = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJDamdrdzZPWmNlZS1Vd2V0NkdzN0ZVRVdSU2t0SUJZYzZYMXZGN2Z3SEFFIn0.eyJleHAiOjE3MjczNDY2MjIsImlhdCI6MTcyNzM0NjMyMiwianRpIjoiMGM4ZTFiZjYtMjExYi00Yzc3LWE2YTEtYmNjZDMyMjYxMDhmIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4ODg4L3JlYWxtcy9qYXZhbGluLXRlc3QiLCJhdWQiOiJqYXZhbGluLWFwaSIsInN1YiI6IjU1YmZiM2QzLTQ2YzAtNDlmOC1iZGViLWU4ZTY1NWI2ZjYzNyIsInR5cCI6IklEIiwiYXpwIjoiamF2YWxpbi1hcGkiLCJhdF9oYXNoIjoiN3VJTmlNSU9sQVMwZVl3ZmZxZXFfUSIsImFjciI6IjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudEhvc3QiOiIxNzIuMTguMC4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LWphdmFsaW4tYXBpIiwiY2xpZW50QWRkcmVzcyI6IjE3Mi4xOC4wLjEiLCJjbGllbnRfaWQiOiJqYXZhbGluLWFwaSJ9.CMN9v0XGnDCSY5fTs7OGnCO-mb1u12hbdCwaIPUUtXt0Z7Tr0Rawyc02FZTGbjjTaOhIqYX3LjARRUgPyKOkbc77z6Xsn6dnIpb-BG39qa7ln8EI1jyiCLE-vojQhTz_CfuPj8L-eARtmA1zXVvn7-HVdb4dbrOgkrtivTVP7dVioqU-tkidFi84m7jdCtF2aLjyvbi6-Oq8K2y5x1AV92g34XeXOnceWa3KKLUt5VLYSTvSDlncUc2JnJiNA6G0JrU160qZhZpuf5FOSFbgp27BIGtNKzcjUjUOt36P6XyFs8s88vr56Yh8BHJ-E3MGBQu4NPr8tI15SrwoD2g4aw";

    JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(generatePublicKey()).build();
    Jws<Claims> headerClaimsJwt = jwtParser.parseClaimsJws(accessTokenJwt);

    System.out.println(headerClaimsJwt);
}
```

## Reference material

### Keycloak:

- https://medium.com/@disa2aka/docker-deployments-for-keycloak-and-postgresql-e75707b155e5

### Jib:

- https://contact-rajeshvinayagam.medium.com/build-containers-with-jib-simplify-containerization-workflow-da5f3d192e64