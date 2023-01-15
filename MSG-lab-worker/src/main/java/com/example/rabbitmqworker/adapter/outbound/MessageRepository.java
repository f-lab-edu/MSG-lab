package com.example.rabbitmqworker.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rabbitmqworker.domain.Message;

public interface MessageRepository extends JpaRepository<Message, String> {
}
