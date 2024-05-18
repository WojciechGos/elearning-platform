package project.backend.carts.packet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.exception.types.ResourceNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PacketService {
    private final PacketRepository packetRepository;

    public List<Packet> getAllPackets() {
        return packetRepository.findAll();
    }

    public Packet getPacketById(Long packetId) {
        return packetRepository.findById(packetId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Packet with id [%s] not found.", packetId)
                ));
    }

    public Packet createPacket(Packet packet) {
        return packetRepository.save(packet);
    }

    public Packet updatePacket(Long packetId, Packet packetDetails) {
        Packet packet = packetRepository.findById(packetId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Packet with id [%s] not found.", packetId)
                ));

        packet.setName(packetDetails.getName());
        packet.setPrice(packetDetails.getPrice());
        packet.setIsActive(packetDetails.getIsActive());

        return packetRepository.save(packet);
    }

    public void deletePacket(Long packetId) {
        if (!packetRepository.existsById(packetId)) {
            throw new ResourceNotFoundException(
                    String.format("Packet with id [%s] not found.", packetId)
            );
        }
        packetRepository.deleteById(packetId);
    }
}

