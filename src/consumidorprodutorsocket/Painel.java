/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FlavioFilho
 */
public class Painel implements Runnable{

    private BufferTCP bufferCompartilhado;
     public Painel(BufferTCP bufferCompartilhado) throws Exception {
        
        this.bufferCompartilhado = bufferCompartilhado;
    }
     public void run() {
         while(true){
             try { 
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
            }
            bufferCompartilhado.painel();
        }
     }
}
