package rest_api_comandas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest_api_comandas.auth.UtilJwt;
import rest_api_comandas.errors.ValidationError;
import rest_api_comandas.model.UsuarioEntity;
import rest_api_comandas.pojos.AuthPojo;
import rest_api_comandas.pojos.UsuarioPojo;
import rest_api_comandas.pojos.UsuarioTransform;
import rest_api_comandas.repository.UsuarioRepository;

@RestController
@RequestMapping
public class AuthController {

	private final UsuarioRepository usuarioRepository;
    private final UtilJwt utilJwt;

    public AuthController(UsuarioRepository usuarioRepository, UtilJwt utilJwt) {
        this.usuarioRepository = usuarioRepository;
        this.utilJwt = utilJwt;
    }
    
    // POST /usuarios → cadastro
    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioPojo pojo) {
        try { 
        	
        	if (usuarioRepository.findByEmail(pojo.getEmailUsuario()) != null) {
                throw new ValidationError("Email já em utilização");
            }
        	
        	UsuarioEntity entity = UsuarioTransform.toEntity(pojo);
            UsuarioEntity salvo = usuarioRepository.save(entity);
            
            return ResponseEntity.ok(UsuarioTransform.toPojo(salvo));
 
        } catch(ValidationError erro) {
        	return ResponseEntity.badRequest().body(erro.getMessage());
        } catch (Exception e) {
        	return ResponseEntity.internalServerError().body("Ocorreu um erro ao cadastrar usuário");
        }
    }

    // POST /login → login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthPojo credenciais) {
       try {
    	   UsuarioEntity usuario = usuarioRepository.findByEmail(credenciais.getEmail());
           
    	   if (usuario == null || !usuario.getSenha().equals(credenciais.getSenha())) {
    		   throw new ValidationError("Email ou senha inválidos");
    	   }
              
    	   String token = "Bearer " + utilJwt.gerarToken(usuario.getEmail());
    	   return ResponseEntity.ok(token);
    	   
       } catch (ValidationError erro) {
    	   return ResponseEntity.status(401).body(erro.getMessage());
       } catch (Exception e) {
    	   return ResponseEntity.status(401).body("Ocorreu um erro no servidor");
       }
    }
    
}
