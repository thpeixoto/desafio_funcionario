package br.com.first.funcionario.services;

import br.com.first.funcionario.models.Funcionario;
import br.com.first.funcionario.repository.FuncionarioRepository;
import br.com.first.funcionario.repository.banco.BD;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService extends BasicServiceImpl<Funcionario> {

    public FuncionarioService(BD conexao ) {
        super(new FuncionarioRepository( conexao ));
    }

}
