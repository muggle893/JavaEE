package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author:txf
 * @description:
 * @date: 2023/4/20.
 */
public class TcpEchoClient {
    public Socket socket;
    public TcpEchoClient(String serverIp, int port) throws IOException {
        socket = new Socket(serverIp, port);
    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        try (InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            while (true) {
                //控制台接受输入
                System.out.print("->:");
                String request = scan.next();

                //发送请求
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.println(request);
                printWriter.flush();

                //接受服务器的响应
                Scanner responseScan = new Scanner(inputStream);
                String response = responseScan.next();

                //打印
                System.out.printf("req:%s res:%s\n", request, response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
       TcpEchoClient client =  new TcpEchoClient("127.0.0.1", 9090);
       client.start();
    }
}
