package br.com.first.funcionario.services;

import br.com.first.funcionario.models.BasicModel;
import br.com.first.funcionario.repository.BasicRepository;

import java.util.Optional;

public abstract class BasicServiceImpl<C extends BasicModel> implements BasicService<C> {


    private BasicRepository repository;

    public BasicServiceImpl( BasicRepository repository) {
       this.repository = repository;
    }

    public Optional<C> insert(C objectInsert )  {
        return  repository.insert(objectInsert );
    }

    public Optional<C> findById(Long  id) {
        return    repository.findById(  id);
    }

    public boolean update(C objectUpdate   ) {
        return repository.update( objectUpdate ) ;
     }

    public boolean delete(Long  id) {
        return repository.delete(   id);
    }



}
