// package aposta.register.euro.grpc;

// import apostas.euromil.grpc.Euromil.*;
// import apostas.euromil.grpc.Euromil.HelloRequest;// HelloRequest;
// import apostas.euromil.grpc.Euromil.HelloResponse;
// import apostas.euromil.grpc.MyServiceGrpc;
// import io.grpc.ManagedChannel;
// import io.grpc.ManagedChannelBuilder;
// import org.springframework.stereotype.Service;

// @Service
// public class MyGrpcClient {

//     private final ManagedChannel channel;
//     private final MyServiceGrpc.MyServiceBlockingStub blockingStub;

//     public MyGrpcClient() {
//         // Crie um canal gRPC para o servidor
//         channel = ManagedChannelBuilder.forAddress("localhost", 9090) // substitua localhost e 9090 pelo seu endereço e porta gRPC
//                 .usePlaintext()
//                 .build();

//         // Crie um stub gRPC para fazer chamadas ao servidor
//         blockingStub = MyServiceGrpc.newBlockingStub(channel);
//     }

//     public String sendMessage(String name) {
//         // Crie uma mensagem de solicitação
//         HelloRequest request = HelloRequest.newBuilder().setName(name).build();
//         System.out.println("Vou tentar o gRPC: " + request.getName());
//         // Faça uma chamada gRPC e receba uma resposta
//         HelloResponse response = null;
//         try{
//             response = blockingStub.sayHello(request);
//             // Exiba a resposta do servidor
//         System.out.println("Resposta do servidor: " + response.getGreeting());
//         }
//         catch (Exception e){
//         System.out.println("Erro:" + e.getMessage());
//         }

        
//         return response.getGreeting();
//     }

//     public void shutdown() {
//         // Encerre o canal quando não for mais necessário
//         channel.shutdown();
//     }
// }
