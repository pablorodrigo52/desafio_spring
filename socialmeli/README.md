# SocialMeli 
## Onde os compradores poderão acompanhar seus vendedores favoritos e saber todas as novidades que eles mesmos postam.

## O que é
Trata-se de exercício promovido pela Digital House referente ao aprendizado de spring boot.
## Como instalar
1 - Faça um clone do repositório
```shell
git clone git@github.com:pablorodrigo52/desafio_spring.git
```
2 - Você pode executar a aplicaçao pela sua IDE ou pelo comando abaixo na pasta raiz do projeto
```shell
./mvnw spring-boot:run
```

# Funcionalidades
## Follow
    - POST /users/{userId}/follow/{userIdToFollow}
## Unfollow
    - POST /users/{userId}/unfollow/{userIdToUnfollow}
## Count followers
    - GET /users/{userId}/followers/count/
## Followers List
    - GET /users/{UserID}/followers/list?[order=[name_asc][name_desc]]
## Followed List
    - GET /users/{UserID}/followed/list?[order=[name_asc][name_desc]]
## New post
    - POST /products/newpost
    {
        "userId":2,
        "id_post":7,
        "date":"01-07-2021",
        "detail":{
            "product_id":1,
            "productName":"Cadeira Gamer",
            "type":"Gamer",
            "brand":"Racer",
            "color":"Red & Black",
            "notes":"Special Edition"
        },
        "category":100,
        "price":1500.50
    }
## Post List by Followed 
    - GET /products/followed/{userId}/list?[order=[date_asc][date_desc]]

## Promotional Post
    - POST /products/newpromopost
    {
        "userId":1,
        "id_post":21,
        "date":"29-06-2021",
        "detail":{
            "product_id":1,
            "productName":"PROMO CADEIRA GAMER",
            "type":"Gamer",
            "brand":"Racer",
            "color":"Red & Black",
            "notes":"Special Edition"
        },
        "category":"100",
        "price":1500.50,
        "hasPromo":true,
        "discount":0.25
    }
## Count promotional post by user id
    - GET /products/{userId}/countPromo/
## Promotional posts list by user id
    - GET /products/{userId}/list?[order=[name_asc][name_desc]]
