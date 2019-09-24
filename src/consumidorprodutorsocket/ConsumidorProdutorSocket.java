/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Arrays;

class WorkerTCP implements Runnable {
    int id;
    Socket novaConexao;
    DataInputStream entrada;
    DataOutputStream saida;
    BufferTCP bufferCompartilhado;
    
    public WorkerTCP(int id, Socket conexaoCliente,BufferTCP bufferCompartilhado) throws Exception {
        this.id = id;
        this.novaConexao = conexaoCliente;
        this.entrada = new DataInputStream(novaConexao.getInputStream());
        this.saida = new DataOutputStream(novaConexao.getOutputStream());
        this.bufferCompartilhado = bufferCompartilhado;
    }
    
    @Override
    public void run() {
        
        while (true) {
            try {
                String mensagemRecebida = entrada.readUTF();
                System.out.println(this.novaConexao.getRemoteSocketAddress().toString() 
                    + " >> " + mensagemRecebida);
                
                String[] textoSeparado = mensagemRecebida.split(";");
                
               String id = textoSeparado[0];
               String tipoOperacao = textoSeparado[1];
               String item = textoSeparado[2];
               
               
               String  mensagemEnviada = "";
               
               if(tipoOperacao.equals("0")){
                   
                   if(bufferCompartilhado.set(Integer.parseInt(id), item)){
                       mensagemEnviada = "0;1;"+item;
                   }else{
                       mensagemEnviada = "0;2;"+item;
                   }
                   
                   
               }else{
                   
                   Item dados = bufferCompartilhado.get(Integer.parseInt(id));
                   
                   if(dados != null){
                       mensagemEnviada = "0;4;"+dados.get();
                   }else{
                       mensagemEnviada = "0;5;null";
                   }
               }
               
                
             
               saida.writeUTF(mensagemEnviada);
               
                    
            } catch (Exception erro) {
                System.out.println("ERRO: " + erro.getMessage());
            }
        }
    }
}


public class ConsumidorProdutorSocket {

    /**
     * @param args the command line arguments
     */
    
    
    
     public static void main(String[] args) throws Exception {
        System.out.println("Iniciando Buffer!");
        ServerSocket server = new ServerSocket (7777);
        int novoId = 1;
        BufferTCP bufferCompartilhado = new BufferTCP();
        Painel painel = new Painel(bufferCompartilhado);
        Thread paienlThread = new Thread(painel);
        paienlThread.start();
        
        while (true) {
            Socket novaConexao = server.accept();
            System.out.println("Nova conexão!");
            WorkerTCP novoWorker = new WorkerTCP(novoId, novaConexao,bufferCompartilhado);
            novoId++;
            Thread novaThread = new Thread(novoWorker);
            novaThread.start();
        }
    }
    
}
