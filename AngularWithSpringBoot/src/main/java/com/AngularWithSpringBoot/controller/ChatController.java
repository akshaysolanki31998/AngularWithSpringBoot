
package com.AngularWithSpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.AngularWithSpringBoot.Entity.ChatMessage;
import com.AngularWithSpringBoot.chatboatDto.ChatRequest;
import com.AngularWithSpringBoot.chatboatDto.ChatResponse;
import com.AngularWithSpringBoot.repository.ChatMessageRepository;
import com.AngularWithSpringBoot.services.AiChatService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private AiChatService aiChatService;

    @Autowired
    private ChatMessageRepository repository;

//    @PostMapping("/ask")
//    public ResponseEntity<?> askQuestion(@RequestBody ChatRequest request) {
//
//        if (request.getQuestion() == null || request.getQuestion().trim().isEmpty()) {
//            return ResponseEntity.badRequest().body("Question is required");
//        }
//
//        // 1️⃣ AI Answer
//        String aiAnswer = aiChatService.getAiAnswer(request.getQuestion());
//
//        // 2️⃣ Save to DB
//        ChatMessage chat = new ChatMessage();
//        chat.setSessionId(
//                request.getSessionId() != null ? request.getSessionId() : "default"
//        );
//        chat.setUserQuestion(request.getQuestion());
//        chat.setAiAnswer(aiAnswer);
//
//        repository.save(chat);
//
//        // 3️⃣ Response
//        return ResponseEntity.ok(aiAnswer);
//        
//    }
    
    @PostMapping("/ask")
    public ResponseEntity<?> askQuestion(
            @org.springframework.web.bind.annotation.RequestBody ChatRequest request) {

        if (request.getQuestion() == null || request.getQuestion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Question cannot be empty");
        }

        String aiAnswer = aiChatService.getAiAnswer(request.getQuestion());

        // DB save
        ChatMessage chat = new ChatMessage();
        chat.setSessionId(request.getSessionId());
        chat.setUserQuestion(request.getQuestion());
        chat.setAiAnswer(aiAnswer);
        repository.save(chat);

        // ✅ ALWAYS JSON
        return ResponseEntity.ok(new ChatResponse(aiAnswer));
    }

    // Chat History
    @GetMapping("/history/{sessionId}")
    public List<ChatMessage> getHistory(@PathVariable String sessionId) {
        return repository.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }
}
