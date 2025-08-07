package rest_api_comandas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rest_api_comandas.errors.ValidationError;
import rest_api_comandas.model.ComandaEntity;
import rest_api_comandas.model.ComandaProdutoEntity;
import rest_api_comandas.model.UsuarioEntity;
import rest_api_comandas.pojos.ComandaPojo;
import rest_api_comandas.pojos.ComandaTransform;
import rest_api_comandas.pojos.ProdutoTransform;
import rest_api_comandas.pojos.UsuarioPojo;
import rest_api_comandas.pojos.UsuarioTransform;
import rest_api_comandas.repository.ComandaProdutoRepository;
import rest_api_comandas.repository.ComandaRepository;
import rest_api_comandas.repository.UsuarioRepository;

@RestController
@RequestMapping("/comandas")
public class ComandaController extends BaseController {

    @Autowired
    private ComandaRepository comandaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComandaProdutoRepository comandaProdutoRepository;

    // GET /comandas
    @GetMapping
    public ResponseEntity<?> listarUsuariosComandas() {
        return executar(() -> {
        	List<ComandaEntity> comandas = comandaRepository.findAll();

            List<UsuarioPojo> resposta = comandas.stream().map(comanda -> {
                UsuarioEntity usuario = comanda.getUsuario();
      
                return UsuarioTransform.toPojo(usuario);
            
            }).distinct().collect(Collectors.toList());

            return resposta;
        });
    }

    // GET /comandas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return executar(() -> {
        	Optional<ComandaEntity> opt = comandaRepository.findById(id);
            
            if (opt.isEmpty()) {
                throw new ValidationError("Comanda não encontrada");
            }

            return ComandaTransform.toPojo(opt.get());
        });
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ComandaPojo dto) {
        return executar(() -> {
        	// Verifica se o usuário existe
            UsuarioEntity usuario = usuarioRepository.findById(dto.getIdUsuario()).orElse(null);

            if (usuario == null) {
                usuario = new UsuarioEntity();
            }

            // Atualiza dados (caso novo ou existente)
            usuario.setNome(dto.getNomeUsuario());
            usuario.setTelefone(dto.getTelefoneUsuario());
            usuario = usuarioRepository.save(usuario);

            // Cria a comanda com os produtos
            ComandaEntity comanda = ComandaTransform.toEntity(dto, usuario);
            comanda = comandaRepository.save(comanda);

            // Salva os produtos da comanda
            for (ComandaProdutoEntity produto : comanda.getProdutos()) {
                comandaProdutoRepository.save(produto);
            }

            return ComandaTransform.toPojo(comanda);
        });
    }


    // PUT /comandas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ComandaPojo dto) {
        return executar(() -> {
        	Optional<ComandaEntity> opt = comandaRepository.findById(id);
            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ComandaEntity comanda = opt.get();

            // Atualiza apenas os produtos (não remove os antigos)
            List<ComandaProdutoEntity> novosProdutos = dto.getProdutos().stream().map(p -> {
                ComandaProdutoEntity novo = ProdutoTransform.toEntity(p);
                novo.setComanda(comanda);
                return novo;
            }).collect(Collectors.toList());

            for (ComandaProdutoEntity p : novosProdutos) {
                comandaProdutoRepository.save(p);
            }

            comanda.getProdutos().addAll(novosProdutos);
            
            return ComandaTransform.toPojo(comanda);    
        });
    }

    // DELETE /comandas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
    	return executar(() -> {
            if (!comandaRepository.existsById(id)) {
                throw new ValidationError("Comanda não encontrada");
            }

            // Apaga os produtos da comanda primeiro
            List<ComandaProdutoEntity> produtos = comandaProdutoRepository.findByComandaId(id);
            comandaProdutoRepository.deleteAll(produtos);

            comandaRepository.deleteById(id);

            Map<String, Map<String, String>> resposta = new HashMap<>();
            Map<String, String> success = new HashMap<>();
            
            success.put("text", "comanda removida");
            resposta.put("success", success);

            return resposta;
    	});
    }
}