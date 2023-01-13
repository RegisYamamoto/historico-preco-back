package com.regis.historicoprecoback.service;

import com.regis.historicoprecoback.dto.PrecoResponseDTO;
import com.regis.historicoprecoback.dto.ProdutoRequestDTO;
import com.regis.historicoprecoback.dto.ProdutoResponseDTO;
import com.regis.historicoprecoback.dto.ProdutoResponsePageDTO;
import com.regis.historicoprecoback.model.Preco;
import com.regis.historicoprecoback.model.Produto;
import com.regis.historicoprecoback.repository.PrecoRepository;
import com.regis.historicoprecoback.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PrecoRepository precoRepository;

    public void cadastrarProduto(ProdutoRequestDTO produtoRequestDto) {
        Produto produto = new Produto();
        produto.setId(produtoRequestDto.getId());
        produto.setNome(produtoRequestDto.getNome());
        produto.setDescricao(produtoRequestDto.getDescricao());
        produto.setMarca(produtoRequestDto.getMarca());
        produto.setDataCadastro(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        produtoRepository.save(produto);
    }

    public ProdutoResponsePageDTO listarTodosProdutos(Pageable pageable) {
        Page<Produto> produtos = produtoRepository.findAll(pageable);

        List<ProdutoResponseDTO> produtosResponseDto = new ArrayList<>();
        for (Produto produto : produtos) {
            ProdutoResponseDTO produtoResponseDto = new ProdutoResponseDTO();
            produtoResponseDto.setId(produto.getId());
            produtoResponseDto.setNome(produto.getNome());
            produtoResponseDto.setDescricao(produto.getDescricao());
            produtoResponseDto.setMarca(produto.getMarca());
            produtoResponseDto.setDataCadastro(produto.getDataCadastro());
            produtoResponseDto.setDataUltAtualizacao(produto.getDataUltAtualizacao());

            List<PrecoResponseDTO> precosResponseDto = new ArrayList<>();
            for (Preco preco : produto.getPrecos()) {
                PrecoResponseDTO precoResponseDto = new PrecoResponseDTO();
                precoResponseDto.setId(preco.getId());
                precoResponseDto.setPreco(preco.getPreco());
                precoResponseDto.setDataConsulta(preco.getDataConsulta());
                precoResponseDto.setLojaConsultadada(preco.getLojaConsultadada());
                precosResponseDto.add(precoResponseDto);
            }
            produtoResponseDto.setPrecos(precosResponseDto);

            produtosResponseDto.add(produtoResponseDto);
        }

        ProdutoResponsePageDTO produtoResponsePageDTO = new ProdutoResponsePageDTO();
        produtoResponsePageDTO.setPage(pageable.getPageNumber());
        produtoResponsePageDTO.setSize(pageable.getPageSize());
        produtoResponsePageDTO.setTotalPages(produtos.getTotalPages());
        produtoResponsePageDTO.setProdutosResponseDto(produtosResponseDto);

        return produtoResponsePageDTO;
    }

    public ProdutoResponseDTO listarProdutoPorId(String id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.isPresent()) {
            return new ProdutoResponseDTO();
        }

        ProdutoResponseDTO produtoResponseDto = new ProdutoResponseDTO();
        produtoResponseDto.setId(produto.get().getId());
        produtoResponseDto.setNome(produto.get().getNome());
        produtoResponseDto.setDescricao(produto.get().getDescricao());
        produtoResponseDto.setMarca(produto.get().getMarca());
        produtoResponseDto.setDataCadastro(produto.get().getDataCadastro());
        produtoResponseDto.setDataUltAtualizacao(produto.get().getDataUltAtualizacao());

        List<PrecoResponseDTO> precosResponseDto = new ArrayList<>();
        for (Preco preco : produto.get().getPrecos()) {
            PrecoResponseDTO precoResponseDto = new PrecoResponseDTO();
            precoResponseDto.setId(preco.getId());
            precoResponseDto.setPreco(preco.getPreco());
            precoResponseDto.setDataConsulta(preco.getDataConsulta());
            precoResponseDto.setLojaConsultadada(preco.getLojaConsultadada());
            precosResponseDto.add(precoResponseDto);
        }
        produtoResponseDto.setPrecos(precosResponseDto);

        return produtoResponseDto;
    }

    public void atualizarProduto(ProdutoRequestDTO produtoRequestDto, ProdutoResponseDTO produtoResponseDto) {
        Produto produto = new Produto();
        produto.setId(produtoRequestDto.getId());
        produto.setNome(produtoRequestDto.getNome());
        produto.setDescricao(produtoRequestDto.getDescricao());
        produto.setMarca(produtoRequestDto.getMarca());
        produto.setDataCadastro(produtoResponseDto.getDataCadastro());
        produto.setDataUltAtualizacao(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

        List<PrecoResponseDTO> precosResponseDto = new ArrayList<>();
        for (Preco preco : produto.getPrecos()) {
            PrecoResponseDTO precoResponseDto = new PrecoResponseDTO();
            precoResponseDto.setId(preco.getId());
            precoResponseDto.setPreco(preco.getPreco());
            precoResponseDto.setDataConsulta(preco.getDataConsulta());
            precoResponseDto.setLojaConsultadada(preco.getLojaConsultadada());
            precosResponseDto.add(precoResponseDto);
        }
        produtoResponseDto.setPrecos(precosResponseDto);

        produtoRepository.save(produto);
    }

    public void excluirProduto(String id) {
        precoRepository.deleteAllByProdutoId(id);
        produtoRepository.deleteById(id);
    }

}
