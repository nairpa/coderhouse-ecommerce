package edu.coderhouse.ecommerce.services.interfaces;

import edu.coderhouse.ecommerce.models.User;
import edu.coderhouse.ecommerce.models.request.LoginRequest;
import edu.coderhouse.ecommerce.models.request.RegisterRequest;
import edu.coderhouse.ecommerce.models.response.TokenResponse;

import java.util.Optional;

public interface AuthService {
    /***
     * Registra un nuevo usuario en la base de datos.
     * @param register
     * @return
     */
    Optional<User> signupUser(RegisterRequest register);

    /**
     * Autentica el usuario loggeado.
     * @param login
     * @return devuelve el token de autenticación y los datos del usuario registrado
     */
    Optional<TokenResponse> signinUser(LoginRequest login);

}
