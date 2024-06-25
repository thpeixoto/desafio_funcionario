package br.com.first.funcionario.repository;

import br.com.first.funcionario.models.BasicModel;

import java.util.Optional;


/**
 * <b>BasicRepository</b>
 * </p>
 * Interface padrão das classes de repository, com o objetivo de padronizar os métodos de manipulação do banco de dadso
 * @author Thiago Peixoto
 */
public interface BasicRepository<C  extends BasicModel>  {

    /**
     * <b>insert</b>
     * </p>
     * Inclusão do registro no banco de dados.
     * @param objectInsert Recebe um objeto que extende de BasicModel, podendo ter o ID preenchido ou não.
     * @return Optional<C> com os dados do parametro, com o novo ID, caso  o ID do parametro for null;
     */
    public Optional<C> insert( C objectInsert );

    /**
     * <b>findById</b>
     * </p>
     * Busca do registro no banco de dados.
     * @param  id Busca do registro pela chave primária
     * @return Optional<C> os dados encontrados no banco sem manipulação
     */
    public Optional<C> findById (Long id);

    /**
     * <b>update</b>
     * </p>
     * Alteração do registro no banco de dados.
     * @param  objectUpdate Registro a ser alterado sendo obrigatório ter a chave primária preenchida
     * @return True ou False caso tenha ocorido algum erro no processo de alteração. Caso o registro não exista no banco de dados, irá retornar False.
     */
    public boolean update ( C objectUpdate  );

    /**
     * <b>delete</b>
     * </p>
     * Exclusão do registro no banco de dados.
     * @param  id Chave Primária do Registro a ser excluído.
     * @return True ou False caso tenha ocorido algum erro no processo de exclusão. Caso o registro não exista no banco de dados, irá retornar False.
     */
    public boolean delete ( Long id );


}
