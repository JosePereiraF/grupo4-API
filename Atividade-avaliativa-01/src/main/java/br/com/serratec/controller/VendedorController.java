package br.com.serratec.controller;

import br.com.serratec.dtos.LancamentoVendasMostrarDTO;
import br.com.serratec.dtos.VendedorDTO;
import br.com.serratec.entities.LancamentoVendas;
import br.com.serratec.entities.Vendedor;
import br.com.serratec.service.LancamentoVendasService;
import br.com.serratec.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping
	public ResponseEntity<List<VendedorDTO>> getAllVendedores(){
		return ResponseEntity.ok(vendedorService.getAllVendedores());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Set<VendedorDTO>> getVendedorById(@PathVariable Long id){
		Set<VendedorDTO> vendedores = vendedorService.getVendedorById(id);
		if(vendedores != null) {
			return ResponseEntity.ok(vendedores);
		}else {
			return ResponseEntity.badRequest().build();
			
		}
	}
	@PostMapping
	public ResponseEntity<Vendedor> saveVendedor(@Valid @RequestBody Vendedor vendedor){
		return ResponseEntity.created(null).body(vendedorService.saveVendedor(vendedor));
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Vendedor>> saveAll(@Valid @RequestBody List<Vendedor> vendedores){
		vendedores = vendedorService.saveAll(vendedores);
		if(vendedores == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(vendedores);
			
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vendedor> updateVenda(@PathVariable Long id,@Valid @RequestBody Vendedor vendedor){
		vendedor = vendedorService.updateVendedor(id, vendedor);
		if(vendedor != null) {
			return ResponseEntity.ok(vendedor);
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletevendedor(@PathVariable Long id){
		vendedorService.deleteVendedor(id);
		return ResponseEntity.noContent().build();
	}
	
}
