package project.backend.carts.packet;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/packets")
public class PacketController {

    private final PacketService packetService;

    @GetMapping
    public ResponseEntity<List<Packet>> getAllPackets() {
        List<Packet> packets = packetService.getAllPackets();
        return ResponseEntity.ok(packets);
    }

    @GetMapping("{id}")
    public ResponseEntity<Packet> getPacketById(@PathVariable("id") Long packetId) {
        Packet packet = packetService.getPacketById(packetId);
        return ResponseEntity.ok(packet);
    }

    @PostMapping
    public ResponseEntity<Packet> createPacket(@RequestBody Packet packet) {
        Packet createdPacket = packetService.createPacket(packet);
        return new ResponseEntity<>(createdPacket, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Packet> updatePacket(
            @PathVariable("id") Long packetId,
            @RequestBody Packet packetDetails
    ) {
        Packet updatedPacket = packetService.updatePacket(packetId, packetDetails);
        return ResponseEntity.ok(updatedPacket);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePacket(@PathVariable("id") Long packetId) {
        packetService.deletePacket(packetId);
        return ResponseEntity.noContent().build();
    }
}

