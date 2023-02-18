
package RPCCalculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
     public static void main(String args[]){
         
        int x, y; // declaramos la vaiable de entrada que seria el primer numero y el segundo 
        int rs;// declaramos la variable del resultado que nos dara despues de hacer el calculo
        rs = 0; // declaramos la variable en 0 
        String a,b,c,tp; //declaramos las variables que recibimos del cliente y lo mandaremos a imprimir en pantalla          

        /* Ponemos la palabra reervada que es ServerSocket para reliazar 
        la comunicacion entre servidor y cliente que se llamara 
        "servidor " y estara en null*/
        ServerSocket servidor = null; 
        Socket s = null; //llamamos nuestro socket
        final int puerto = 4000; //elegimos el puerto 
        InputStream getin; //Declaramos nuestro flujo de entrada
        OutputStream getout; //Declaramos nuestro flujo de salida 
        DataInputStream datain; //entrada de datos primitivos
        DataOutputStream dataout; //salida de datos primitivos  
        
        try{
            servidor = new ServerSocket(puerto); //creamos el socket del servidor y lanzamos el puerto que asignamos 
            s = servidor.accept(); //queda en la espera del cliente
            getin = s.getInputStream(); //devuelve la entrada para el socket que estamos utlizando 
            getout = s.getOutputStream(); //devuelve la salida para el socket que estamos utlizando 
            datain = new DataInputStream(getin); // 
            dataout = new DataOutputStream(getout); //
            System.out.println("**********************************");
            System.out.println("     Servidor de la calculadora   ");
            System.out.println("**********************************");
            System.out.println("");
            System.out.println("Modo espera...");
            System.out.println("");
                        
                        //guardara la entrada de datos del cliente y se decodifica de UTF para leerlo y despues mandarlo a imprimir
			tp = datain.readUTF(); 
                        System.out.println("");
			System.out.println("¿Que tipo de operacion eligio el Cliente? : ");
                        System.out.println("");
                        System.out.println(tp);
			System.out.println("Esperando el primer numero dado por el cliente ...");
			a = datain.readUTF(); 
			System.out.println("");
			System.out.println(a); 
			System.out.println("");
			System.out.println("Esperando segundo numero dado por el cliente...");
			b = datain.readUTF();
			System.out.println("");
			System.out.println(b);
			
                        x = Integer.parseInt(a); //recibiendo el primera entrada de dato dado por el cliente, lo pasamos  a dato entero 
			y = Integer.parseInt(b);//      ||       segunda entrada                 ||                               

            switch (tp) { //dependiendo el tipo de operacion que eligio el cliente, utilizamos un swtich para realizar la operacion 
                case "1":
                    rs = x + y; //la respuesta se guarda en res
                    break;
                case "2":
                    rs = x - y;
                    break;
                case "3":
                    rs = x * y;
                    break;
                case "4":
                    rs = x / y;
                    break;
                default:
                    System.out.print("Error"); //si no hay una respuesta correcta, mandamos un error en pantalla 
                    break;
            }

			System.out.println("El resultado es: " + rs); // mandamos a imprimir el resultado
			dataout.writeUTF(Integer.toString(rs));//mandamos la respuesta al cliente

			s.close(); //cerramos socket 
			datain.close();	//cerramos la entrada de datos dada por el cliente	
        
        
        }
        
        catch(IOException ex){ //si no se puede conectar con el cliente se mnada un excpetion 
            System.out.println("Desconexion del cliente ");
        }
    }
}
