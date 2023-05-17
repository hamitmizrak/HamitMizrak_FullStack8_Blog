package com.hamitmizrak.business.services;

public interface IModelMapper<D,E> {

    // MODEL MAPPER
    public D EntityToDto(E e);

    public E DtoToEntity(D d);
}
