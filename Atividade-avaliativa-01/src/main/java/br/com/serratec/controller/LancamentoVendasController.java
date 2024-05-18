package br.com.serratec.controller;

import java.util.List;
import java.util.Set;

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

import br.com.serratec.dtos.LancamentoVendasMostrarDTO;
import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.service.LancamentoVendasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class LancamentoVendasController {

	@Autowired
	private LancamentoVendasService service;
	
	@GetMapping
	public ResponseEntity<List<LancamentoVendasMostrarDTO>> getAllVendas(){
		return ResponseEntity.ok(service.getAllVendas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Set<LancamentoVendasMostrarDTO>> getVendaById(@PathVariable Long id){
		Set<LancamentoVendasMostrarDTO> venda = service.getVendaById(id);
		return ResponseEntity.ok(venda);
	}
	@PostMapping
	public ResponseEntity<LancamentoVendas> saveVenda(@Valid @RequestBody LancamentoVendas venda){
		
		return ResponseEntity.created(null).body(service.saveVenda(venda));
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<LancamentoVendas>> saveAll(@Valid @RequestBody List<LancamentoVendas> vendas){
		vendas = service.saveAll(vendas);
	
		return ResponseEntity.ok(vendas);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LancamentoVendas> updateVenda(@PathVariable Long id,@Valid @RequestBody LancamentoVendas venda){
		venda = service.updateVenda(id, venda);
		return ResponseEntity.ok(venda);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletevenda(@PathVariable Long id){
		service.deleteVenda(id);
		return ResponseEntity.noContent().build();
	}
	
}
