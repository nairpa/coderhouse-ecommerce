package edu.coderhouse.ecommerce.services.interfaces;

import edu.coderhouse.ecommerce.models.documents.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /***
     * Elimina un usuario según su Id
     * @param userId
     * */
    void deleteUser(String userId);
    /***
     * Actualiza un usuario según su Id
     * @param user
     * @param userId
     * @return Devuelve el usuario actualizado.
     * */
    User updateUser(User user, String userId);

    /***
     * Registra un nuevo usuario en la base de datos.
     * @param register
     * @return
     */
    //Optional<User> signupUser(RegisterRequest register);

    /**
     * Autentica el usuario loggeado.
     * @param login
     * @return devuelve el token de autenticación y los datos del usuario registrado
     */
    //Optional<TokenResponse> signinUser(LoginRequest login);

    /***
     * Busca un usuario según su Id
     * @param userId
     * @return devuelve el usuario buscado si este existe.
     */
    Optional<User> getUserById(String userId);

    /***
     * Devuelve todos los usuarios registrados
     * @return
     */
    List<User> getUsers();
}
