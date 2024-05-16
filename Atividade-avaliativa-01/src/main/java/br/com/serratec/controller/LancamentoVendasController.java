package br.com.serratec.controller;

import java.util.List;

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

import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.service.LancamentoVendasService;

@RestController
@RequestMapping("/vendas")
public class LancamentoVendasController {
	/*
	Criar a classe LancamentoService com os seguintes métodos:
	listarPorPagina getAllVendas -> totoal vendas
	listarPorId getById
	inserirLancamento saveVenda
	alterar updateVenda
	deletar deleteVenda
	*/
	@Autowired
	private LancamentoVendasService service;
	
	@GetMapping
	public ResponseEntity<List<LancamentoVendas>> getAllVendas(){
		return ResponseEntity.ok(service.getAllVendas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LancamentoVendas> getVendaById(@PathVariable Long id){
		LancamentoVendas venda = service.getVendaById(id);
		if(venda != null) {
			return ResponseEntity.ok(venda);
		}else {
			return ResponseEntity.badRequest().build();
			
		}
	}
	@PostMapping
	public ResponseEntity<LancamentoVendas> saveVenda(@RequestBody LancamentoVendas venda){
		return ResponseEntity.created(null).body(service.saveVenda(venda));
	}
	@PutMapping("/{id}")
	public ResponseEntity<LancamentoVendas> updateVenda(@PathVariable Long id, @RequestBody LancamentoVendas venda){
		venda = service.updateVenda(id, venda);
		if(venda != null) {
			return ResponseEntity.ok(venda);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletevenda(@PathVariable Long id){
		
		if(service.deleteVenda(id) != null) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
