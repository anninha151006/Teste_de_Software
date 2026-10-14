package br.com.teste.acompanhamento_api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Map<Integer, Usuario> usuarios =
            new ConcurrentHashMap<>();

    // LISTAR todos os usuários
    @GetMapping
    public Collection<Usuario> listar() {
        return usuarios.values();
    }

    // BUSCAR um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(
            @PathVariable int id) {

        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    // CADASTRAR um usuário
    @PostMapping
    public ResponseEntity<?> cadastrar(
            @RequestBody Usuario usuario) {

        if (usuario.getIdUsuario() <= 0
                || usuario.getNome() == null
                || usuario.getNome().isBlank()
                || usuario.getDataNascimento() == null) {

            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "Dados inválidos"));
        }

        Usuario existente = usuarios.putIfAbsent(
                usuario.getIdUsuario(), usuario);

        if (existente != null) {
            return ResponseEntity.status(409)
                    .body(Map.of("erro", "ID já cadastrado"));
        }

        return ResponseEntity.status(201).body(usuario);
    }

    // ATUALIZAR o nome de um usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable int id,
            @RequestBody Usuario dados) {

        Usuario usuario = usuarios.get(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        if (dados.getNome() == null
                || dados.getNome().isBlank()) {

            return ResponseEntity.badRequest()
                    .body(Map.of("erro", "Nome inválido"));
        }

        usuario.setNome(dados.getNome());

        return ResponseEntity.ok(usuario);
    }

    // EXCLUIR um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable int id) {

        Usuario removido = usuarios.remove(id);

        if (removido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
