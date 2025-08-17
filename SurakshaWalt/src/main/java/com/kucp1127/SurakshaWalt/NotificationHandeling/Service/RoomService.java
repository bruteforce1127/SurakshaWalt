package com.kucp1127.SurakshaWalt.NotificationHandeling.Service;


import com.kucp1127.SurakshaWalt.NotificationHandeling.DataTransferObject.MessageDTO;
import com.kucp1127.SurakshaWalt.NotificationHandeling.Entities.Message;
import com.kucp1127.SurakshaWalt.NotificationHandeling.Entities.Room;
import com.kucp1127.SurakshaWalt.NotificationHandeling.Repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public Optional<Room> findById(String username){
        return roomRepository.findById(username);
    }

    public void save(Room room) {
        roomRepository.save(room);
    }

    public Object sendNotification(MessageDTO messageDTO) {
        Optional<Room> room = roomRepository.findById(messageDTO.getUsername());
        if(room.isPresent()){
            Message message = new Message();
            message.setContent(messageDTO.getContent());
            message.setTimeStamp(LocalDateTime.now());
            room.get().getMessages().add(message);
            return roomRepository.save(room.get());
        }
        Room room1 = new Room();
        room1.setUser1(messageDTO.getUsername());
        List<Message> messages = new ArrayList<>();
        Message message = new Message();
        message.setTimeStamp(LocalDateTime.now());
        message.setContent(messageDTO.getContent());
        messages.add(message);
        room1.setMessages(messages);
        return roomRepository.save(room1);
    }
}
