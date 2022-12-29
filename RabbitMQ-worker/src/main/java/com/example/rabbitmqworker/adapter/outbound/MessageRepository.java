package com.example.rabbitmqworker.adapter.outbound;

import com.example.rabbitmqworker.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
