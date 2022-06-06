# <strong style="color:#1f6363">gRPC </strong>-TRACER

## <p style="text-decoration: underline">Le projet : </p>

Ce projet est un POC d'utilisation de la technologie <strong style="color: #1f6363"> gRPC </strong> aussi bien coté server que coté client.

Il implémente une infrastructure de tracing de log distribué.

Cette infrastructure de tracing est constituée de 3 composants :


- <strong style="color: chocolate" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html"> Le tracing-server </strong>
    
    Il est chargé de créer et d'enregistrer les différentes traces et logs provenant de différents micro-services.
    
    Il expose aussi les informations relatives aux traces.


- <strong style="color: chocolate"> Le tracing-client </strong>

    Il sert d'intermédiaire entre les micro-services et le **Tracing-server**.
    
    C'est lui qui envoie les différentes requêtes d'enregistrement de traces ou de logs vers le **Tracing-server**.


- <strong style="color: chocolate"> Le tracing-ui </strong>
    
    C'est l'interface utilisateur permettant de visualiser les traces des différents micro-services.

<br>
<p> 
    La communication entre ces différents composants se fait via le protocole <strong style="color: #1f6363"> gRPC </strong> 
</p>

## <p style="text-decoration: underline">Comment le tester ? </p>

    git clone https://github.com/henriSedjame/grpc-tracer.git
    
    cd grpc-tracer

Sur un terminal #1
    
    make start-tracer


Sur un terminal #2

    make start-user-service

Sur un terminal #3

    make start-bet-service

<p>
La première commande lance le <strong style="color: chocolate">Tracing-server</strong>, le <strong style="color: chocolate">Tracing-UI</strong> et un  proxy <span style="font-weight: bold; color: blueviolet">Envoy </span>. Le proxy permet la communication gRPC entre le server et le web.

Les deux dernières commandes lancent deux micro-services: <span style="font-weight: bold; color:yellow">User-Service</span> et <span style="font-weight: bold; color: yellow">Bet-Service</span>.

Sur un navigateur allez sur l'url <a> http://localhost:4200</a>
</p>

<br>

<p>
Effectuez ensuite les appels http suivants : 

    POST http://localhost:9002/bets
    Content-Type: application/json
    Authorization: joe:joe1234
    
    {
      "name": "joe",
      "amount": 10
    }

&&

    POST http://localhost:9001/users/check
    Content-Type: application/json

    {
      "login" : "joe",
      "password": "joe1234"
    }

<br>

Vous devriez obtenir le résultat suivant : 👇

<img src="./docs/tracer-ui-1.png"></img>

</p>