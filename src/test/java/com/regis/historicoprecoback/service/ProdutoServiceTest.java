package com.regis.historicoprecoback.service;

import com.regis.historicoprecoback.dto.ProdutoRequestDTO;
import com.regis.historicoprecoback.model.Produto;
import com.regis.historicoprecoback.repository.ProdutoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Captor
    ArgumentCaptor<Produto> produtoCaptor;

    @Test
    public void deveSalvarONomeCorretoDoProduto() {
        ProdutoRequestDTO produtoRequestDto = new ProdutoRequestDTO();
        produtoRequestDto.setNome("Chocolate");

        produtoService.cadastrarProduto(produtoRequestDto);

        verify(produtoRepository).save(produtoCaptor.capture());
        Produto produtoCaptorValue = produtoCaptor.getValue();

        assertEquals("Chocolate", produtoCaptorValue.getNome());
    }
}
