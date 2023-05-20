package com.hamitmizrak.business.services;


import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.data.entity.EmailEntity;

public interface IEmailServices <D,E>{


    // MODEL MAPPER
    D EntityToDto(E e);

    E DtoToEntity(D d);

    // BLOG SEND
    public D blogSendEmail(D d);
}
