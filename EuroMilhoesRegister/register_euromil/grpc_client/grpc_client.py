
import grpc
import euromil_pb2_grpc
import euromil_pb2
from loguru import logger

class GrpcClient():

    def __init__(self):
        pass

    def create_channel(self, host:str, port:int):
        self.channel = grpc.insecure_channel('{}:{}'.format(host, port))
        self.stub = euromil_pb2_grpc.EuromilStub(self.channel)
    
    def sendMessage(self, key: str, check_id:str):
        print("""""")
        print(str(key))
        print(str(check_id))
        request = euromil_pb2.RegisterRequest(key=key,checkid=check_id )

        try:
            response = self.stub.RegisterEuroMil(request)
            return response
        except Exception as e:
            logger.error("Error to send the messsage to the Server {}".format(e))
            return None