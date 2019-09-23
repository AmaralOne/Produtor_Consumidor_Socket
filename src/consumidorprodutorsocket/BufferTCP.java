/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidorprodutorsocket;




public class BufferTCP {
    private Item [] conteudo =  new Item[10];;
    private int qtd = 0;
    private boolean disponivel;
 
    public synchronized boolean set(int idProdutor, String valor) {
        if(disponivel == true && qtd > 9) {
            return false;
        }
        try {
                
            // wait();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        conteudo[qtd] = new Item(valor);
        
        System.out.println("Produtor #" + idProdutor + " colocou " + conteudo[qtd].get());
        qtd++;
        disponivel = true;
        notifyAll();
        
        return true;
    }
    
    public synchronized void painel(){
        System.out.println("****************************");
        System.out.println("Qtd de itens no buffer: "+qtd);
        for(int i = 0; i<10; i++){
            if((conteudo[i] == null)){
                System.out.print(" 0");  
            }else{
                System.out.print(" "+conteudo[i].get());  
            }
         
        }
        System.out.println("");
        System.out.println("****************************");
    }
 
    public synchronized Item get(int idConsumidor) {
        if(disponivel == false) {
            return null;
        }
        try {
               
            wait();
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        qtd--;
        System.out.println("Consumidor #" + idConsumidor + " consumiu: "
                + conteudo[qtd].get());
        
        
        conteudo[qtd] = null;
        
        if(qtd == 0){
            disponivel = false;
        }
        
        
        notifyAll();
        return conteudo[qtd];
    }

    
}
