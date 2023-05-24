package com.hamitmizrak.business.services;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IModelMapperService extends IProfileHeaderApp

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IModelMapperService

// Generics
// D => Dto
// E => Entity
public interface IModelMapperService<D,E> {

    // MODEL MAPPER
    public D EntityToDto(E e);

    public E DtoToEntity(D d);
}
