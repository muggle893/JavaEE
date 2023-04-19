package udp;

import java.net.SocketException;
import java.util.Scanner;

/**
 * @author:txf
 * @description:
 * @date: 2023/4/18.
 */
public class UdpServerTest {
    public static void main(String[] args) throws Exception{
        UdpDicServer server = new UdpDicServer(9090);
        server.start();
    }
}
