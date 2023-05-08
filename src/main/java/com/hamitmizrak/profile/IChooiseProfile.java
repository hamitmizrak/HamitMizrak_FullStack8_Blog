package com.hamitmizrak.profile;

import org.springframework.stereotype.Component;
//@Component ==> Spring nesnesi olamaıs için
@Component
public interface IChooiseProfile {
    public String message(String name);
}