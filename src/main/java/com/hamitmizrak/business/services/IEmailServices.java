package com.hamitmizrak.business.services;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IBlogGenericsServices extends IProfileHeaderApp

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IBlogGenericsServices

// Generics
// D => Dto
// E => Entity
public interface IEmailServices <D,E>{

    // MODEL MAPPER
    D EntityToDto(E e);
    E DtoToEntity(D d);

    // BLOG SEND BASIC
    public D blogSendBasicEmail(D d);

    // BLOG SEND INTERMEDIA
    public D blogSendAttachmentMail(D d);
}
