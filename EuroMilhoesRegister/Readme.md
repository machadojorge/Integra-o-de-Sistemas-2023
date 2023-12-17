# Cliente gRPC EuroMil

## Sobre
- Este projeto pretende a criação de um cliente . . .

## Comando para gerar os ficheiros para a implementação do cliente gRPC a partir do ficheiro proto
- python -m grpc_tools.protoc -I./protos --python_out=./register_euromil/grpc_client/ --pyi_out=./register_euromil/grpc_client/ --grpc_python_out=./register_euromil/grpc_client/ ./protos/*