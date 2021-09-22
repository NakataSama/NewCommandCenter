# New Command Center

Bem vindo ao novo centro de comando da NASA! Para testar nosso lançamento de veículos para um terreno em Marte.

## Classes

### Field

Essa classe representa o campo no qual os veículos serão inseridos, possui os atributos `id`, `position` e `vehicles` . Esta classe possui métodos `get`para os atributos acima, assim como os métodos:

- `saveVehicle` - este método irá salvar na lista da classe Field, um objeto derivado da classe abstrata `Vehicle` , ele possui algumas validações como se há um outro veículo na posição, ou se o veículo irá ultrapassar os limites do campo.
- `isVehiclePositionWithinFieldLimits` - método que verifica se o veículo estará dentro das proporções do campo
- `isPositionAvailable` - método que verifica se algum veículo ja está na posição que será afetada por outro veículo
- `getVehicleById` - método que retorna um veículo através de um Id pesquisado

### Vehicle

Esta classe abstrata representa os atributos e métodos comuns entre os veículos que serão inseridos futuramente, qualquer classe de veículo deverá herdar atributos e métodos desta classe. Cada atributo, `id`,`name`,`position`,`orientation` tem seu método `get`, esta classe também possui os seguintes métodos abstratos:

- `move` - método que irá implementar lógica de movimento do veículo
- `rotate` - método que irá implementar lógica de rotação do veículo

### Orientation

Este enum representa as direções cardinais, necessárias para guiar o veículo para a direção correta, cada valor deste enum possui `id` e `description` , há também um Map estático que irá mapear todos os valores de Orientation em um único lugar. Além dos atributos terem seus reespectivos métodos `get`, este enum possui os seguintes métodos:

- `getOrientationById` - este método irá retornar um enum Orientation tendo como parâmetro o id passado
- `changeOrientationFromDirectionInput` - este método faz parte da lógica de rotacionar um veículo, ele irá retornar uma nova orientação baseada na direção que foi passada por parâmetro, no nosso caso, L de Left, e R de Right.

### Position

Esta classse representa a posição de um veículo, assim como representa o limite de tamanho de um Field. Possui apenas 2 atributos, representando coordenadas: `x` e `y` . Esta classe também possui o seguinte método:

- `changeCurrentVehiclePosition` - este método irá retornar uma nova posição, que será alterava seguindo como parâmetro a Orientation passada, ou seja, a direção cardinal que está indicada no parâmetro

### Probe

Esta classe representa um veículo, no caso uma sonda. Além de herdar atributos e métodos da classe Vehicle, ele também possui os atributos `movements` e `field` , que são feitos para contagem de movimentos e ligar um veículo à um campo reespectivamente. Esta classe possui a lógica de movimentação e rotação, métodos herdados da classe `Vehicle`.

### CommandCenter

Esta classe representa o nosso centro de comando, nela estão apenas alguns atalhos para construção de campo e veículos, mas também está a responsabilidade de executar os comandos (input do usuário). O nosso centro de comando possui os seguintes métodos:

- `menu`- este método serve para criar uma representação visual do sistema, lendo inputs do terminal para executar os comandos dos veículos
- `executeCommand`- este método irá receber um id e um comando (String)  e executar este comando especificado, passando por algumas validações.
- `buildCommandCenter` - este método é responsavel pela criação do campo e dos veículos que estarão atrelados a este campo. Ele se utiliza dos métodos `buildField` e `buildVehicles` para estas criações.
- `buildField` - este método irá criar um novo objeto Field, seguindo os parâmetros definidos pelo usuário na classe App
- `buildVehicles` - este método irá construir um número de veículos, definido pelo usuário, e atrela-los ao campo gerado
- `isValidCommand` - este método avalia se o comando possui os caractere correto
- `printFieldStatus` - este método irá mostrar na tela o status campo criado, juntamente com cada veículo dentro dele

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
