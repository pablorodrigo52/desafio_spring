# SocialMeli 
## Onde os compradores poderão acompanhar seus vendedores favoritos e saber todas as novidades que eles mesmos postam.


# Caracteristicas
- USER == SELLER

# Funcionalidades
## SIGN - Seguir um usuário
    - POST /users/{userId}/follow/{userIdToFollow}
        Status Code 200 (tudo OK)
        Status Code 400 (Bad Request)

## Count followers - Contador de seguidores
    - GET /users/{userId}/followers/count/    	
    {
        "userId": 1569,
        "userName": "vendedor1",
        "followers_count": 35
    }

