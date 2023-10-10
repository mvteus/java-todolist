package br.com.mvteus.todolist;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id /* Para definir que este este atributo 'id' seja a chave primária da entidade no banco de dados */
    @GeneratedValue(generator = "UUID") /* Para que o JPA gere automaticamente as chaves primárias */
    private UUID id;
    private String name;
    private String username;
    private String password;
    @CreationTimestamp /* Atributo para ter o registro de quando foi gerado o dado no banco de dados */
    private LocalDateTime createdAt;

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
