# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import euromil_pb2 as euromil__pb2


class EuromilStub(object):
    """The euromil service definition.

    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.RegisterEuroMil = channel.unary_unary(
                '/euromil.Euromil/RegisterEuroMil',
                request_serializer=euromil__pb2.RegisterRequest.SerializeToString,
                response_deserializer=euromil__pb2.RegisterReply.FromString,
                )


class EuromilServicer(object):
    """The euromil service definition.

    """

    def RegisterEuroMil(self, request, context):
        """Sends a greeting

        """
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_EuromilServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'RegisterEuroMil': grpc.unary_unary_rpc_method_handler(
                    servicer.RegisterEuroMil,
                    request_deserializer=euromil__pb2.RegisterRequest.FromString,
                    response_serializer=euromil__pb2.RegisterReply.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'euromil.Euromil', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class Euromil(object):
    """The euromil service definition.

    """

    @staticmethod
    def RegisterEuroMil(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/euromil.Euromil/RegisterEuroMil',
            euromil__pb2.RegisterRequest.SerializeToString,
            euromil__pb2.RegisterReply.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)