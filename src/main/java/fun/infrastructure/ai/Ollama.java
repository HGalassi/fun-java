 package fun.infrastructure.ai;

import fun.usecases.user.entity.UserEntity;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Ollama {

    private final ChatModel chatModel;

    public Ollama(@Qualifier("ollamaChatModel") ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String ask(String prompt) {
        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OllamaChatOptions.builder()
                                .model(OllamaModel.LLAMA3_1)
                                .temperature(0.4)
                                .build()
                ));
        return response.getResult().getOutput().getText();
    }

    public UserEntity userStubbedInfo() {
        UserEntity entity = ChatClient.create(chatModel).prompt()
                .user(spec -> spec.text(
                        "Generate a stub data for a user with a random name and a random email address. " +
                                "The email address must have the format of a valid email address and must" +
                                "have the domain 'example.com'. The name must be the same as the user name given." +
                                "The response must be in JSON format and must only contain the fields: name, email, following" +
                                "the rules mentined above."))
                .options(OllamaChatOptions.builder()
                        .model(OllamaModel.LLAMA3_1)
                        .temperature(0.4)
                        .build())
                .call()
                .entity(UserEntity.class);
        return entity;
    }
}

