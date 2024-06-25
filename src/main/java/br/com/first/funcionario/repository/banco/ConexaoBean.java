package br.com.first.funcionario.repository.banco;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConexaoBean {

    @Bean
    public BD conexao() {
        return new BD();
    }



}
