package com.jonas.module.register;

import com.jonas.module.register.handler.PacketHandler;
import com.jonas.module.register.handler.RegisterPacketHandler;
import com.jonas.module.register.packet.Packet;
import com.jonas.module.register.packet.RegisterPacket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterService {

    private final ApplicationContext applicationContext;
    private final ScanClassUtils scanClassUtils;

    private final Map<Integer, Class<? extends Packet>> packetClassMap = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Integer, PacketHandler<?>> handlerMap = new ConcurrentHashMap<>();

    private void registerPackets() {
        List<Class<?>> classes = scanClassUtils.getClasses(Packet.class.getPackage().getName());
        for (Class<?> clazz : classes) {
            if (Packet.class.isAssignableFrom(clazz)) {
                RegisterPacket annotation = clazz.getAnnotation(RegisterPacket.class);
                if (annotation != null) {
                    int pid = annotation.value();
                    if (packetClassMap.putIfAbsent(pid, (Class<? extends Packet>) clazz) != null) {
                        log.warn("数据包pid重复注册 {} {}", pid, clazz);
                    }
                }
            }
        }
    }

    private void registerHandlers() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RegisterPacketHandler.class);
        for (Object value : beans.values()) {
            if (!(value instanceof PacketHandler<?> packetHandler)) {
                continue;
            }

            int pid = packetHandler.getRegisteredPid();
            if (handlerMap.putIfAbsent(pid, packetHandler) != null) {
                log.warn("包处理器重复注册 {} {}", pid, packetHandler);
            }
        }
    }
}
