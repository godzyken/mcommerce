package com.clientui.exceptions;

import feign.Feign;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

   private final ErrorDecoder defaultErrorDecoder = new Default();


    @Override
    public Exception decode(String methodKey, Response response){

        if (response.status() == 400){

            return new ProductBadRequestException("Requête incorrecte");
        }
        else if (response.status() == 404){

            return new ProductNotFoundException("Prouduit non trouvé");
        }
        return response.status() > 400 && response.status() <= 499 ? new Product4XXException("Erreur de au format 4XX ") : defaultErrorDecoder.decode(methodKey, response);
    }
}
