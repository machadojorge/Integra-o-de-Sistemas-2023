from loguru import logger
from grpc_client.grpc_client import GrpcClient


class ConnectGrpc():
    
    # cria um objecto da classe GrpcClient, que é a classe gRPC client
    ## e cria o channel e o stub, nessa classe, para a ligação com o servidor
    def __init__(self, host: str, port: int):
        self.__client_grpc = GrpcClient()
        self.__client_grpc.create_channel(host, port)

    def sendKeyCheck(self, key:str, check:str):
        try:
            response = self.__client_grpc.sendMessage(key, check)
            if response != None:
                logger.success("EuroMil as register with {}".format(response))
            else:
                logger.warning("Error to get the response from the Server {}".format(response))
        except Exception as e:
            logger.error("Error to send the messsage to the Server {}".format(e))
