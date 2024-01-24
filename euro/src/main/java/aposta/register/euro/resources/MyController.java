// package aposta.register.euro.resources;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import aposta.register.euro.grpc.MyGrpcClient;

// @RestController
// public class MyController {

//     @GetMapping("/hello")
//     public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
//         MyGrpcClient client = new MyGrpcClient();
//         System.out.println("Name: " + name);
//         String result = " ";
//         try{
//             result = client.sendMessage(name);
//             System.out.println("Result: " + result);
//         }catch (Exception e){
//         }
     
//         return "Hello, " + name + "!-->" + result;
//     }
// }