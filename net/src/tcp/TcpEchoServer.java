package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author:txf
 * @description:
 * @date: 2023/4/20.
 */
public class TcpEchoServer {
    public ServerSocket serverSocket;
    public TcpEchoServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException{
        System.out.println("服务器启动~");
        while (true) {
            //接受用户的请求
            Socket socket = serverSocket.accept();
            //处理用户的请求
            processConnection(socket);
        }
    }

    public void processConnection(Socket socket) {
        System.out.printf("客户端[%s %d]上线\n", socket.getInetAddress().toString(), socket.getPort());
        //得到字节输入流和字节输出流，服务器做出回响
        try (InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            Scanner scanner = new Scanner(inputStream);
            PrintWriter printWriter = new PrintWriter(outputStream);

            while (true) {
                if (!scanner.hasNext()) {
                    System.out.printf("客户端[%s %d]下线\n", socket.getInetAddress().toString(), socket.getPort());
                    break;
                }
                //读取请求
                String request = scanner.next();

                //回响
                String response = processRequest(request);
                printWriter.println(request);
                printWriter.flush();

                //服务器打印日志
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                System.out.printf("%s [%s %d] req:%s res:%s\n",
                        dateFormat.format(new Date()),
                        socket.getInetAddress().getHostAddress(),
                        socket.getPort(),
                        request, response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String processRequest(String request) {
        return request;
    }

    public static void main(String[] args) throws IOException{
        TcpEchoServer tcpEchoServer = new TcpEchoServer(9090);
        tcpEchoServer.start();
    }
}
