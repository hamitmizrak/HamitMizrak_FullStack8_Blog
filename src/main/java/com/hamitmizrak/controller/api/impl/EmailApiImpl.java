package com.hamitmizrak.controller.api.impl;

import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.services.IEmailServices;
import com.hamitmizrak.controller.api.IEmailApi;
import com.hamitmizrak.util.FrontendURL;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// LOMBOK
@RequiredArgsConstructor

// REST
@RestController
@CrossOrigin(origins = FrontendURL.FRONTEND_URL)
@RequestMapping("/email/api/v1")
public class EmailApiImpl implements IEmailApi<EmailDto> {

    // INJECTION
    private final IEmailServices iEmailServices;

    // http://localhost:2222/email/api/v1/basic/email
    @Override
    @PostMapping("/basic/email")
    public EmailDto blogSendEmail(@RequestBody EmailDto emailDto) {
        return (EmailDto) iEmailServices.blogSendEmail(emailDto);
    }
}
