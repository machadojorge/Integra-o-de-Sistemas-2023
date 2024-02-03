package aposta.register.euro.grpc;

import euromil.*;
import euromil.EuromilOuterClass.RegisterRequest;
import euromil.EuromilOuterClass.RegisterReply;
import euromil.EuromilGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class ClientEuromil {
    
    // atributos privados que vao armazenar o channel gRPC e o Stub gRPC
    private final ManagedChannel channel;
    private final EuromilGrpc.EuromilBlockingStub blockingStub;

        public ClientEuromil() 
        {
            // Crie um canal gRPC para o servidor com o Host e a Porta
            channel = ManagedChannelBuilder.forAddress("ken.utad.pt", 8282) 
                    .usePlaintext()
                    .build();
    
            // Criar um stub gRPC para fazer chamadas ao servidor
            blockingStub = EuromilGrpc.newBlockingStub(channel);
        }
    

        // Método responsavel por chamar o método do Serviço gRPC para enviar o pedido ao Servidor
        public String registerEuroMil(String key, String checkid) {
           
            // Crie uma mensagem de solicitação
            RegisterRequest request = RegisterRequest.newBuilder().setKey(key).setCheckid(checkid).build();
            System.out.println("Vou tentar o gRPC: KEY:  "+ key + " --> CheckId: " + checkid );
            
            // Fazer a chamada gRPC e receba uma resposta
            RegisterReply response = null;
            try{
                response = blockingStub.registerEuroMil(request);
            }
            catch (Exception e){
            System.out.println("Erro:" + e.getMessage());
            }
            return response.getMessage();
        }
    
        
        // Encerrar o canal quando já não é necessário
        public void shutdown() {
            
            channel.shutdown();
        }
}
