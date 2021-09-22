# New Command Center

Bem vindo ao novo centro de comando da NASA! Para testar nosso lançamento de veículos para um terreno em Marte, siga as seguintes instruções:

## Construindo o campo

No método **main**, localizado na classe **App**, temos o seguinte trecho de código:

```java
int x = 20; //Type your field horizontal size here;
int y = 20; //Type your field vertical size here;
int numberOfVehicles = 2; //Type how many vehicles would you like to deploy on the field
```

Edite as informações acima com a sua preferência, vale lembrar que o valor de X e Y não pode ser menor ou igual a 0, assim como o número de veículos.

Será criado automaticamente o seu campo e os veículos que irão pertencer a ele (cada um com sua posição aleatória dentro do campo).

## Executando comandos para os veículos

Após a construção de campo e veículos, o sistema irá pedir para você escolher o ID de veículo para o comando ser executado, você pode olhar os ID's através do próprio console. Após a resposta válida (id existente), ele irá pedir o seu comando, fique livre para escrever quantos comandos quiser, contanto que a resposta possua apenas os caracteres L, R e M.

```
Choose which vehicle to command by typing ID
<seu_id_aqui>
Type your command (valid characters = L, R, M)
<seu_comando_aqui>
```

Após a execução do comando validado, o sistema irá perguntar se você gostaria de escrever mais comandos, ou não, responda com Y ou N para continuar, responda com P para mostrar como está o seu campo e os veículos dentro dele.

```
Continue with more commands?
Respond with y [yes] or n [no]
Respond with p to print the field status
<sua_resposta_aqui>
```
