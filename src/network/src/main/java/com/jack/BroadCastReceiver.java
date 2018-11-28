package com.jack;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadCastReceiver {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(8888);
            byte[] buf = new byte[5];
            DatagramPacket dp = new DatagramPacket(buf, buf.length);
            ds.receive(dp);
            System.out.println(new String(buf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
