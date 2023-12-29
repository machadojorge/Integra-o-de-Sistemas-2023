# Cliente API REST CreditBank

## Sobre
- Implementação de um cliente que, faria um pedido GET a um determinado URL, passando “credit_account_id” (o número da conta) e o “value” (montante) e, que em resposta a esse pedido, iria receber uma resposta com, a data e hora e, identificação de 16 digitos do check.

## Aspetos importantes
- é utilizado um input de dados atraves do teclado para inserir os valores (em string e double), para serem inseridos no URL e enviados no pedido Get à API REST.

- Tambem foram adicionados valores por defeito no codigo, para não ser necessário introduzir valores atraves do teclado. Para trocar entre eles, está, em comentário no "main.java", que codigo é necessário comentar e descomentar para isso acontecer.

## Como executar o cliente
- Ir até o directorio do projecto;
   - java -jar target/mavenproject-1.0.0-jar-with-dependencies.jar
- Ou
    - Na classe "main.java" clicar no "run" logo acima do metodo "public static void main(String[] args)"

## Dependencias:
- Neste projecto houve algumas dependencias nomeadamente na utilização de JSON, e foi necessário alguns packages maven que foram adicionados no ficheiro "pom.xml".
- Para abrir e correr o projecto é necessário ter instalado:
    - Java versão >= 11;
    - Maven, para gerir as dependencias dos packages

## Extrutura do Projecto
- main/java/tarefa5/creditbank/Main.java --> contem o codigo de inicialização deste projeto 
- main/java/tarefa5/creditbank/entities/ --> contem as classes responsaveis por armazenar os valores que irão ser enviados e recebidos, em objectos, do pedido GET. Isto ajuda na organização do codigo. 
- main/java/tarefa5/creditbank/Service/DigitalCheckService.java --> classe responsavel por criar o URL correto já com os parametros, fazer o pedido GET à API REST e receber a resposta e fazer o parse da mesma.
- main/java/tarefa5/creditbank/utils--> Contem uma classe de utils para converter a data do formato String para Date e vice-versa;
- target/ --> onde ter os ficheiros ".jar"
- pom.xml --> onde tem as dependencias dos pacotes maven.


# Run the project
 - java -jar target/mavenproject-1.0.0-jar-with-dependencies.jar
