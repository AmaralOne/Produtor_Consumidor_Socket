/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
                DataOutputStream saida = new DataOutputStream(conexaoServidor.getOutputStream());
                DataInputStream entrada = new DataInputStream(conexaoServidor.getInputStream());
                String mensagem = "";
                int item = 0;
                while(true) {
                    int numero =  new java.util.Random().nextInt(5);
                    numero++;
                    Thread.sleep(1000*numero);
                    mensagem = ""+id+";0;item"+id+": "+item;
                    boolean status = true;
                    item++;
                    while(status){    
                        saida.writeUTF(mensagem);
                        saida.flush();
                        System.out.println("Mensagem foi enviada com sucesso!");
                        

                        String mensagemRecebida = entrada.readUTF();
                        System.out.println("Mensagem Recebida >> " + mensagemRecebida);

                        String[] textoSeparado = mensagemRecebida.split(";");

                        String idBuffer = textoSeparado[0];
                        String tipoOperacao = textoSeparado[1];
                        String itemBuffer = textoSeparado[2];

                        if(tipoOperacao.equals("1")){
                            status = false;
                        }
                    }      
                }
             
         }catch(Exception e){
              System.out.println("Erro ao tentar conectar ao Servidor!");
         }
       
        //conexaoServidor.close();
    }
}
