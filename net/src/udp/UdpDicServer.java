package udp;

import java.net.SocketException;
import java.util.HashMap;

/**
 * @author:txf
 * @description:
 * @date: 2023/4/19.
 */
public class UdpDicServer extends UdpEchoServer {
    private HashMap<String, String> hashMap;

    public UdpDicServer(int port) throws SocketException {
        super(port);
        hashMap = new HashMap<>();
        hashMap.put("dog", "狗");
        hashMap.put("cat", "猫");
        hashMap.put("fuck", "cao");
    }

    @Override
    public String processRequestData(String data) {
        return hashMap.get(data);
    }
}
