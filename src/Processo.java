/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marcio
 */
public class Processo {
    private final int pid;
    private final  int ordem;
    private  int tempoExe;
    private  int prioridade;   

    public Processo(int pid, int ordem, int tempoExe, int prioridade) {
        this.pid = pid;
        this.ordem = ordem;
        this.tempoExe = tempoExe;
        this.prioridade = prioridade;
    }  

    public int getPid() {
        return pid;
    }

    public int getOrdem() {
        return ordem;
    }

    public int getTempoExe() {
        return tempoExe;
    }

    public int getPrioridade() {
        return prioridade;
    }
    
    public void jsFifo(int inicio, int fim){      
        if(tempoExe > 0){
            System.out.println("PID: "+pid+" "+
                               "\tORDEM DE CHEGADA: "+ordem+" "+
                               "\t\tTEMPO PARA EXECUÇÃO: "+tempoExe+"ms"+" "+
                               "\tINICIOU:"+inicio+"ms"+" "+
                               "\tFINALIZOU: "+fim+"ms");    
            tempoExe -= tempoExe;
        }        
    }     
    
    public void roundRobin(int inicio, int fim, Processo[] proc, int p, boolean conta){      
        if(tempoExe > 0){
            System.out.println("PID: "+pid+" \t "+
                               "ORDEM DE CHEGADA: "+ordem+" \t "+
                               "TEMPO PARA EXECUÇÃO: "+tempoExe+"ms"+" \t "+
                               "INICIOU: "+inicio+"ms"+" \t "+
                               "FINALIZOU: "+fim+"ms");    
            tempoExe -= 5;
            if(tempoExe <= 0){               
               System.out.println("Processo: "+pid+ " Encerrado ");                           
            }             
        }        
    } 
    
    public void prioridades(int inicio, int fim, Processo[] proc){      
        if(prioridade > 0){            
            System.out.println("PID: "+pid+" \t "+
                               "ORDEM DE CHEGADA: "+ordem+" \t "+
                               "PRIORIDADE: "+prioridade+"ms"+" \t "+
                               "INICIOU: "+inicio+"ms"+" \t "+
                               "FINALIZOU: "+fim+"ms");                
           prioridade--;           
           if(prioridade <=0){
              System.out.println("Processo: "+pid+ " Encerrado ");              
           }             
        }        
    }                
}

