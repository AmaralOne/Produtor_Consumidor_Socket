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

class WorkerTCP implements Runnable {
    int id;
    Socket novaConexao;
    DataInputStream entrada;
    DataOutputStream saida;
    
    public WorkerTCP(int id, Socket conexaoCliente) throws Exception {
        this.id = id;
        this.novaConexao = conexaoCliente;
        this.entrada = new DataInputStream(novaConexao.getInputStream());
        this.saida = new DataOutputStream(novaConexao.getOutputStream());
    }
    
    @Override
    public void run() {
        
        while (true) {
            try {
                String mensagemRecebida = entrada.readUTF();
                System.out.println(this.novaConexao.getRemoteSocketAddress().toString() 
                    + " >> " + mensagemRecebida);
                
               String  mensagem = "recebido";
               saida.writeUTF(mensagem);
                    
            } catch (Exception erro) {
                System.out.println("ERRO: " + erro.getMessage());
            }
        }
    }
}


public class BufferTCP {
     public static void main(String[] args) throws Exception {
        System.out.println("Iniciando Buffer!");
        ServerSocket server = new ServerSocket (7777);
        int novoId = 1;
        while (true) {
            Socket novaConexao = server.accept();
            System.out.println("Nova conexão!");
            WorkerTCP novoWorker = new WorkerTCP(novoId, novaConexao);
            novoId++;
            Thread novaThread = new Thread(novoWorker);
            novaThread.start();
        }
    }
}
