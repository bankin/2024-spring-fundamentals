# Vault

## Download vault
https://developer.hashicorp.com/vault/install

## Start a server
`vault server -dev -dev-root-token-id 00000000-0000-0000-0000-000000000000`

### Verify
`vault status`

## Execute followin commands in powershell/cmd
 - powershell -> $env:VAULT_ADDR="http://127.0.0.1:8200"
 - cmd -> set VAULT_ADDR=http://127.0.0.1:8200

 - powershell -> $env:VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
 - cmd -> set VAULT_TOKEN=00000000-0000-0000-0000-000000000000

## Add dependency in build.gradle

`implementation 'org.springframework.cloud:spring-cloud-starter-vault-config:4.1.0'`

## Add configuration in application.yml

```
spring:
  cloud:
    vault:
      uri: http://127.0.0.1:8200
      token: 00000000-0000-0000-0000-000000000000
      kv:
        enabled: true
  config:
    import: vault://
```

## Open UI
http://127.0.0.1:8200/ui