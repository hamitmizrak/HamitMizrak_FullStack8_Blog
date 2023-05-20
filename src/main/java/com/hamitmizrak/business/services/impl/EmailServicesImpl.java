package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.services.IEmailServices;
import com.hamitmizrak.data.entity.EmailEntity;
import com.hamitmizrak.data.repository.IEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// LOMBOK
@RequiredArgsConstructor //INJECTION

@Service
public class EmailServicesImpl implements IEmailServices<EmailDto, EmailEntity> {

    //INJECTION
    private final IEmailRepository iEmailRepository;
    private final ModelMapperBean modelMapperBean;
    private final JavaMailSender javaMailSender;

    // MODEL MAPPER
    @Override
    public EmailDto EntityToDto(EmailEntity emailEntity) {
        return modelMapperBean.modelMapperMethod().map(emailEntity, EmailDto.class);
    }

    @Override
    public EmailEntity DtoToEntity(EmailDto emailDto) {
        return modelMapperBean.modelMapperMethod().map(emailDto, EmailEntity.class);
    }

    // Email Send
    @Override
    public EmailDto blogSendEmail(EmailDto emailDto) {
        // MODEL MAPPER, SAVE
        EmailEntity emailEntity = DtoToEntity(emailDto);
        emailEntity = iEmailRepository.save(emailEntity);
        emailDto.setId(emailEntity.getId());

        // Mail Send
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(emailDto.getEmailFrom()); //KİMDEN
        mailMessage.setTo(emailDto.getEmailTo()); //KİME
        mailMessage.setSentDate(emailDto.getSentDate()); //TARIH
        mailMessage.setSubject(emailDto.getEmailSubject()); //Subject
        /*mailMessage.setCc(emailDto.getEmailCc()); //CC
        mailMessage.setBcc(emailDto.getEmailBcc()); //BCC*/
        mailMessage.setText(emailDto.getEmailText()); //Text

        //Mail Gönder
        javaMailSender.send(mailMessage);
        return emailDto;
    }
}
