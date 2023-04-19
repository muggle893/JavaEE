package udp;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author:txf
 * @description:客户端
 * @date: 2023/4/18.
 */
public class UdpEchoClient {
    public DatagramSocket socket;
    public String serverIp;
    public int serverPort;

    //根据ip和端口构造客户端
    public UdpEchoClient(String ip, int port) throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        this.serverIp = ip;
        this.serverPort = port;
    }

    public void start() throws IOException {
        while (true) {
            //1.从控制台获取用户输入的数据
            Scanner scan = new Scanner(System.in);
            System.out.print("请输入->:");
            String requestData = scan.nextLine();

            //2.发送请求
            byte[] buffer = requestData.getBytes();
            DatagramPacket requestPacket = new DatagramPacket(buffer, 0,
                                                                buffer.length,
                                                InetAddress.getByName(serverIp),
                                                                serverPort);
            socket.send(requestPacket);
            //3.接受服务器的回响
            byte[] b = new byte[2048];
            DatagramPacket responsePacket = new DatagramPacket(b, 0, b.length);
            socket.receive(responsePacket);

            //4.输出
            System.out.println(new String(responsePacket.getData(), 0, responsePacket.getLength()));
        }
    }
}
