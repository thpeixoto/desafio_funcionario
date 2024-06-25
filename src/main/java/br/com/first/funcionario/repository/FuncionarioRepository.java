package br.com.first.funcionario.repository;

import br.com.first.funcionario.models.Funcionario;
import br.com.first.funcionario.repository.banco.BD;


/**
 * <b>FuncionarioRepository</b>
 * </p>
 * Repository para manipulação dos dados de funcionário no banco de dados
 * @author Thiago Peixoto
 */
public class FuncionarioRepository extends BasicRepositoryImpl<Funcionario>  {


    /**
     * <b>Construtor</b>
     * </p>
     * Recebe o Bean de conexão com o banco de dados
     * @param conexao Bean
     */
    public FuncionarioRepository(BD conexao) {
        super(conexao);
    }
}
