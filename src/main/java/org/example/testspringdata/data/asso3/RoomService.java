package org.example.testspringdata.data.asso3;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public class RoomService {

  private final RoomRepository roomRepository;

  public RoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @Transactional
  public List<Room> getAllAnotherOneRequest() {
    return roomRepository.getAllAnotherOneRequest();
  }
}
