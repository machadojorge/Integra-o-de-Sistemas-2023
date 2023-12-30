from loguru import logger
from grpc_client.grpc_client import GrpcClient
from register_data.comunication_data import CommunicationData
from connect_class.connect_gRPC import ConnectGrpc

def main():
    # # este codigo é apenas para introduzir os dados ( KEY e CHECK_ID) atraves de inputs
    # # do teclado, e usar esses dados que foram introduzidos atraves do teclado, 
    # # para enviar para o servidor como "KEY" e "CHECK_ID"
    # # Basta descomentar este código e comentar as linhas 20 e 21
    # data = CommunicationData()
    # data.insert_data_from_keyword()
    # start_Conection = ConnectGrpc("localhost", 5050)
    # start_Conection.sendKeyCheck(data.get_key(), data.get_check_id())
    ########################################################

    # # Este código define por defeito o valor do (KEY, CHECK_ID). Para os introduzir
    # # por inputs do teclado, basta descomentar o bloco de código anterior e 
    ## commentar as linhas seguintes (20, 21)
    start = ConnectGrpc("localhost", 5050)
    start.sendKeyCheck("KEY", "123456789789456")

if __name__=="__main__":
    main()