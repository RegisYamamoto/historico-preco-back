package com.regis.historicoprecoback.controller;

import com.regis.historicoprecoback.dto.ProdutoRequestDTO;
import com.regis.historicoprecoback.dto.ProdutoResponseDTO;
import com.regis.historicoprecoback.dto.ProdutoResponsePageDTO;
import com.regis.historicoprecoback.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> cadastrarProduto(@RequestBody ProdutoRequestDTO produtoRequestDto) {
        ProdutoResponseDTO produtoResponseDto = produtoService.listarProdutoPorId(produtoRequestDto.getId());
        if (!produtoResponseDto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("produto com id " + produtoRequestDto.getId() + " já existe");
        }

        produtoService.cadastrarProduto(produtoRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRequestDto);
    }

    @GetMapping
    public ResponseEntity<Object> listarTodosProdutos(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)
            Pageable pageable) {
        ProdutoResponsePageDTO produtoResponsePageDto = produtoService.listarTodosProdutos(pageable);
        return ResponseEntity.ok().body(produtoResponsePageDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> listarProdutoPorId(@PathVariable String id) {
        ProdutoResponseDTO produtoResponseDto = produtoService.listarProdutoPorId(id);
        if (produtoResponseDto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("preduto com id " + id + " não existe");
        }

        return ResponseEntity.ok().body(produtoResponseDto);
    }

    @PutMapping
    public ResponseEntity<Object> atualizarProduto(@RequestBody ProdutoRequestDTO produtoRequestDto) {
        ProdutoResponseDTO produtoResponseDto = produtoService.listarProdutoPorId(produtoRequestDto.getId());

        if (produtoResponseDto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto com id " + produtoRequestDto.getId() + " não existe");
        }

        produtoService.atualizarProduto(produtoRequestDto, produtoResponseDto);

        return ResponseEntity.ok().body(produtoRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirProduto(@PathVariable String id) {
        ProdutoResponseDTO produtoResponseDto = produtoService.listarProdutoPorId(id);

        if (produtoResponseDto.getId().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto com id " + id + " não existe");
        }

        produtoService.excluirProduto(id);

        return ResponseEntity.ok().body("produto " + id + " excluído com sucesso");
    }

}
