package com.hamitmizrak.controller.api;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp
public interface IEmailApi <D>{

    // BLOG SEND
    public D blogSendEmail(D d);

    // Intermedia
    public D blogSendAttachmentMail(D d);

} //end class
