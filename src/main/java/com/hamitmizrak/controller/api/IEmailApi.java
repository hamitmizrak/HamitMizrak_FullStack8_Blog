package com.hamitmizrak.controller.api;

public interface IEmailApi <D>{

    // SEND EMAIL
    // Basic
    public D blogSendMail(D d);

    // Intermedia
    public D blogSendAttachmentMail(D d);
}
