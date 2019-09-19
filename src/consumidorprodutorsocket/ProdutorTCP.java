/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;

import java.io.OutputStream;
import static java.lang.Math.random;
import java.net.Socket;
import java.util.Random;
import java.util.Random;
import java.util.logging.Logger;
/**
 *
 * @author FlavioFilho
 */
public class ProdutorTCP {
  
     public static void main(String[] args) throws Exception {
         try{   
                int id = new java.util.Random().nextInt(1000);
                System.out.println("Conectando ao servidor...");
                Socket conexaoServidor = new Socket("127.0.0.1", 7777);
                System.out.println("Servidor conectado!");
                OutputStream saida = conexaoServidor.getOutputStream();
                String mensagem = "Eu sou o cliente " + id;
                byte[] dadosEnviados = mensagem.getBytes();

                while(true) {
                    int numero =  new java.util.Random().nextInt(5);
                    numero++;
                    Thread.sleep(1000*numero);
                    saida.write(dadosEnviados);
                    saida.flush();
                    System.out.println("Mensagem foi enviada com sucesso!");
                }
             
         }catch(Exception e){
              System.out.println("Erro ao tentar conectar ao Servidor!");
         }
       
        //conexaoServidor.close();
    }
}
