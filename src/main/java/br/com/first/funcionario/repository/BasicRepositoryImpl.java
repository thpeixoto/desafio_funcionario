package br.com.first.funcionario.repository;

import br.com.first.funcionario.models.BasicModel;
import br.com.first.funcionario.repository.banco.BD;

import java.util.Comparator;
import java.util.Optional;


/**
 * <b>BasicRepositoryImpl</b>
 * </p>
 * Classe abstrata que implementa os métodos comuns dos repositorys
 * @author Thiago Peixoto
 */
public abstract class BasicRepositoryImpl<C extends BasicModel> implements BasicRepository<C> {


    private BD bd;


    public BasicRepositoryImpl(BD bd) {
        this.bd = bd;
    }

    /**
     * <b>insert</b>
     * </p>
     * Inclusão do registro no banco de dados.
     * @param objectInsert Recebe um objeto que extende de BasicModel, podendo ter o ID preenchido ou não.
     * @return Optional<C> com os dados do parametro, com o novo ID, caso  o ID do parametro for null;
     */
    public Optional<C> insert(C objectInsert) {
        try {

            return Optional.ofNullable(objectInsert)
                    .map(obj -> {
                        if (obj.isIdEmpty()) {
                            obj.setId(getNextId().orElse(0L) + 1);
                        } else if (bd.existObj(obj.getId())) {
                            return null;
                        }
                        return obj;
                    })
                    .flatMap(obj -> obj != null ? persistMap(obj.getId(), obj) : Optional.empty());
        } catch (Exception ex) {
            throw ex;
        }
    }


    private Optional<Long> getNextId() {
        try {
            return bd.getBanco().keySet().stream().max(Comparator.naturalOrder());
        } catch (NullPointerException ex) {
            throw new RuntimeException("Não foi possivel encontrar o próximo ID.");
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <b>findById</b>
     * </p>
     * Busca do registro no banco de dados.
     * @param  id Busca do registro pela chave primária
     * @return Optional<C> os dados encontrados no banco sem manipulação
     */
    public Optional<C> findById(Long id) {
        try {
            return Optional.ofNullable((C) bd.getBanco().get(id));
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <b>update</b>
     * </p>
     * Alteração do registro no banco de dados.
     * @param  objectUpdate Registro a ser alterado sendo obrigatório ter a chave primária preenchida
     * @return True ou False caso tenha ocorido algum erro no processo de alteração. Caso o registro não exista no banco de dados, irá retornar False.
     */
    public boolean update(C objectUpdate) {
        try {
            return bd.existObj(objectUpdate.getId()) && bd.merge(objectUpdate.getId(), objectUpdate);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <b>delete</b>
     * </p>
     * Exclusão do registro no banco de dados.
     * @param  id Chave Primária do Registro a ser excluído.
     * @return True ou False caso tenha ocorido algum erro no processo de exclusão. Caso o registro não exista no banco de dados, irá retornar False.
     */
    public boolean delete(Long id) {
        try {
            return bd.existObj(id) && bd.delete(id);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * <b>persistMap</b>
     * </p>
     * Executa a inclusão, recebendo o ID correto
     * @param id Chave primária do registro
     * @param object Recebe um objeto que extende de BasicModel, podendo ter o ID preenchido ou não.
     * @return Optional<C> com os dados do parametro, o ID não é preenchido nesta etapa
     */

    private Optional<C> persistMap(Long id, C object) {
        try {
            bd.persist(id, object);
            return Optional.ofNullable(object);
        } catch (Exception ex) {
            throw ex;
        }
    }


}
