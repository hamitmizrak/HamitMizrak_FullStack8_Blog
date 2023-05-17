package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.services.IEmailServices;
import com.hamitmizrak.controller.api.IEmailApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

// LOMBOK
@Log4j2
@RequiredArgsConstructor // Injection

// Apı
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/email/api/v1")
public class EmailApiImpl implements IEmailApi<EmailDto> {

    // IUNJECTION
    private final IEmailServices iEmailServices;

    // http://localhost:2222/email/api/v1/basic/email
    @Override
    @PostMapping("basic/email")
    public EmailDto blogSendMail(@RequestBody EmailDto emailDto) {
        return (EmailDto) iEmailServices.blogSendMail(emailDto);
    }

    // http://localhost:2222/email/api/v1/intermedia/email
    @Override
    @PostMapping("intermedia/email")
    public EmailDto blogSendAttachmentMail(@RequestBody EmailDto emailDto) {
        return (EmailDto) iEmailServices.blogSendAttachmentMail(emailDto);
    }

} // end class
