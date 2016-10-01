
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcio
 */
public class Programa {

    /**
     * @param proc
     * @return 
     */
    public static Processo[] ordenaTempo(Processo[] proc){
        Processo aux;
        for (Processo proc1 : proc) {
            for(int j=0;j<proc.length-1;j++){
                if(proc[j].getTempoExe() > proc[j+1].getTempoExe()){
                    aux = proc[j];
                    proc[j] = proc[j+1];
                    proc[j+1] = aux;
                }
            }
        }
        return proc;                                                                                                                                
    } 
    
    public static Processo[] ordenaPrioridades(Processo[] proc){        
        Processo aux;        
        for (Processo proc1 : proc) {
            for(int j=0;j<proc.length-1;j++){
                if(proc[j].getPrioridade() < proc[j+1].getPrioridade()){
                    aux = proc[j];
                    proc[j] = proc[j+1];
                    proc[j+1] = aux;
                }else if(proc[j].getPrioridade() == proc[j+1].getPrioridade()){
                    if(proc[j].getOrdem() > proc[j+1].getOrdem()){
                        aux = proc[j];
                        proc[j] = proc[j+1];
                        proc[j+1] = aux;
                    }
                }
            }
        }
        return proc;
    }      
    
    public static boolean verifica1(Processo[] proc){
        for (Processo proc1 : proc) {
            if (proc1.getTempoExe() > 1) {
                return true;
            }
        }
        return false;        
    }
    
     public static boolean verifica2(Processo[] proc){         
        for (Processo proc1 : proc) {
            if (proc1.getPrioridade() > 0) {
                return true;
            }
        }
        return false;        
    }
     
    public static boolean contaAtivos(Processo[] proc){
        int ativos = 0;
        for (Processo proc1 : proc) {
            if (proc1.getTempoExe() > 0) {
                ativos++;
            }
        }
        return ativos > 1;
    } 
    
    public static boolean contaAtivos2(Processo[] proc){
        int ativos = 0;
        for (Processo proc1 : proc) {
            if (proc1.getPrioridade() > 0) {
                ativos++;
            }
        }
        return ativos > 1;
    } 
    
    public  static boolean salvaContexto1(Processo[] proc, int p, boolean conta ){
        if(p<proc.length-1){
             return proc[p].getPid() != proc[p+1].getPid() && proc[p].getTempoExe()>0 && conta ;
        }else if(p == proc.length-1){
             return proc[p].getPid() != proc[0].getPid() && proc[p].getTempoExe()>0 && conta;
        }
        return false;
    }
    
    public static boolean salvaContexto2(Processo[] proc, boolean conta){        
        if(conta == true){
            if(proc[1].getPrioridade() > proc[0].getPrioridade() && proc[0].getPrioridade() > 0 ){
                 return true;
            }else if(proc[1].getPrioridade() >= proc[0].getPrioridade()){
                return proc[1].getOrdem() < proc[0].getOrdem();
            }else{
                return false;
            }
        }
        return false;
    }     
     
    
    public static void main(String[] args) {
        
        while(true){
            Processo[]  proc = new Processo[4];
            
            proc[0] = new Processo(100, 1, 250, 7);
            proc[1] = new Processo(200, 2, 75,  3);
            proc[2] = new Processo(300, 3, 55,  6);
            proc[3] = new Processo(400, 4, 100, 9);
                        
            int inicio, fim, p;
            boolean  conta;
            Scanner entrada = new Scanner(System.in);  
            menu();
            try{
                int entra = entrada.nextInt();  
                switch(entra){
                    case 0:
                       System.out.println("Encerrando Programa");
                       System.exit(0);
                    case 1:
                       inicio = 0;
                       fim  = 0; 
                       System.out.println("\nFIRST IN, FIRST OUT (FIFO)\n");
                       for(p = 0; p < proc.length; p++){
                           fim += proc[p].getTempoExe();
                           proc[p].jsFifo(inicio,fim);
                           inicio = fim;
                       }
                       break;
                    case 2:   
                       inicio = 0;
                       fim  = 0; 
                       proc = ordenaTempo(proc);
                       System.out.println("\nSHORTEST JOB FIRST (JSF)\n");
                       for(p = 0; p < proc.length; p++){
                           fim += proc[p].getTempoExe();
                           proc[p].jsFifo(inicio,fim);
                           inicio = fim;
                       }
                       break;
                    case 3:
                        inicio = 0;
                        fim  = 0;  
                        conta = true;
                        System.out.println("\nROUND ROBIN\n");
                        while(verifica1(proc)){
                            for(p = 0; p < proc.length; p++){
                                if(proc[p].getTempoExe() > 0){
                                   fim += 5;                                  
                                }                                 
                                proc[p].roundRobin(inicio,fim,proc,p, conta);
                                inicio = fim;
                                conta = contaAtivos(proc);
                                if(salvaContexto1(proc, p, conta)){
                                    System.out.println("Salvando"); 
                                    fim+=1;
                                }
                            }                            
                        }
                        break;
                    case 4: 
                        inicio = 0;
                        fim  = 0;                                            
                        proc = ordenaPrioridades(proc);   
                        conta = true;
                        System.out.println("\nPRIORIDADES\n");
                        while(verifica2(proc)){   
                            fim+=0;
                            proc = ordenaPrioridades(proc);               
                            proc[0].prioridades(inicio,fim, proc);                         
                            inicio = fim;
                            conta = contaAtivos2(proc);
                            if(salvaContexto2(proc, conta)){
                               System.out.println("Salvando"); 
                               fim+=0;
                            }                                                                 
                        }  
                        break;
                    default:
                        System.err.println("\nOpção Invalidade!!! Tente de novo\n");
              }
            }catch(Exception ex){
                System.err.println("Digite apenas caracteres numéricos");
            }
        }
    }
    
    public static void menu(){
        System.out.println();
        System.out.println("ESCALONADORES\n");
        System.out.println("OPÇÕES");
        System.out.println("0: SAIR - 1: FIFO - 2: SJF - 3: ROUND ROBIN - 4: PRIORIDADE ");
        System.out.print("OPÇÂO: ");           
   }    
}
