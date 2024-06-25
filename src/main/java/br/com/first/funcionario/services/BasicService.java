package br.com.first.funcionario.services;

import java.util.Optional;

public interface   BasicService<C> {

    public Optional<C> insert(C objectInsert);

    public Optional<C> findById(Long id);

    public boolean update(C objectUpdate);

    public boolean delete(Long id);


}