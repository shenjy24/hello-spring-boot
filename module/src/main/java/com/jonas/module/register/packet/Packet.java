package com.jonas.module.register.packet;

import lombok.Data;

@Data
public abstract class Packet {
    /**
     * 消息id，不能冲突
     */
    protected int pid;
    protected String deviceId;

    protected Packet() {
        setPidFromAnnotation();
    }

    protected void setPidFromAnnotation() {
        RegisterPacket annotation = this.getClass().getAnnotation(RegisterPacket.class);
        if (annotation != null) {
            setPid(annotation.value());
        }
    }
}
