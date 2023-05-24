package com.hamitmizrak.business.services.impl;

import com.hamitmizrak.bean.ModelMapperBean;
import com.hamitmizrak.business.dto.EmailDto;
import com.hamitmizrak.business.services.IEmailServices;
import com.hamitmizrak.data.entity.EmailEntity;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

// LOMBOK
@RequiredArgsConstructor //INJECTION

@Service
//@Transactional // Create,delete,update eklemelisin
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

    // Email Basic Send
    @Override
    @Transactional // Create,delete,update eklemelisin
    public EmailDto blogSendBasicEmail(EmailDto emailDto) {
        // MODEL MAPPER, SAVE
        EmailEntity emailEntity = DtoToEntity(emailDto);
        emailEntity = iEmailRepository.save(emailEntity);
        emailDto.setId(emailEntity.getId());

        // Mail Send
        SimpleMailMessage mailMessage = new SimpleMailMessage();
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
    } // end send Basic

    // MAIL SEND IMMEDIAD Attachment
    @SneakyThrows
    @Override
    @Transactional // Create,delete,update eklemelisin
    public EmailDto blogSendAttachmentMail(EmailDto emailDto) { //throws MessagingException
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
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

        //Mail Gönder
        javaMailSender.send(mimeMessage);
        return emailDto;
    } // end blogSendAttachmentMail

}// end class
