package com.jack;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class BroadCastSender {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("192.168.0.255");
            DatagramSocket ds = new DatagramSocket();
            String str = "hello";
            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, ip, 8888);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
