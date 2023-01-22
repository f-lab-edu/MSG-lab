# 가이드

## application.yml 설정법

### Datasource 설정

```yml
  datasource:
    driver-class-name: com.your.driver # MySQL은 com.mysql.cj.jdbc.Driver
    url: { 프로토콜 }://{주소}:{포트}/{데이터베이스 이름} # 주로 프로토콜은 jdbc:mysql 사용
    username: # 접속할 유저 이름
    password: # 유저의 암호
```

### FCM 설정

```yml
  fcm:
    auth: key=blahblah # fcm authkey 입력
    url: https://fcm.googleapis.com/fcm/send # fcm url 입력
```

### RabbitMQ 설정

```yml
  rabbitmq:
    host: aaa.com # 호스트 주소
    port: 5678 # 호스트 포트
    username: jhone # 유저네임
    password: pw1234 # 유저암호
```