package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.AdminEntity;
import com.hamitmizrak.data.repository.IAdminRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.webjars.NotFoundException;

// LOMBOK
@RequiredArgsConstructor

// <A,T> A=Annotation T=Type
public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail,String> {

    //Injection
    private final IAdminRepository iAdminRepository;

    // Database email bulunan  kullanıcıyı var mı yok mu ?
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //AdminEntity customerEntity= iAdminRepository.findByEmail(email).orElseThrow( ()->new NotFoundException(email+" bulunmadı"));
        AdminEntity customerEntity= iAdminRepository.findByEmail(email);
        // Eğer böyle bir email varsa return false döndürsün
        if(customerEntity!=null){
            return false;
        }
        return true;
    } // end  isValid
} //end class
