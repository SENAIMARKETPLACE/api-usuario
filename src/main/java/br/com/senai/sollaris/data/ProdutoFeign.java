package br.com.senai.sollaris.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senai.sollaris.data.model.ReturnProdutoDto;

@FeignClient(name = "api-produtos", url = "http://localhost:8100/")
public interface ProdutoFeign {
	
	@GetMapping("api/products/categories/{id}")
	public ResponseEntity<Page<ReturnProdutoDto>> listarProdutoPorCategoria(@PathVariable Integer id, Pageable pageable);
}
