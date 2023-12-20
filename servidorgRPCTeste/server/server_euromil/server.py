from loguru import logger
import euromil_pb2
import euromil_pb2_grpc
import grpc
from concurrent import futures

class ServerService(euromil_pb2_grpc.EuromilServicer):
    
    def RegisterEuroMil(self, request, context):
        logger.info("Message Received!")
        key = request.key
        check = request.checkid
        logger.info(f"Registering euromil with key {key} and check {check}")
        response = euromil_pb2.RegisterReply(message="EuroMillhões Registado!")
        return response
    


def run_server():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    euromil_pb2_grpc.add_EuromilServicer_to_server(ServerService(), server)
    server.add_insecure_port("[::]:5050")
    print("Server running on port 5050")
    server.start()
    server.wait_for_termination()