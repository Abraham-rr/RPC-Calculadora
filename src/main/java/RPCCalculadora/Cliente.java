
package RPCCalculadora;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String args[]){
    
    String el1, el2;//se guarda la variable de entrada que dara el cliente 
    String rs = ""; //resultado de nuestra operacion 
    final int puerto = 4000; //elegimos el puerto 
    Socket s;//llamamos nuestro socket
    BufferedReader bf;//llamamos un buffer para poder leer 
    InputStreamReader inputstr; //ya con el buffer, leemos los datos de entrada del cliente 
    InputStream getin; //Declaramos nuestro flujo de entrada
    OutputStream outstr; //Declaramos nuestro flujo de salida
    DataInputStream datain; //entrada de datos primitivos
    DataOutputStream dataout; //salida de datos primitivos 
    String url = "localhost"; //usamos localhost para hotear nuestro servidor 
    
    try {
         
       s = new Socket(url, puerto); // hacemos el socket con el puero 400 y lo hosteamos con nuestro servidor local 
       inputstr = new InputStreamReader( System.in ); //lo utlizamos para nuestro buffer que recibimos la entrada del cliente
       getin = s.getInputStream(); //  ponemos nuestro flujo de entrada para utilizarlo en el socket 
       bf = new BufferedReader(inputstr); // aqui alojaremos los datos de entrada 
       outstr = s.getOutputStream();// ponemos nuestro flujo de salida para utlizarlo en el socket 
       dataout = new DataOutputStream(outstr); //salida del flujo en datos primitvos para ponerlo en el socket 
       datain = new DataInputStream(getin); //entrada del flujo en datos primitvos para ponerlo en el socket
       System.out.println("**********************************");
       System.out.println("            Calculadora           ");
       System.out.println("**********************************");
       System.out.println("");
       System.out.println("Que tipo de operacion quieres");
       System.out.println("1. Suma ");
       System.out.println("2. Resta ");
       System.out.println("3. Multiplicacion ");
       System.out.println("4. Division ");
       System.out.println("");
	el1 = bf.readLine(); // leemos el tipo de operacion que quiere el cliente 
	dataout.writeUTF(el1); //escribimos en tipo UTF para mandarlo al servidor

	System.out.println("Dame un numero para enviar al servidor: ");
	el2 = bf.readLine(); // leemos el numero para realizar la operacion 
	dataout.writeUTF(el2); //scribimos en tipo UTF para mandarlo al servidor

	System.out.println("Dame el siguiente numero para enviar al servidor: ");
	el1 = bf.readLine();
	dataout.writeUTF(el1);

	rs = datain.readUTF(); // ya que el servidor tenga la respuesta lo guardaremos en la varibale rs
        System.out.println("");
	System.out.println("El resultado es: " + rs); //mandamos a imprimir a pantalla el resultado
        System.out.println("");
	dataout.flush(); //vaciamos el bufer	
	dataout.close(); //cerramos la salida de datos al servidor 

         
    }
      catch(IOException ex){
            System.out.println("Desconexion del servidor "); //si no se puede conectar con el servidor se mnada un excpetion 
        }
     
    }
}
