package com.jonas.module.register.packet;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@RegisterPacket(PacketPid.PING_ID)
public class PingPacket extends Packet {

    private long pingId;

    private long timestamp;

    private String publicIp;
}
