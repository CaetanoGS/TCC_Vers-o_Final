
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws IOException {
        
        System.out.println("Iniciando o Server");
        
        ServerSocket server = new ServerSocket(2525);
        
        System.out.println("Aguardando conexão");
        
        Socket socket = server.accept();
        
        System.out.println("Conexão estabelecida");
        
        InputStream intput = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader (intput) );
        PrintStream out = new PrintStream(output);
        
        while(true){
            
            String msg = in.readLine();
            
            System.out.println(
                    "Mensagem recebida do cliente ["
                    + socket.getInetAddress().getHostName() + 
                    "]: "+
                    msg);
            
            if("FIM".equals(msg)){
                
                break;
            }
            
            if("Oi".equals(msg))
                out.println("Sim");
            
            //out.println(msg);
        }
        
        System.out.println("Conexão encerrada");
        
        in.close();
        out.close();
        socket.close();
        
        System.out.println("Servidor encerrado");
        
        server.close();
    }
    
}
