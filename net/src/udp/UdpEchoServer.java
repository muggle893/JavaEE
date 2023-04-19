package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;

/**
 * @author:txf
 * @description:udp回响服务器
 * @date: 2023/4/18.
 */
public class UdpEchoServer {
    public DatagramSocket socket;

    //根据端口构造服务器
    public UdpEchoServer(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            //1.接收用户的请求
            byte[] buffer = new byte[1024];
            DatagramPacket requestPacket = new DatagramPacket(buffer, 0, buffer.length);
            socket.receive(requestPacket);

            //2.处理请求
            String requestData = new String(requestPacket.getData(), 0, requestPacket.getLength());
            String responseData = processRequestData(requestData);

            //3.服务器回响，把请求数据返回给客户端
            DatagramPacket responsePacket = new DatagramPacket(responseData.getBytes(),
                                                        0, requestPacket.getLength(),
                                                        requestPacket.getAddress(),
                                                        requestPacket.getPort());

            socket.send(responsePacket);

            //4.显示日志信息
            System.out.printf("%s [%s %d] requestInfo:%s responseInfo:%s\n", new Date().toString(),
                    requestPacket.getAddress().toString(),
                    requestPacket.getPort(),
                    requestData.toString(), responseData.toString());

        }

    }

    public String processRequestData(String data) {
        return data;
    }
}
