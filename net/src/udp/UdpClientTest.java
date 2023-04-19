package udp;

/**
 * @author:txf
 * @description:
 * @date: 2023/4/18.
 */
public class UdpClientTest {
    public static void main(String[] args) throws Exception{
        UdpEchoClient client = new UdpEchoClient("127.0.0.1", 9090);
        client.start();
    }
}
