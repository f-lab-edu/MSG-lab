package com.example.rabbitmqworker.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rabbitmqworker.domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
}
