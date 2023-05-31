package com.jonas.module.register.handler;

import com.jonas.module.register.packet.Packet;

public interface PacketHandler<T extends Packet> {
    void handle(T packet);

    default int getRegisteredPid() {
        RegisterPacketHandler annotation = this.getClass().getAnnotation(RegisterPacketHandler.class);
        if (annotation == null) {
            throw new RuntimeException("处理器没有使用RegisterPacketHandler注解注册");
        }
        return annotation.value();
    }
}
