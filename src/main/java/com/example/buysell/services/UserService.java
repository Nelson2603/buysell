package com.example.buysell.services;

import com.example.buysell.enums.Role;
import com.example.buysell.models.User;
import com.example.buysell.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service // Эта аннотация говорит Spring, что этот класс является сервисом,
@Slf4j //Эта аннотация от библиотеки Lombok добавляет логгер
@RequiredArgsConstructor
//Также от Lombok, эта аннотация автоматически генерирует конструктор, который принимает все final поля
public class  UserService {
    private final UserRepository userRepository;//финал значит иницилизируется в конструкторе и не меняется после
    private final PasswordEncoder passwordEncoder;//для шифровки пароллей

    public boolean createUser(User user) {
        String email = user.getEmail();
        // Проверяем, существует ли пользователь с таким email
        if (userRepository.findByEmail(email).isPresent()) {
            return false;
        }

        // Настраиваем параметры пользователя
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);

        // Логируем создание пользователя
        log.info("Создали Нового User c email: {}", email);

        // Сохраняем пользователя
        userRepository.save(user);
        return true;
    }
}
