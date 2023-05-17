package com.hamitmizrak.business.services;

public interface IEmailServices<D,E> {

    // MODEL MAPPER
    public E DtoToEntity(D d);
    public D EntityToDto (E e);

    // SEND EMAIL
    // Basic
    public D blogSendMail(D d);

    // Intermedia
    public D blogSendAttachmentMail(D d);
}
