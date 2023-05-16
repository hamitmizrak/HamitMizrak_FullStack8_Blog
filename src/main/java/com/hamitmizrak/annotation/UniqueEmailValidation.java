package com.hamitmizrak.annotation;

import com.hamitmizrak.data.entity.CustomerEntity;
import com.hamitmizrak.data.repository.ICustomerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// <A,T> A=Annotation T=Type
public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail,String> {

    //Injection
    private final ICustomerRepository iCustomerRepository;

    // Database email bulunan  kullanıcıyı var mı yok mu ?
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //CustomerEntity customerEntity= iCustomerRepository.findByEmail(email).orElseThrow( ()->new NotFoundException(email+" bulunmadı"));
        CustomerEntity customerEntity= iCustomerRepository.findByEmail(email);
        // Eğer böyle bir email varsa return false döndürsün
        if(customerEntity!=null){
            return false;
        }
        return true;
    } // end  isValid
} //end class
