from loguru import logger
from grpc_client.grpc_client import GrpcClient
from register_data.comunication_data import CommunicationData
from connect_class.connect_gRPC import ConnectGrpc

def main():
    # este codigo é apenas para introduzir os dados ( KEY e CHECK_ID) atraves de inputs
    # do teclado, e usar esses dados que foram introduzidos atraves do teclado, 
    # para enviar para o servidor como "KEY" e "CHECK_ID"
    data = CommunicationData()
    data.insert_data_from_keyword()
    start_Conection = ConnectGrpc("localhost", 5050)
    start_Conection.sendKeyCheck(data.get_key(), data.get_check_id())

    # Caso seja necessário defenir os dados do (KEY, CHECK_ID) por defeito, basta
    # commentar o código anterior e descomentar as seguinte linhas
    # start = ConnectGrpc("localhost", 5050)
    # start.sendKeyCheck("KEY", "Value")

if __name__=="__main__":
    main()