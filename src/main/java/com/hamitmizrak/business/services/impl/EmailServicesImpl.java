package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.services.IEmailServices;
import com.hamitmizrak.data.entity.EmailSendEntity;
import com.hamitmizrak.data.repository.IEmailRepository;
import com.hamitmizrak.exception.HamitMizrakException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.CharEncoding;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.File;

// LOMBOK
@RequiredArgsConstructor // injection

// SERVICE
@Service
public class EmailServicesImpl implements IEmailServices<EmailDto, EmailSendEntity> {

    private final IEmailRepository iEmailRepository;
    private final JavaMailSender mailSender; // Mail oluşturma
    private final ModelMapperBean modelMapperBean; // DTO => ENTITY

    // MODEL MAPPER
    @Override
    public EmailSendEntity DtoToEntity(EmailDto emailDto) {
        return modelMapperBean.modelMapperMethod().map(emailDto, EmailSendEntity.class);
    }

    @Override
    public EmailDto EntityToDto(EmailSendEntity emailEntity) {
        return modelMapperBean.modelMapperMethod().map(emailEntity, EmailDto.class);
    }

    // MAIL SEND BASIC
    @Override
    public EmailDto blogSendMail(EmailDto emailDto) {
        // Database Kaydet
        EmailSendEntity emailEntity = DtoToEntity(emailDto);
        iEmailRepository.save(emailEntity);

        //Mail Gönder
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailDto.getEmailFrom());
        message.setTo(emailDto.getEmailTo());
        message.setSentDate(emailDto.getSentDate());
        message.setSubject(emailDto.getEmailSubject());
        message.setBcc(emailDto.getEmailTo());
        message.setBcc(emailDto.getEmailTo());
        String mailContent = "Üyeliğinizi aktifleşmesine son bir adım lütfen linke tıklayınız. ";
        message.setText(mailContent);
        mailSender.send(message);
        return emailDto;
    }

    // MAIL SEND IMMEDIAD Attachment
    @SneakyThrows
    @Override
    public EmailDto blogSendAttachmentMail(EmailDto emailDto) { //throws MessagingException
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, CharEncoding.UTF_8);
        String content = "<b>Merhabalar</b>,<br><i>Please look at this nice picture:.</i>" + "<br><img src='cid:image001'/><br><b>Best Regards</b>";
        try {
            messageHelper.setFrom(emailDto.getEmailFrom());
            messageHelper.setTo(emailDto.getEmailTo());
            messageHelper.setSentDate(emailDto.getSentDate());
            messageHelper.setSubject(emailDto.getEmailSubject());
            messageHelper.setBcc(emailDto.getEmailTo());
            messageHelper.setBcc(emailDto.getEmailTo());
            // burdaki true eğer Html formatında yazmak istersen kullanabilirsin
            messageHelper.setText(content, true);

            //pdf txt resim göndermek
            FileSystemResource pdf = new FileSystemResource(new File("C:\\fileio\\document.pdf"));
            messageHelper.addAttachment("document.pdf", pdf);
            messageHelper.addAttachment("deneme.txt", new FileSystemResource(new File("C:\\fileio\\deneme.txt")));
            FileSystemResource resim = new FileSystemResource(new File("C:\\fileio\\document.pdf"));
            messageHelper.addAttachment("picture.png", resim);
            messageHelper.addInline("image001", resim);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new HamitMizrakException("Gönderim Sırasında hata meydana geldi.");
        }
        mailSender.send(mimeMessage);
        return emailDto;
    }
}// end class
