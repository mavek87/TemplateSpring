
# Keycloack

Keycloack addresses (RSA public key):

http://localhost:8888/realms/master/.well-known/openid-configuration

### Configurations

### Postman configurations

POST http://localhost:8888/realms/master/protocol/openid-connect/token (body => x-www-form-urlencoded)
grant_type: (client_credentials)
client_id: (client_id)
client_secret: (client_secret)
scope: openid email profile


