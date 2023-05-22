package com.hamitmizrak.business.services;

public interface IEmailServices <D,E>{


    // MODEL MAPPER
    D EntityToDto(E e);
    E DtoToEntity(D d);

    // BLOG SEND
    public D blogSendEmail(D d);
}
