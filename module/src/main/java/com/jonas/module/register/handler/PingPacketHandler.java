package com.jonas.module.register.handler;

import com.jonas.module.register.packet.PacketPid;
import com.jonas.module.register.packet.PingPacket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * ping包处理器
 */
@Service
@RequiredArgsConstructor
@RegisterPacketHandler(PacketPid.PING_ID)
public class PingPacketHandler implements PacketHandler<PingPacket> {

    @Override
    public void handle(PingPacket packet) {

    }
}
