package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.RegisterEntity;
import com.hamitmizrak.data.repository.IRegisterRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// <A,T> A=Annotation T=Type
public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail,String> {

    //Injection
    private final IRegisterRepository iAdminRepository;

    // Database email bulunan  kullanıcıyı var mı yok mu ?
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //AdminEntity customerEntity= iAdminRepository.findByEmail(email).orElseThrow( ()->new NotFoundException(email+" bulunmadı"));
        RegisterEntity customerEntity= iAdminRepository.findByEmail(email);
        // Eğer böyle bir email varsa return false döndürsün
        if(customerEntity!=null){
            return false;
        }
        return true;
    } // end  isValid
} //end class
