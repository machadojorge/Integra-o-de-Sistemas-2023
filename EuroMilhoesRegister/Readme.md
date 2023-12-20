# Cliente gRPC EuroMil

## Sobre
- Este projeto pretende a criação de um cliente . . .

## Aspetos importantes
- é utilizado um input de dados atraves do teclado para inserir os valores (em string), para serem enviados via gRPC para o servidor.

- Tambem foram adicionados valores por defeito no codigo, para não ser necessário introduzir valores atraves do teclado. Para trocar entre eles, está, em comentário no __main__.py, que codigo é necessário comentar e descomentar para isso acontecer.

## Como executar o cliente
- Ir até o directorio do projecto;
    - python3 register_euromil
- Ou
    - pipenv run start

## Dependencias:
- No ficheiro Requirements.txt estão todos os pacotes python essenciais para correr este projeto. Caso seja necessário instalar algum pode ser instalado individualmente:
    - pip install <nome do pacote>
- Instalar em ambiente virtual (virtualenv):
    - pipenv install <nome do pacote>

- Instalar todos:
    - pip instal -r Requirements.txt
    - Ou em ambiente virtual (virtualenv):
    - pipenv install -r Requirements.txt 

## Extrutura do Projecto
- protos -- euromil.proto  --> contem o ficheiro proto
- register_euromil --> pasta do projeto.
- connect_class -- connect_gRPC.py --> classe de ligação entre "main" e a class "GrpcClient".
- grpc_client -- pasta que contem os ficheiros gerados a partir do ficheiro proto, com o comando no final deste Readme. Também tem o ficheiro "grpc_client" onde está o codigo que implementa o nosso cliente gRPC, inclusive o metodo que vai enviar os dados para o servidor.
- register_data --> uma pasta que contem uma classe onde são armazenados os dados introduzidos do teclado, é apenas uma classe auxiliar para ficar mais organizado o codigo.
- Requirements --> é o ficheiro onde estão todos os packages utilizados neste projeto.

## Comando para gerar os ficheiros para a implementação do cliente gRPC a partir do ficheiro proto
- python -m grpc_tools.protoc -I./protos --python_out=./register_euromil/grpc_client/ --pyi_out=./register_euromil/grpc_client/ --grpc_python_out=./register_euromil/grpc_client/ ./protos/*
