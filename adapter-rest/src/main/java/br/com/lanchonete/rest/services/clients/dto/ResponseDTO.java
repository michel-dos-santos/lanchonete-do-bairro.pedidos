package br.com.lanchonete.rest.services.clients.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private String id;
    private String name;
    private String cpf;
    private String email;

}
