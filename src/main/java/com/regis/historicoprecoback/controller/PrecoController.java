package com.regis.historicoprecoback.controller;

import com.regis.historicoprecoback.dto.PrecoRequestDTO;
import com.regis.historicoprecoback.dto.ProdutoResponseDTO;
import com.regis.historicoprecoback.model.Preco;
import com.regis.historicoprecoback.service.PrecoService;
import com.regis.historicoprecoback.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/produtos/{produtoId}/precos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PrecoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PrecoService precoService;

    @PostMapping()
    public ResponseEntity<Object> cadastrarPreco(@PathVariable String produtoId, @RequestBody PrecoRequestDTO precoRequestDto) {
        ProdutoResponseDTO produtoResponseDto = produtoService.listarProdutoPorId(produtoId);
        if (produtoResponseDto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto com id " + produtoId + " não existe");
        }

        precoService.cadastrarPreco(precoRequestDto, produtoResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(precoRequestDto);
    }

    @PutMapping("/{precoId}")
    public ResponseEntity<Object> atualizarPreco(@PathVariable Long precoId, @RequestBody PrecoRequestDTO precoRequestDto) {
        Optional<Preco> precoOpt = precoService.listarPrecoPorId(precoId);
        if (!precoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("preço com id " + precoId + " não existe");
        }

        precoService.atualizarPreco(precoOpt, precoRequestDto);

        return ResponseEntity.ok().body(precoRequestDto);
    }

    @DeleteMapping("/{precoId}")
    public ResponseEntity<Object> excluirPreco(@PathVariable Long precoId) {
        Optional<Preco> preco = precoService.listarPrecoPorId(precoId);
        if (!preco.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("preço com id " + precoId + " não existe");
        }

        precoService.excluirPreco(precoId);

        return ResponseEntity.ok().body("preço excluído com sucesso");
    }

}
