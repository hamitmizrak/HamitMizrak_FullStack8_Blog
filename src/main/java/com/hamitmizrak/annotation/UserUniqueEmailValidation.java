package com.hamitmizrak.annotation;

import com.hamitmizrak.data.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
// LOMBOK
@RequiredArgsConstructor

// <A,T> A=Annotation T=Type
public class UserUniqueEmailValidation implements ConstraintValidator<UserUniqueEmail,String> {

    //Injection
    private final IUserRepository iUserRepository;

    // Database email bulunan  kullanıcıyı var mı yok mu ?
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        //UserEntity userEntity= iUserRepository.findByEmail(email).orElseThrow( ()->new NotFoundException(email+" bulunmadı"));
       Boolean  userEntity= iUserRepository.findByEmail(email).isPresent();
        // Eğer böyle bir email varsa return false döndürsün
        if(userEntity){
            return false;
        }
        return true;
    } // end  isValid
} //end class
