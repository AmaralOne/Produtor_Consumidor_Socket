/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;

/**
 *
 * @author FlavioFilho
 */
public class Item {
    private String numero;

    Item(String valor) {
       this.numero = valor;
    }
    
    public String  get(){
        return numero;
    }
    
    public void set(String n){
        this.numero = n;
    }
}
